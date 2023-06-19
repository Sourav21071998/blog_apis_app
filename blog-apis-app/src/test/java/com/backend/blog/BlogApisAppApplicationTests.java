package com.backend.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.backend.blog.repositories.UserRepo;

@SpringBootTest
class BlogApisAppApplicationTests {

	@Autowired
	private UserRepo userRepo;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void testRepo()
	{
		System.out.println(this.userRepo.getClass().getName());
		System.err.println(this.userRepo.getClass().getPackage());
	}

}
