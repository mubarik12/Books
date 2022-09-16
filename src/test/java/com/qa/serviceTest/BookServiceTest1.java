package com.qa.serviceTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.persistence.domain.Book;
import com.qa.repo.BookRepo;
import com.qa.service.BookService;

@SpringBootTest  //(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class BookServiceTest1 {
	@Autowired

	private BookService service;

	@MockBean


	private BookRepo repo;

	@Test
	void testGetById() {
	final Long Id = (long) 1;
	final Optional<Book> book = Optional.ofNullable(new Book(Id, 123, "Kangaroo",145289, "Australia"));

	Mockito.when(this.repo.findById(Id)).thenReturn(book);

	assertThat(this.service.findById(Id)).isEqualTo(book);

	Mockito.verify(this.repo, Mockito.times(1)).findById(Id);
	}

	@Test
	void testGetAllBook() {
	final List<Book> book = List.of(new Book(1, 123, "Kangaroo", 145289,"Australia"),
	new Book(2, 23, "Wallaby",145993, "Holland"));

	Mockito.when(this.repo.findAll()).thenReturn(book);

	assertThat(this.service.getAll()).isEqualTo(book);

	Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}

	@Test
	void testUpdate() { // REMEMBER TO OVERRIDE THE equals() METHOD IN YOUR ENTITY
	final Long id = (long) 1;
	Book book = new Book(id, 3, "Kangaroo",145289, "Australia");
	Optional<Book> optionalAuthor = Optional.of(book);

	Book newBook = new Book (id, 23, "Wallabee", 145993, "Cyprus");

	Mockito.when(this.repo.findById(id)).thenReturn(optionalAuthor);
	Mockito.when(this.repo.save(newBook)).thenReturn(newBook);

	assertThat(this.service.updateBook(book.getId(),newBook)).isEqualTo(newBook);

	Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	Mockito.verify(this.repo, Mockito.times(1)).save(newBook);
	}


	@Test
	void testDelete() {
	final Long id = (long) 1;

	Mockito.when(this.repo.existsById(id)).thenReturn(false);

	assertThat(this.service.removeBook(id)).isEqualTo(true);

	Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
	}
}
