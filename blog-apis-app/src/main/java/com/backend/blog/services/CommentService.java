package com.backend.blog.services;

import org.springframework.stereotype.Service;

import com.backend.blog.payload.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto, Integer postId);
	
	void deleteComment(Integer commentId);
	
}
