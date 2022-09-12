package com.qa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.persistence.domain.Author;
import com.qa.repo.AuthorRepo;




@Service
public class AuthorService {
	
	
	
	
	
		
		

		    //private List<Person> people = new ArrayList<>();
		
		    private AuthorRepo repo;

		    public AuthorService(AuthorRepo repo) {
		        super();
		        this.repo = repo;
		    }

		    // CRUD methods
		

		    public Author addAuthor(Author author) {
		        return this.repo.save(author);
		    }
		    public List<Author> getAllAuthor() {
		        return this.repo.findAll();
		    }
		        

		    public Author updateAuthor(Long id, Author newAuthor) {
		        Optional<Author> existingOptional = this.repo.findById(id);
		        Author existing = existingOptional.get();

		        existing.setAge(newAuthor.getAge());
		        existing.setName(newAuthor.getName());

		        return this.repo.save(existing);
		        }
		    
		    
		    
		    	public List <Author> getAll(){
		    	
		    	return this.repo.findAll();
		    }
	            //delete
		    public boolean removeAuthor(Long id) {
		        // removes the entity
		        this.repo.deleteById(id);
		        // checks to see if it still exists
		        boolean exists = this.repo.existsById(id);
		        // returns true if entity no longer exists
		        return !exists;
		        }

}

