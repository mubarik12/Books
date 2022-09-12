package com.qa.rest;

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
import com.qa.service.AuthorService;




	@RestController
	public class AuthorController {
		// private List<Author> author = new ArrayList<>();  
		
		
		private AuthorService service;
		
		
		        
		
		    

	        // Create
	      
	        
		
		   public AuthorController(AuthorService service) {
			super();
			this.service = service;
		}
		@PostMapping("/author/create")
		    public Author addAuthor(@RequestBody Author author) {
		        return this.service.addAuthor(author);
		        
		}
		   @GetMapping("/author/getAll")
		    public List<Author> getAll() {
		        return this.service.getAll();
		    }
		    @PutMapping("/author/update")
		    public Author updateAuthor(@PathParam("id") Long id, @RequestBody Author author) {
		    	return this.service.updateAuthor(id, author);
		    	
//		        // Remove existing Author with matching 'id'
//		        this.service.remove(id);
//		        // Add new Person in its place
//		        this.author.add(id, author);
//		        // Return updated Accounts from List
//		        return this.author.get(id);
//		    
		    }
		    @DeleteMapping("/author/delete/{id}")
		    public boolean removeAuthor(@PathVariable Long id) {
		        // Remove Accounts and return it
		        return this.service.removeAuthor(id);
		    }
	}
