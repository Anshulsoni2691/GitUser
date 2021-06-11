package com.systemplus.spring.bean;

import com.fasterxml.jackson.annotation.JsonAlias;

import io.swagger.annotations.ApiModelProperty;

public class Owner {

	@JsonAlias("ownerLogin")
	@ApiModelProperty(value = "Owner login info", example = "user")
	private String login;

	@JsonAlias("ownerLogin")
	public String getLogin() {
		return login;
	}

	@JsonAlias("login")
	public void setLogin(String login) {
		this.login = login;
	}

}
