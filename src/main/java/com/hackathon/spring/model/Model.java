package com.hackathon.spring.model;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="USERS")
public class Model {
	@Id
	private long id;
	private String uname;
	private int age;
	private String email;
	public long getId() {
		return id;
	}
	
	public Model() {
		id=0;
	}
	
	public Model(long id, String uname, int age, String email) {
		super();
		this.id = id;
		this.uname = uname;
		this.age = age;
		this.email = email;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
