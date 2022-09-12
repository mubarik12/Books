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

import com.qa.persistence.domain.Book;
import com.qa.service.BookService;


	@RestController
	public class BookController {
		//private List<Book> book = new ArrayList<>();  
		
		
		private BookService service;
		
		
		

		public BookController(BookService service) {
			super();
			this.service = service;   // dependency injection 
		}

	
		        
		 

	        // Create
	      
	        
		
		   @PostMapping("/book/create")
		    public Book addBook(@RequestBody Book book) {
		        return this.service.addBook(book);
		    }
		   @GetMapping("/book/getAll")
		    public List<Book> getAll() {
		        return this.service.getAll();
		    }
		    @PutMapping("/book/update")
		    public Book updateBook(@PathParam("id") Long id, @RequestBody Book book) {
		        // Remove existing Accounts with matching 'id'
		    	
		    	return this.service.updateBook(id, book);
//		        this.service.removeBook(id);
//		        // Add new Person in its place
//		        this.service.add(id, book);
//		        // Return updated Books from List
//		        return this.book.get(id);
//		    
		    }
		    @DeleteMapping("/book/delete/{id}")
		    public boolean removeBook(@PathVariable long id) {
		        // Remove Books and return it
		        return this.service.removeBook(id);
		    }
	}

