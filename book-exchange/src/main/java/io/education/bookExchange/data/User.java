package io.education.bookExchange.data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue
    private long id;

    String userName, pwd, bio;

	public User() {
		super();
	}

	public long getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getPwd() {
		return pwd;
	}

	public String getBio() {
		return bio;
	}

	public User(String userName, String pwd, String bio) {
		super();
		this.userName = userName;
		this.pwd = pwd;
		this.bio = bio;
	}
}
