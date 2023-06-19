package com.backend.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.backend.blog.entities.Category;
import com.backend.blog.entities.Post;
import com.backend.blog.entities.User;
import com.backend.blog.exceptions.ResourceNotFoundException;
import com.backend.blog.payload.PostDto;
import com.backend.blog.payload.PostResponse;
import com.backend.blog.repositories.CategoryRepo;
import com.backend.blog.repositories.PostRepo;
import com.backend.blog.repositories.UserRepo;
import com.backend.blog.services.PostService;

@Service
public class PostServiceImplementation implements PostService {
	
	
	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId, Integer categoryId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User ID",userId));
		
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category ID", categoryId));
		
		Post post=this.modelMapper.map(postDto,Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post newPost=this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer id) {
		Post post=this.postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Post Id", "post Id", id));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setAddedDate(new Date());
		post.setImageName(postDto.getImageName());
		Post updatedPost=this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer id) {
		Post post=this.postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Post Id", "post Id",id));
		this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		//int pageSize=5;
		//int pageNumber=1;
		Sort sort=null;
		if(sortDir.equalsIgnoreCase("asc"))
		{
			sort=Sort.by(sortBy).ascending();
		}
		else
		{
			sort=Sort.by(sortBy).descending();
		}
		Pageable p=PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePost=this.postRepo.findAll(p);
		List<Post> allPosts = pagePost.getContent();
		List<PostDto> postDtos=allPosts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastpage(pagePost.isLast());
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post Id",postId));
		return this.modelMapper.map(post,PostDto.class);
	}

	@Override
	public List<PostDto> getAllPostsByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
		List<Post> posts=this.postRepo.findByCategory(cat);
		List<PostDto> postDtos= posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getAllPostsByUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User Id",userId));
		List<Post> posts=this.postRepo.findByUser(user);
		List<PostDto> postDtos= posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		postDtos.forEach((post)->System.out.println(post.toString()));
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts=this.postRepo.searchByTitle("%"+keyword+"%");
		List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
		}

}
