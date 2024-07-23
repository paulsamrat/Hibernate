package com.learn.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name="laptop")
@Table(name = "laptop")
public class Laptop {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "brand" , nullable = false)
	private String brand;
	
	public Laptop() {
		
	}
	
	public Laptop(final String brand) {
		this.brand = brand;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}
	
	
	@Override
	public String toString() {
		return "Laptop [id=" + id + ", brand=" + brand + "]";
	}
	
	
}
