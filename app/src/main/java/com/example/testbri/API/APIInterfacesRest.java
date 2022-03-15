package com.example.testbri.API;

import com.example.testbri.API.Request.RequestDaftar;
import com.example.testbri.API.Request.RequestDaftarTwo;
import com.example.testbri.API.Respon.ApiBasic;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIInterfacesRest {

    @POST("kx0XKx9k9ktBjzggWRej")
    Call<ApiBasic> postNewRegis( @Body RequestDaftar regist);

    @POST("kx0XKx9k9ktBjzggWRej")
    Call<ApiBasic> postNewRegisTow(@Body RequestDaftarTwo regist);
}
