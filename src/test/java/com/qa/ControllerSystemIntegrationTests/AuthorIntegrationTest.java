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
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.persistence.domain.Author;

import com.qa.repo.AuthorRepo;
import com.qa.rest.AuthorController;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
public class AuthorIntegrationTest {


	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private AuthorRepo repository;

	// test data
	private List<Author> authorsInDb = new ArrayList<>();
	private Author testAuthor;
	private Long testAuthorIsbn;
	private Author expectedTestAuthor;
	private Author authorUpdateInfo;

	@BeforeEach
	public void init() {
		List<Author> authors = List.of(
		new Author(1, 2 , "One friday night", 400),
		new Author(5, 3 , "The second", 500));
		authorsInDb.addAll(repository.saveAll(authors));
		testAuthor = new Author(4, 3, "on a Outside", 450);
		testAuthorIsbn = testAuthor.getId();
		expectedTestAuthor = new Author(testAuthor.getId(), testAuthor.getAuthorNumber(), testAuthor.getName(),
		testAuthor.getAge());
		authorUpdateInfo = new Author(3,4, "Wednesday", 12);
	
	}

	@Disabled
	@Test
	public void getAuthorsTest() throws Exception {
		// mock http request builder
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/authors");
		// specifying accept header return type
		//mockRequest.accept(MediaType.APPLICATION_JSON);
		// JSON string for obj mapper
		String Author = objectMapper.writeValueAsString(authorsInDb);
		// result matcher
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(Author);
		// request and assert
		mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}

	@Test
	public void getByIsbnTest() throws Exception {
		authorsInDb.add(repository.save(testAuthor));
		// mock http request builder
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET,
				"/authors/" + testAuthorIsbn);
		// specifying accept header return type
		mockRequest.accept(MediaType.APPLICATION_JSON);
		// JSON string for obj mapper
		String testAuthorStr = objectMapper.writeValueAsString(testAuthor);
		// result matcher
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(testAuthorStr);
		// request and assert
		//mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}

	@Test
	public void createAuthorTest() throws Exception {
		// test object
		expectedTestAuthor = new Author(testAuthor.getId(), testAuthor.getAuthorNumber(), testAuthor.getName(),
				testAuthor.getAge());
		// mock request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "/authors");
		// specifying accept header return type
		mockRequest.contentType(MediaType.APPLICATION_JSON);
		mockRequest.content(objectMapper.writeValueAsString(testAuthor));

		mockRequest.accept(MediaType.APPLICATION_JSON);
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isCreated();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(expectedTestAuthor));

		//mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);

	}

	@Test
	public void updateAuthorTest() throws Exception {
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.PUT,
				"/authors/" + authorUpdateInfo.getId());
		// specifying accept header return type
		mockRequest.contentType(MediaType.APPLICATION_JSON);
		mockRequest.content(objectMapper.writeValueAsString(authorUpdateInfo));
		mockRequest.accept(MediaType.APPLICATION_JSON);
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isAccepted();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(authorUpdateInfo));

		//mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}

	@Test
	public void deleteByIsbn() throws Exception {
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE,
				"/authors/" + authorUpdateInfo.getId());
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isAccepted();
		//mockMvc.perform(mockRequest).andExpect(statusMatcher);
	}

}
