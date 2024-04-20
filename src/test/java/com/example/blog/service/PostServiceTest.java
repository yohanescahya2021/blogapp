package com.example.blog.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.example.blog.domain.Post;
import com.example.blog.repository.PostRepository;
import com.example.blog.service.impl.PostServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

  @Mock
  private PostRepository postRepository;

  @InjectMocks
  private PostServiceImpl postService;

  private Post post;

  @BeforeEach
  public void setup() {
    post = new Post();
    post.setAuthor("author 1");
    post.setBody("body 1");
    post.setTitle("title 1");
  }

  @Test
  void createPost() {

    given(postRepository.save(post)).willReturn(post);

    Post savedPost = postService.createPost(post);

    assertThat(savedPost).isNotNull();
  }
}