package io.playground.javalin.data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Repos implements Serializable{
	@Id @GeneratedValue
    private long id;
	
	private String name, link;
	public Repos() {}
	public Repos(String name, String link) {
		super();
		this.name = name;
		this.link = link;
	}
	public String getName() {
		return name;
	}
	public String getLink() {
		return link;
	}
	@Override
	public String toString() {
		return "Repos: name=" + name + ", link= <a href=\"" + link + "\">" +link +"</a>";
	}
	
}
