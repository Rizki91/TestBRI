package com.example.testbri.API.Request;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class RequestDaftarTwo{

	@SerializedName("officeId")
	private int officeId;

	@SerializedName("nid")
	private int nid;

	public void setOfficeId(int officeId) {
		this.officeId = officeId;
	}

	public void setNid(int nid) {
		this.nid = nid;
	}

	public int getOfficeId(){
		return officeId;
	}

	public int getNid(){
		return nid;
	}
}