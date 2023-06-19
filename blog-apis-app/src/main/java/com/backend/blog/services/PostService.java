package com.backend.blog.services;

import java.util.List;

import com.backend.blog.entities.Category;
import com.backend.blog.entities.Post;
import com.backend.blog.entities.User;
import com.backend.blog.payload.PostDto;
import com.backend.blog.payload.PostResponse;

public interface PostService {
	
	//create
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
    
    //update
    PostDto updatePost(PostDto postDto,Integer id);
    
    //delete
    public void deletePost(Integer id);
    
    //get all posts
    PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
    
    //get single post
    PostDto getPostById(Integer postId);
    
    //get all posts by category
    List<PostDto> getAllPostsByCategory(Integer categoryId);
    
    //get all posts by user
    List<PostDto> getAllPostsByUser(Integer userId);
    
    //search posts
    List<PostDto> searchPosts(String keyword);
    
}
