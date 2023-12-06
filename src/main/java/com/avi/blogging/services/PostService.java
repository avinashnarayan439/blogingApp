package com.avi.blogging.services;

import com.avi.blogging.Entity.Post;
import com.avi.blogging.Payload.PostDto;
import com.avi.blogging.PostResponse;

import java.util.List;

public interface PostService {
    //createPost
    PostDto createpost(PostDto postDto,Long categoryId,Long userId);

    //updatePost
    PostDto updatePost(PostDto postDto,Long postId);

    //deletePost
    void deletePost(Long postId);

    //getAllPost
    PostResponse getAllPost(int pageNumber, int pageSize);

    //getPostById
    PostDto getPostById(Long postId);

    //getAllPostByCategory
    PostResponse getPostByCategory(Long categoryId ,int pageNumber,int pageSize);

    //getAllPostByUser
    PostResponse getPostByUser(Long userId ,int pageNumber,int pageSize);

    //searchPost
    List<Post> searchPost(String keyword);

}
