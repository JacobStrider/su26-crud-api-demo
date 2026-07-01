package com.csc340.crud_api.posts;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

  @GetMapping
  public ResponseEntity<String> homePage() {
    String html = "<html> <body><h1>Hello from RestController!</h1>" +
        "<p>Our API endpoints:</p>" +
        " <ul><li><a href=\"/api/posts\">/api/posts</a></li>"+
        "<li><a href=\"/api/posts/1\">/api/posts/{id} (you can replace 1 with any post ID)</a></li>"+
        "<li><a href=\"/api/posts/author?name=John\">/api/posts/author?name=John (find posts by author)</a></li>"+
        "<li><a href=\"/api/posts/search?query=big\">/api/posts/search?query=big (search posts using title or content)</a></li>"+
        "</ul>"+
        "<p> Try POST, PUT, and DELETE requests using tools like EchoAPI, Postman, ThunderClient or curl.</p>" +
        "<p> For example, to create a new post, send a POST request to <code>/api/posts</code> with a JSON body like:</p>" +
        "<pre>{\"title\": \"My New Post\", \"content\": \"This is the content of my new post.\", \"author\": \"John Doe\"}</pre>" +
        "<p>To update a post, send a PUT request to <code>/api/posts/{id}</code> with a JSON body containing the updated fields.</p>" +
        "<p>To delete a post, send a DELETE request to <code>/api/posts/{id}</code>.</p>"
        +
        " </body></html>";

    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.TEXT_HTML)
        .body(html);

  }

}
