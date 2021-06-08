package com.systemplus.spring.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class User {

	@ApiModelProperty(value = "repository name",name="repositoryName", example = "repo name")
	@JsonProperty("repositoryName")
	private String name;

	@ApiModelProperty(value = "owner", example = "owner")
	private Owner owner;

	@ApiModelProperty(value = "branchesUrl", example = "branchesUrl")
	private String branchesUrl;

	@JsonIgnore
	@ApiModelProperty(value = "fork", example = "true")
	private boolean fork;

	private List<Branch> branches;

	public List<Branch> getBranches() {
		return branches;
	}

	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}

	@JsonProperty("repositoryName")
	@ApiModelProperty(value = "repository name",name="repositoryName", example = "repo name")
	public String getName() {
		return name;
	}

	//@ApiModelProperty(value = "repository name",name="name", example = "repo name")
	//@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	@JsonProperty("branchesUrl")
	public String getBranchesUrl() {
		return branchesUrl;
	}

	// @JsonProperty("branches_url")
	public void setBranchesUrl(String branchesUrl) {
		this.branchesUrl = branchesUrl;
	}

	public boolean isFork() {
		return fork;
	}

	public void setFork(boolean fork) {
		this.fork = fork;
	}

}
