package com.gp.pf.user.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gp.pf.user.service.dto.UserRequest;
import com.gp.pf.user.service.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@Slf4j
class ApplicationTests {

	@Container
	static MongoDBContainer container = new MongoDBContainer("mongo:latest");

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	ObjectMapper mapper;

	
	@Autowired
	UserRepository repository;
	
	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		dynamicPropertyRegistry.add("spring.data.mongodb.url", container::getReplicaSetUrl);
	}

	@Test
	void shouldCreateUser() throws Exception {
		UserRequest userRequest = getUserRequest();
		int initialSize = repository.findAll().size();
		log.info("Initially there are {} records.",initialSize);
		String requestString = mapper.writeValueAsString(userRequest);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/api/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestString)
		);
		log.info("Create Request Completed");
		Assertions.assertEquals(repository.findAll().size(),initialSize+1);
		log.info("Read Request Completed");

	}

	private UserRequest getUserRequest() {
		return UserRequest.builder().username("dheerajkumar04")
				.firstName("Dheeraj")
				.lastName("Kumar")
				.address("Address is okay")
				.email("dheeraj@gmail.com")
				.mobile("+918421986081")
				.build();
	}

}
