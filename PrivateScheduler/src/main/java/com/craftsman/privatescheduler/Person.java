package com.craftsman.privatescheduler;

public class Person {

	private String name;
	private String phoneNumber;
	private String email;
	private String kakaoId;

	Person(String name,String phoneNumber,String email, String kakaoId){
		this(name, phoneNumber, email);
		this.kakaoId = kakaoId;
	}

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

	public String getKakaoId() {
		return kakaoId;
	}
}
