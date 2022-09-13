package com.qa.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
	@Entity


	public class Book {

 
	    
	    
	    //Fields 
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long  id ;
	    
	    @Column(unique = true, nullable = false)
	    private int bookNumber;
	    
	    private String name;
	    private String bookTitle;
	    
	   
	    private int numberPages;
	    
	    
	    //constructors
	    public Book() {
	        
	    }
	    
	    
	     public Book(long id, int bookNumber, String name, int numberPages, String bookTitle) {
	        super();
	        this.id = id;
	        this.bookNumber = bookNumber;
	        this.name = name;
	        this.numberPages = numberPages;
	        this.bookTitle = bookTitle;
	    }
	    //getters and setters
	    
	


	


	


		public String getBookTitle() {
			return bookTitle;
		}


		public void setBookTitle(String bookTitle) {
			this.bookTitle = bookTitle;
		}


		public int getNumberPages() {
			return numberPages;
		}


		public void setNumberPages(int numberPages) {
			this.numberPages = numberPages;
		}


		public long getId() {
	        return id;
	    }
	    public void setId(long id) {
	        this.id = id;
	    }
	    public int getBookNumber() {
	        return bookNumber;
	    }
	    public void setBookNumber(int bookNumber) {
	        this.bookNumber = bookNumber;

	}
	    public String getName() {
	        return name;
	    }
	    public void setName(String name) {
	        this.name = name;
	    }
	    
	}

