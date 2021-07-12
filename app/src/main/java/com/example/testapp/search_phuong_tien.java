package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testapp.Controller.APIService;
import com.example.testapp.Controller.ApiUtils;
import com.example.testapp.Model.VehicleProfilesModel.VehicleProfileRequestModel;
import com.example.testapp.Model.VehicleProfilesModel.VehicleProfiles;

import java.nio.charset.StandardCharsets;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class search_phuong_tien extends AppCompatActivity {
    EditText edtSearch;
    TextView txtChu_xe,txtBien_so,txtModel,txtChassis,txtEngine,txtRegistrationDate,txtColor;
    Button btsearch;
    private APIService mAPIService;
    private static String KEY_SEND_DATA_TOKEN = "KEY_SEND_DATA_TOKEN";
    private static String KEY_SEND_DATA_USER_ID = "KEY_SEND_DATA_USER_ID";
    private static String TOKEN;
    private static String USER_ID;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_phuong_tien);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAPIService = ApiUtils.getAPIService();

        Intent intent = getIntent();
        TOKEN = intent.getStringExtra(KEY_SEND_DATA_TOKEN);
        USER_ID = intent.getStringExtra(KEY_SEND_DATA_USER_ID);

        edtSearch = findViewById(R.id.txtSearch);
        txtChu_xe = findViewById(R.id.txtHo_ten);
        txtBien_so = findViewById(R.id.txtNgay_sinh);
        txtModel = findViewById(R.id.txtGioi_tinh);
        txtChassis = findViewById(R.id.txtpermanentResidence);
        txtEngine = findViewById(R.id.txthometown);
        txtRegistrationDate = findViewById(R.id.txtjob);
        txtColor = findViewById(R.id.txtethnic);

        btsearch = findViewById(R.id.bt_search_user);
        btsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtSearch.getText().toString() != null) {
                    VehicleProfileRequestModel vehicleProfileRequestModel = new VehicleProfileRequestModel(" ", edtSearch.getText().toString());
                    GetVehicleProfile(TOKEN, vehicleProfileRequestModel);
                }else {
                    Toast.makeText(search_phuong_tien.this,"Vui lòng nhập biển số xe",Toast.LENGTH_LONG).show();
                }
            }
        });




    }

    private void GetVehicleProfile(String token, VehicleProfileRequestModel model) {
        try {
            token = "Bearer " + token;
            String contentLength = String.valueOf(model.toString().getBytes(StandardCharsets.US_ASCII).length);
            mAPIService.GetVehicleProfile(contentLength, token, model).enqueue(new Callback<VehicleProfiles>() {
                @Override
                public void onResponse(Call<VehicleProfiles> call, Response<VehicleProfiles> response) {

                    if(response.isSuccessful()) {
                        if(response.body().getIsSuccess()){
                            txtChu_xe.setText(response.body().getData().getOwner().getFirstName() +
                                    " " + response.body().getData().getOwner().getLastName());
                            txtBien_so.setText(response.body().getData().getPlate());
                            txtChassis.setText(response.body().getData().getChassis());
                            txtEngine.setText(response.body().getData().getEngine());
                            txtModel.setText(response.body().getData().getModelCode());
                            txtRegistrationDate.setText(response.body().getData().getRegistrationDate());
                            txtColor.setText(response.body().getData().getColor());
                        }else{
                            Toast.makeText(getApplicationContext(),"Không tìm thấy phương tiện nào", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                @Override
                public void onFailure(Call<VehicleProfiles> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"Vui lòng kiểm tra lại kết nối internet rồi thử lại", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(search_phuong_tien.this, IndexActivity.class);
        intent.putExtra(KEY_SEND_DATA_TOKEN,TOKEN);
        intent.putExtra(KEY_SEND_DATA_USER_ID, USER_ID);
        startActivity(intent);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }

        return true;
    }

}
