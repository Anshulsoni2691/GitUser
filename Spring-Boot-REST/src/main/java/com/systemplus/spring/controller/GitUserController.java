package com.systemplus.spring.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.systemplus.spring.bean.Branch;
import com.systemplus.spring.bean.User;
import com.systemplus.spring.exception.handler.ErrorResponse;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/getUsers")
public class GitUserController {

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/repository", headers = "Accept=application/json", method = RequestMethod.GET, produces = "application/json")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesful data received"),
			@ApiResponse(code = 404, message = "No such user found"),
			@ApiResponse(code = 406, message = "Accept only application/json", response = ErrorResponse.class) })
	public ResponseEntity<List<User>> getUserRepoDetails(
			@RequestParam(name = "name", required = false, defaultValue = "Unknown") String name) {

		final String uri = "https://api.github.com/users/{name}/repos";

		Map<String, String> params = new HashMap<String, String>();
		params.put("name", name);

		User[] response = restTemplate.getForObject(uri, User[].class, params);

		return new ResponseEntity<>(getBranchAndLastCommit(Arrays.asList(response)), HttpStatus.OK);
	}

	public List<User> getBranchAndLastCommit(List<User> userList) {

		List<User> notForkedRepoList = userList.stream().filter(user -> !user.isFork()).collect(Collectors.toList());

		notForkedRepoList.forEach(user -> {
			Branch[] response = restTemplate.getForObject(user.getBranchesUrl().replace("{/branch}", ""),
					Branch[].class);

			user.setBranches(Arrays.asList(response));
			user.setBranchesUrl(null);

		});

		return notForkedRepoList;
	}

}
