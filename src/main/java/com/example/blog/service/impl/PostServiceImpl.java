package com.example.blog.service.impl;

import com.example.blog.domain.Post;
import com.example.blog.dto.PostResponse;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.repository.PostRepository;
import com.example.blog.service.PostService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

  private final PostRepository postRepository;

  @Override
  public Post createPost(Post post) {

    return postRepository.save(post);
  }

  @Override
  public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

    Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
        : Sort.by(sortBy).descending();

    Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

    Page<Post> posts = postRepository.findAll(pageable);

    List<Post> listOfPosts = posts.getContent();

    PostResponse postResponse = new PostResponse();
    postResponse.setPosts(listOfPosts);
    postResponse.setPageNo(posts.getNumber());
    postResponse.setPageSize(posts.getSize());
    postResponse.setTotalElements(posts.getTotalElements());
    postResponse.setTotalPages(posts.getTotalPages());
    postResponse.setLast(posts.isLast());

    return postResponse;
  }

  @Override
  public Post getPostById(long id) {

    return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
  }

  @Override
  public Post updatePost(Post post, long id) {

    Post postUpdate = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

    postUpdate.setTitle(post.getTitle());
    postUpdate.setBody(post.getBody());
    postUpdate.setTitle(post.getTitle());
    postUpdate.setAuthor(post.getAuthor());
    Post updatedPost = postRepository.save(postUpdate);
    return updatedPost;
  }

  @Override
  public void deletePostById(long id) {

    Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    postRepository.delete(post);
  }
}
