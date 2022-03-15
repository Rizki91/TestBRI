package com.example.testbri.API.Respon;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class ApiBasic{

	@SerializedName("success")
	private boolean success;

	public boolean isSuccess(){
		return success;
	}
}