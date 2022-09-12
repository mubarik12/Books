package com.qa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.persistence.domain.Book;






@Repository
public interface BookRepo extends JpaRepository<Book, Long>{

}

