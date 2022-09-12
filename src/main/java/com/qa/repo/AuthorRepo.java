package com.qa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.persistence.domain.Author;




@Repository
public interface AuthorRepo extends JpaRepository<Author, Long>{

}