package com.craftsman.privatescheduler;

public class Person {

	private String name;
	private String phoneNumber;
	private String email;

	public Person(String name,String phoneNumber,String email) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

}
