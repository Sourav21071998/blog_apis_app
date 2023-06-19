package com.backend.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.blog.entities.Category;
import com.backend.blog.exceptions.ResourceNotFoundException;
import com.backend.blog.payload.CategoryDto;
import com.backend.blog.repositories.CategoryRepo;
import com.backend.blog.services.CategoryService;

@Service
public class CategoryServiceImplementation implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category cat=this.modelMapper.map(categoryDto,Category.class);
		Category addedCat=this.categoryRepo.save(cat);
		
		return this.modelMapper.map(addedCat,CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category ID",categoryId));
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		Category updatedCat=this.categoryRepo.save(cat);
		
		return this.modelMapper.map(updatedCat,CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Catrgory","Category ID",categoryId));
		this.categoryRepo.delete(cat);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Catrgory","Category ID",categoryId));
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		// TODO Auto-generated method stub
		List<Category> categories=this.categoryRepo.findAll();
		List<CategoryDto> catDtos=categories.stream().map((cat)->this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
		return catDtos;
	}

}
