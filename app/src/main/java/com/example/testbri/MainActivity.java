package com.example.testbri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testbri.API.APIInterfacesRest;
import com.example.testbri.API.REST_Controller;
import com.example.testbri.API.Request.RequestDaftar;
import com.example.testbri.API.Respon.ApiBasic;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText edName,edAdrress,edEmail;
    Button btnSave,btnNext;
    RequestDaftar req = new RequestDaftar();
    String alamat,nama,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edName = findViewById(R.id.edName);
        edAdrress = findViewById(R.id.edAddres);
        edEmail = findViewById(R.id.edEmail);
        btnSave = findViewById(R.id.btnSave);
        btnNext = findViewById(R.id.btnNext);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validasi();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, StepTwo.class);
                startActivity(intent);
            }
        });
    }

    private Boolean  validasi(){

        Boolean validasi = true;

         nama = edName.getText().toString();
         alamat = edAdrress.getText().toString();
         email = edEmail.getText().toString();

        if(nama.isEmpty()){
            edName.setError("Mohon Isi Nama");
        }else {
            if(nama.length() >= 10){
                edName.setError("Nama Max 10 Karakter");
            }
        }

        if(alamat.isEmpty()){
            edAdrress.setError("Mohon Isi Alamat");
        }else {
            if(alamat.length() >= 30){
                edAdrress.setError("Address Max 30 Karakter");
            }
        }

       if(email.isEmpty()){
           edEmail.setError("Mohon Isi Email");
       }else {
           if(!isValidEmail(edEmail.getText().toString())){
               edEmail.setError("Format Email Salah");
           }
       }

       if(!email.isEmpty() && !nama.isEmpty() && !alamat.isEmpty()){
           Register();
       }




       return validasi;

    }

    APIInterfacesRest apiInterface;
    private  void Register(){
        apiInterface = REST_Controller.getClient().create(APIInterfacesRest.class);
        req.setName(nama);
        req.setAddress(alamat);
        req.setEmail(email);
        Call<ApiBasic> call3 = apiInterface.postNewRegis(req);
        call3.enqueue(new Callback<ApiBasic>() {
            @Override
            public void onResponse(Call<ApiBasic> call, Response<ApiBasic> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess() == true){

                        Intent in = new Intent(MainActivity.this, StepTwo.class);
                        startActivity(in);


                    }else {
                        Toast.makeText(MainActivity.this, "GAGAL",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiBasic> call, Throwable t) {
                Toast.makeText(MainActivity.this, "PERIKSA KONEKSI ANDA",Toast.LENGTH_SHORT).show();
            }
        });



    }

    public static boolean isValidEmail(String email) {
        boolean validate;
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String emailPattern2 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+";

        if (email.matches(emailPattern)) {
            validate = true;
        } else if (email.matches(emailPattern2)) {
            validate = true;
        } else {
            validate = false;
        }

        return validate;
    }

}