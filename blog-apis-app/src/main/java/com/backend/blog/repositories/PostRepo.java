package com.backend.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.blog.entities.Category;
import com.backend.blog.entities.Post;
import com.backend.blog.entities.User;

public interface PostRepo extends JpaRepository<Post,Integer>{
	
	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	@Query("Select p from Post p where p.title like :key")
	List<Post> searchByTitle(@Param("key") String title);
	

}
