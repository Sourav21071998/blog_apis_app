package com.backend.blog.services.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.blog.entities.Comment;
import com.backend.blog.entities.Post;
import com.backend.blog.exceptions.ResourceNotFoundException;
import com.backend.blog.payload.CommentDto;
import com.backend.blog.payload.PostDto;
import com.backend.blog.repositories.CommentRepo;
import com.backend.blog.repositories.PostRepo;
import com.backend.blog.services.CommentService;

@Service
public class CommentServiceImplementation implements CommentService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post Id",postId));
		Comment comment=this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment savedComment=this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment,CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment com=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "comment Id", commentId));
		this.commentRepo.delete(com);
	}

}
