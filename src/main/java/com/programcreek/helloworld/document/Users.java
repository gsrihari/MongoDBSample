package com.programcreek.helloworld.document;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@XmlRootElement(name = "Users")
@Document(collection="users")
public class Users {

	 @Id
	 private String id;
	 	 
	 private String firstname;
	 
	 private String lastname;
	 
	 @Transient
	 private String message;
	 
	 public Users() {}
	 
	 public Users(String firstName, String lastName) {
		 this.firstname = firstName;
	     this.lastname = lastName;
	 }

	public String getId() {
		return id;
	}

	@XmlElement
	public void setId(String id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}
	
	@XmlElement
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	@XmlElement
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMessage() {
		return message;
	}
	
	@XmlElement
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
