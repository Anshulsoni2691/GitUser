package com.systemplus.spring.exception.handler;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

@RunWith(MockitoJUnitRunner.class)
class ExceptionResolverTest {

	@InjectMocks
	ExceptionResolver exceptionResolver;

	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testException404() {
		ResponseEntity<ErrorResponse> error = exceptionResolver.handleNoHandlerFound(Mockito.any(),
				new HttpClientErrorException(HttpStatus.NOT_FOUND));
		assertEquals(HttpStatus.NOT_FOUND, error.getStatusCode());
		assertTrue(error.getBody().getMessage().contains("No such user"));

	}

	@Test
	void testException406() {
		ResponseEntity<String> error = exceptionResolver.handleHttpMediaTypeNotAcceptableException();
		assertEquals(HttpStatus.NOT_ACCEPTABLE, error.getStatusCode());
		assertTrue(error.getBody().contains("acceptable"));

	}

}
