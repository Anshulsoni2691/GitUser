package com.systemplus.spring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootRestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class GitUserControllerIt {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testGetGitUserSuccess() {
		String name = "UNKNOWN";
		ResponseEntity<String> responseEntity = this.restTemplate
				.getForEntity("http://localhost:" + port + "/getUsers/repository?name=" + name, String.class);
		assertEquals(200, responseEntity.getStatusCodeValue());
		assertTrue(responseEntity.getBody().contains("login"));
	}

	@Test
	public void testGetGitUserDoesNotExist() {
		String name = "Invalid1234";
		ResponseEntity<String> responseEntity = this.restTemplate
				.getForEntity("http://localhost:" + port + "/getUsers/repository?name=" + name, String.class);
		assertEquals(404, responseEntity.getStatusCodeValue());
		assertTrue(responseEntity.getBody().contains("No such user"));
	}

	@Test
	public void testGetGitUserInvalidHeader() {
		String name = "UNKOWN";
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));

		HttpEntity<String> entity = new HttpEntity<>("body", headers);

		ResponseEntity<String> responseEntity = this.restTemplate.exchange(
				"http://localhost:" + port + "/getUsers/repository?name=" + name, HttpMethod.GET, entity, String.class);
		assertEquals(406, responseEntity.getStatusCodeValue());
		assertTrue(responseEntity.getBody().contains("acceptable content type"));

	}

}
