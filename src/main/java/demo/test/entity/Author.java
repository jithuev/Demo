package demo.test.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

import org.springframework.stereotype.Component;

@Component
@Embeddable
public class Author {
	@Column(name="Author_Name")
	String name;
	@Column(name="Author_Email")
	String email;
	@Column(name="Author_Date")
	String date;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}	
