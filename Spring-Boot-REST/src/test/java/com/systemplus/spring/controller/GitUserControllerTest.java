package com.systemplus.spring.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.systemplus.spring.bean.User;

@RunWith(MockitoJUnitRunner.class)
class GitUserControllerTest {

	@Mock
	RestTemplate restTemplate;

	@InjectMocks
	GitUserController gitUserController;

	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test() {
		Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any(), Mockito.anyMap()))
				.thenReturn(new User[0]);
		ResponseEntity<List<User>> response = gitUserController.getUserRepoDetails("test");
	}

}
