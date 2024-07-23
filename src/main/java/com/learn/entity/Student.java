package com.learn.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//hibernate persistent class
@Entity
@Table(name = "Student")
public class Student {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "firstname" ,nullable = false)
	private String firstname;
	
	@Column(name = "lastname" ,nullable = false)
	private String lastname;
	
	public Student() {
		
	}
	
	public Student(String firstName, String lastName) {
        this.firstname = firstName;
        this.lastname = lastName;
    }
	
		
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
    public String toString() {
        return "Student [id=" + id + ", firstName=" + firstname + ", lastName=" + lastname + "]";
    }
}
