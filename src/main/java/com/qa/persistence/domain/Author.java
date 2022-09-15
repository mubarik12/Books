package com.qa.persistence.domain;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;








@Entity

public class Author {

 
	    
	    
	    //Fields 
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column (name = "author_id")
	    private long  id ;
	    
	    @Column(name = "author_number")
	    
	    private int authorNumber;
	    @Column(name = "author_name")
	    private String name;
	    @Column (name = "author_age")
	    private int age;
	    
	    @OneToMany
	    
	    private List<Book> books;
	  
	    
	    //constructors
	    public Author() {
	    	super();
	        
	    }
	    
	    
	     public Author(long id, int authorNumber, String name, int age) {
	        super();
	        this.id = id;
	        this.authorNumber = authorNumber;
	        this.name = name;
	    }
	     
	   
	     
	    //getters and setters
	    
	    public int getAge() {
			return age;
		}


		public void setAge(int age) {
			this.age = age;
		}


		public long getId() {
	        return id;
	    }
	    public void setId(long id) {
	        this.id = id;
	    }
	    public int getAuthorNumber() {
	        return authorNumber;
	    }
	    public void setAuthorNumber(int authorNumber) {
	        this.authorNumber = authorNumber;

	}
	    public String getName() {
	        return name;
	    }
	    public void setName(String name) {
	        this.name = name;
	    }
	    
	}

