package com.systemplus.spring.bean;

import io.swagger.annotations.ApiModelProperty;

public class Commit {

	@ApiModelProperty(value="sha info", example="f37c3954ce81726fab760fc8a5d333bf852eca41")
	private String sha;

	public String getSha() {
		return sha;
	}

	public void setSha(String sha) {
		this.sha = sha;
	}

}
