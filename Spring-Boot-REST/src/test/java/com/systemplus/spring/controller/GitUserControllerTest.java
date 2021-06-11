package com.systemplus.spring.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.systemplus.spring.bean.Branch;
import com.systemplus.spring.bean.Commit;
import com.systemplus.spring.bean.Owner;
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
	void testUserRepoDetails() {
		Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any(), Mockito.anyMap()))
				.thenReturn(new User[] { getUser() });
		Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(new Branch[0]);
		ResponseEntity<List<User>> response = gitUserController.getUserRepoDetails("test");
		assertNotNull(response.getBody().get(0));
		assertEquals(response.getBody().get(0).getName(), "TestRepo");
		assertEquals(response.getBody().get(0).getOwner().getLogin(), "TestUser");
	}

	@Test
	void testUserRepoDetailsException404() {
		Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any(), Mockito.anyMap()))
				.thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND, "404"));
		assertThrows(HttpClientErrorException.class, () -> {
			gitUserController.getUserRepoDetails("test");
		});
	}

	private User getUser() {
		User user = new User();
		user.setName("TestRepo");
		user.setFork(false);
		user.setBranchesUrl("http://test.com");
		Owner owner = new Owner();
		owner.setLogin("TestUser");
		Branch branch = new Branch();
		Commit commit = new Commit();
		commit.setSha("wertyuilk5679");
		branch.setCommit(commit);
		branch.setName("Branch");
		List<Branch> branchList = new ArrayList<>();
		branchList.add(branch);
		user.setBranches(branchList);
		user.setOwner(owner);
		return user;
	}

}
