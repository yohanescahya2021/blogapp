package com.example.blog.service;

import com.example.blog.domain.Post;
import com.example.blog.dto.PostResponse;

public interface PostService {
    Post createPost(Post post);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    Post getPostById(long id);

    Post updatePost(Post post, long id);

    void deletePostById(long id);
}
