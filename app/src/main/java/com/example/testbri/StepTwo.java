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
import com.example.testbri.API.Request.RequestDaftarTwo;
import com.example.testbri.API.Respon.ApiBasic;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StepTwo extends AppCompatActivity {
    EditText edNID,edOffice;
    Button btnSave;
    String NID,Office;
    RequestDaftarTwo req = new RequestDaftarTwo();
    int nid,officeId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_two);

        edNID = findViewById(R.id.edNID);
        edOffice = findViewById(R.id.edOffice);
        btnSave = findViewById(R.id.btnSave);



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });

    }

    APIInterfacesRest apiInterface;
    private  void Register(){
        apiInterface = REST_Controller.getClient().create(APIInterfacesRest.class);
        NID = edNID.getText().toString();
        Office = edOffice.getText().toString();
        if(NID.isEmpty()){
            edNID.setError("Masukan NID");
        }else {
            nid = Integer.parseInt(NID);
            req.setNid(nid);
        }
        if(Office.isEmpty()){
            edOffice.setError("Masukan Office ID");
        }else {
            officeId = Integer.parseInt(Office);
            req.setOfficeId(officeId);
        }



        apiInterface.postNewRegisTow(req).enqueue(new Callback<ApiBasic>() {
            @Override
            public void onResponse(Call<ApiBasic> call, Response<ApiBasic> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess() == true){

                        Intent i = new Intent(StepTwo.this, FinishActivity.class);
                        startActivity(i);


                    }else {
                        Toast.makeText(StepTwo.this, "GAGAL",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiBasic> call, Throwable t) {
                Toast.makeText(StepTwo.this, "PERIKSA KONEKSI ANDA",Toast.LENGTH_SHORT).show();
            }
        });



    }
}