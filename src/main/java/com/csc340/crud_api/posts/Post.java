package com.csc340.crud_api.posts;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String content;

  @Column(nullable = false)
  private String author;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime createdAt;

  private String thumbnailUrl;

  public Post(String title, String content, String author, String thumbnailUrl) {
    this.title = title;
    this.content = content;
    this.author = author;
    this.thumbnailUrl = thumbnailUrl;
  }

  @PrePersist
  @PreUpdate
  protected void onUpdate() {
    this.createdAt = LocalDateTime.now();
  }

}
