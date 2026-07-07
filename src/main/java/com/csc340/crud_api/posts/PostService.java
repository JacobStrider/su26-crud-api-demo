package com.csc340.crud_api.posts;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PostService {
  private final PostRepository postRepository;

  private static final String UPLOAD_DIR = "src/main/resources/static/images/";

  public PostService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public List<Post> getAllPosts() {
    return postRepository.findAll();
  }

  public Post getPostById(long id) {
    return postRepository.findById(id).orElse(null);
  }

  public Post createPost(Post post) {
    return postRepository.save(post);
  }

  public Post updatePost(long id, Post updatedPost) {
    Post existingPost = postRepository.findById(id).orElse(null);
    if (existingPost != null) {
      existingPost.setTitle(updatedPost.getTitle());
      existingPost.setContent(updatedPost.getContent());
      existingPost.setAuthor(updatedPost.getAuthor());
      return postRepository.save(existingPost);
    }
    return null;
  }

  public boolean deletePost(long id) {
    if (postRepository.existsById(id)) {
      postRepository.deleteById(id);
      return true;
    }
    return false;
  }

  public List<Post> searchPosts(String keyword) {
    return postRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(keyword, keyword);
  }

  public List<Post> getPostsByAuthor(String author) {
    return postRepository.findByAuthorContainingIgnoreCase(author);
  }

  public void saveThumbnail(Post post, MultipartFile thumbnailFile) {
    String originalFileName = thumbnailFile.getOriginalFilename();
    try {
      String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
      String fileName = "post_" + post.getId() + "." + fileExtension;
      Path filePath = Paths.get(UPLOAD_DIR + fileName);
      InputStream inputStream = thumbnailFile.getInputStream();

      Files.createDirectories(Paths.get(UPLOAD_DIR));// ensure the directory exists
      Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

      post.setThumbnailUrl(fileName);
      postRepository.save(post);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}