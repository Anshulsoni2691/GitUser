package com.systemplus.spring.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class Owner {

	@JsonProperty("ownerLogin")
	@ApiModelProperty(value="Owner login info", example="user")
	private String login;

	@JsonProperty("ownerLogin")
	public String getLogin() {
		return login;
	}

	@JsonProperty("login")
	public void setLogin(String login) {
		this.login = login;
	}

}
