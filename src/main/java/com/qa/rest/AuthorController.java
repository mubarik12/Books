package com.qa.rest;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.persistence.domain.Author;




	@RestController
	public class AuthorController {
		private List<Author> author = new ArrayList<>();  
		
		@GetMapping("/test")
		    public String test() {
		        return "Hello, World!";
		        
		        
		
		    }

	        // Create
	      
	        
		
		   @PostMapping("/create")
		    public boolean addAuthor(@RequestBody Author author) {
		        return this.author.add(author);
		    }
		   @GetMapping("/getAll")
		    public List<Author> getAll() {
		        return this.author;
		    }
		    @PutMapping("/update")
		    public Author updateAuthor(@PathParam("id") int id, @RequestBody Author author) {
		        // Remove existing Author with matching 'id'
		        this.author.remove(id);
		        // Add new Person in its place
		        this.author.add(id, author);
		        // Return updated Accounts from List
		        return this.author.get(id);
		    
		    }
		    @DeleteMapping("/delete/{id}")
		    public Author removeAuthor(@PathVariable int id) {
		        // Remove Accounts and return it
		        return this.author.remove(id);
		    }
	}
