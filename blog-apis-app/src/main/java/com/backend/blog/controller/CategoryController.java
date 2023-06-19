package com.backend.blog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.blog.payload.ApiResponse;
import com.backend.blog.payload.CategoryDto;
import com.backend.blog.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
	{
		CategoryDto createCategory=this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
	}
	
	@PutMapping("/{Id}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer Id)
	{
		CategoryDto updatedCategory=this.categoryService.updateCategory(categoryDto, Id);
		return new ResponseEntity<CategoryDto>(updatedCategory,HttpStatus.OK);
	}
	
	@DeleteMapping("/{Id}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer Id)
	{
		this.categoryService.deleteCategory(Id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User is deleted",true,HttpStatus.OK),HttpStatus.OK);
	}
	
	@GetMapping("/{Id}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer Id)
	{
		CategoryDto categoryDto=this.categoryService.getCategory(Id);
		return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategories()
	{
		List<CategoryDto> categories=this.categoryService.getCategories();
		return ResponseEntity.ok(categories);
	}
	

}
