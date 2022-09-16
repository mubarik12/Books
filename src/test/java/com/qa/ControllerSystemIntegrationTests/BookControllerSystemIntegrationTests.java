package com.qa.ControllerSystemIntegrationTests;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.persistence.domain.Book;
import com.qa.repo.BookRepo;
import com.qa.rest.BookController;
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional


public class BookControllerSystemIntegrationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private BookRepo repository;

	// test data
	private List<Book> booksInDb = new ArrayList<>();
	private Book testBook;
	private Long testBookIsbn;
	private Book expectedTestBook;
	private Book bookUpdateInfo;

	@BeforeEach
	public void init() {
		List<Book> books = List.of(
				new Book(1, 2 , "One friday night", 400, "Great night"),
				new Book(2, 3 , "The second", 500, "Winter night"));
		booksInDb.addAll(repository.saveAll(books));
		testBook = new Book(4, 3,  "on Outside", 400, "Dawn");
		testBookIsbn = testBook.getId();
		expectedTestBook = new Book(testBook.getId(), testBook.getBookNumber(), testBook.getName(),
				testBook.getNumberPages(), testBook.getBookTitle());
		bookUpdateInfo = new Book(3,4, "Wednesday", 150, "what a day ");

	}

	@Disabled
	@Test
	public void getBooksTest() throws Exception {
		// mock http request builder
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/books");
		// specifying accept header return type
		mockRequest.accept(MediaType.APPLICATION_JSON);
		// JSON string for obj mapper
		String Book = objectMapper.writeValueAsString(booksInDb);
		// result matcher
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(Book);
		// request and assert
		mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}

	@Test
	public void getByIsbnTest() throws Exception {
		booksInDb.add(repository.save(testBook));
		// mock http request builder
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET,
				"/books/" + testBookIsbn);
		// specifying accept header return type
		mockRequest.accept(MediaType.APPLICATION_JSON);
		// JSON string for obj mapper
		String testBookStr = objectMapper.writeValueAsString(testBook);
		// result matcher
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(testBookStr);
		// request and assert
		mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}

	@Test
	public void createBookTest() throws Exception {
		// test object
		expectedTestBook = new Book(testBook.getId(), testBook.getBookNumber(), testBook.getName(),
				testBook.getNumberPages(), testBook.getBookTitle());
		// mock request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "/books");
		// specifying accept header return type
		mockRequest.contentType(MediaType.APPLICATION_JSON);
		mockRequest.content(objectMapper.writeValueAsString(testBook));

		mockRequest.accept(MediaType.APPLICATION_JSON);
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isCreated();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(expectedTestBook));

		mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);

	}

	@Test
	public void updateBookTest() throws Exception {
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.PUT,
				"/books/" + bookUpdateInfo.getId());
		// specifying accept header return type
		mockRequest.contentType(MediaType.APPLICATION_JSON);
		mockRequest.content(objectMapper.writeValueAsString(bookUpdateInfo));
		mockRequest.accept(MediaType.APPLICATION_JSON);
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isAccepted();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(bookUpdateInfo));

		mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}

	@Test
	public void deleteByIsbn() throws Exception {
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE,
				"/books/" + bookUpdateInfo.getId());
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isAccepted();
		mockMvc.perform(mockRequest).andExpect(statusMatcher);
	}
}


