package com.example.jrtest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "company", name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long id;

	@Column(name = "name")
	public String name;

	@Column(name = "age")
	public int age;

	@Column(name = "address")
	public String address;

	@Column(name = "salary")
	public int salary;

	@Override
	public String toString() {
		return String.format("{\"id\" : %d, \"name\" : \"%s\", \"age\" : %d, \"address\" : \"%s\", \"salary\" : %d}", id, name, age, address, salary);
	}
}
