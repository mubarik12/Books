package com.qa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.persistence.domain.Book;
import com.qa.repo.BookRepo;


@Service
public class BookService {
	
	
	
	
	
		
		

		    //private List<Person> people = new ArrayList<>();
		
		    private BookRepo repo;

		    public BookService(BookRepo repo) {
		        super();
		        this.repo = repo;
		    }

		    // CRUD methods
		

		    public Book addBook(Book book) {
		        return this.repo.save(book);
		    }
		    public List<Book> getAllBook() {
		        return this.repo.findAll();
		    }
		        

		    public Book updateBook(Long id, Book newBook) {
		        Optional<Book> existingOptional = this.repo.findById(id);
		        Book existing = existingOptional.get();

		        
		        existing.setName(newBook.getName());
                existing.setNumberPages(newBook.getNumberPages());
                existing.setBookTitle(newBook.getBookTitle());
		        return this.repo.save(existing);    
		        
		   
		    }
		    
		    public List <Book> getAll(){
		    	
		    	
		    	return this.repo.findAll();
		    }
		        
	            //delete
		    public boolean removeBook(Long id) {
		        // removes the entity
		        this.repo.deleteById(id);
		        // checks to see if it still exists
		        boolean exists = this.repo.existsById(id);
		        // returns true if entity no longer exists
		        return !exists;
		        }

}
