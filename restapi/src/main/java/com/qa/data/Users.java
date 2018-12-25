package com.qa.data;


// POJO -- Plain Old Java Object
public class Users {

	String name;
	String job;
	
	public Users() {
		
	}
	
	public Users(String name,String job) {
		this.name = name;
		this.job = job;
	}

	// Getters and Setters methods
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	
	
	
	
}
