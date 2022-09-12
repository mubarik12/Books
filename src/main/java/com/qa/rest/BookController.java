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

import com.qa.persistence.domain.Book;


	@RestController
	public class BookController {
		private List<Book> book = new ArrayList<>();  
		
		@GetMapping("/test")
		    public String test() {
		        return "Hello, World!";
		        
		        
		
		    }

	        // Create
	      
	        
		
		   @PostMapping("/create")
		    public boolean addBook(@RequestBody Book book) {
		        return this.book.add(book);
		    }
		   @GetMapping("/getAll")
		    public List<Book> getAll() {
		        return this.book;
		    }
		    @PutMapping("/update")
		    public Book updateBook(@PathParam("id") int id, @RequestBody Book book) {
		        // Remove existing Accounts with matching 'id'
		        this.book.remove(id);
		        // Add new Person in its place
		        this.book.add(id, book);
		        // Return updated Books from List
		        return this.book.get(id);
		    
		    }
		    @DeleteMapping("/delete/{id}")
		    public Book removeBook(@PathVariable int id) {
		        // Remove Books and return it
		        return this.book.remove(id);
		    }
	}

