package com.backend.blog.payload;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	
	@NotEmpty
	@Size(min=3,message="Name must be of minimum 3 characers")
	private String name;
	
	@Email(message="Email ID is not valid")
	private String email;
	
	@NotNull
	@Size(min=3,max=10,message="Password must be of min 3 characters and maximum of 10 characters")
	private String password;
	
	@NotNull
	private String about;
	
	
	private Set<RoleDto> roles=new HashSet<>();

}
