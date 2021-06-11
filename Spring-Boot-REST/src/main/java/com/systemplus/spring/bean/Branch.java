package com.systemplus.spring.bean;

import com.fasterxml.jackson.annotation.JsonAlias;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Branch {

	@JsonAlias("branchName")
	@ApiModelProperty(value = "Branch name", example = "gitBranch")
	private String name;

	private Commit commit;

	@JsonAlias("branchName")
	public String getName() {
		return name;
	}

	@JsonAlias("name")
	public void setName(String name) {
		this.name = name;
	}

	public Commit getCommit() {
		return commit;
	}

	public void setCommit(Commit commit) {
		this.commit = commit;
	}

}
