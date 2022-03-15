package com.example.testbri.API.Request;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class RequestDaftar{

	@SerializedName("“email”")
	private String email;

	@SerializedName("address")
	private String address;

	@SerializedName("name")
	private String name;

	public String getEmail(){
		return email;
	}

	public String getAddress(){
		return address;
	}

	public String getName(){
		return name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setName(String name) {
		this.name = name;
	}
}