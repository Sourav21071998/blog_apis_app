package com.backend.blog.services;

import java.util.List;

import com.backend.blog.entities.User;
import com.backend.blog.payload.UserDto;

public interface UserService 
{
	UserDto registerNewUser(UserDto user);
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);
	
}
