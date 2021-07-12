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
import com.example.testapp.Model.GetByIdCardModel.GetByIdCardModel;
import com.example.testapp.Model.GetByIdCardModel.UserResponse;
import com.example.testapp.Model.GetByIdCardModel.Userinfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class search_user extends AppCompatActivity {
    EditText edtSearch;
    TextView txtHoten, txtNgaysinh, txtGioitinh, txtpermanentResidence, txthometown, txtjob, txtethnic;
    Button btsearch;
    private static String KEY_SEND_DATA_TOKEN = "KEY_SEND_DATA_TOKEN";
    private static String KEY_SEND_DATA_USER_ID = "KEY_SEND_DATA_USER_ID";
    private static String TOKEN, ID;
    private APIService mAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_user);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        TOKEN = intent.getStringExtra(KEY_SEND_DATA_TOKEN);
        ID = intent.getStringExtra(KEY_SEND_DATA_USER_ID);
        mAPIService = ApiUtils.getAPIService();

        edtSearch = findViewById(R.id.txtSearch);
        txtHoten = findViewById(R.id.txtHo_ten);
        txtGioitinh = findViewById(R.id.txtGioi_tinh);
        txtNgaysinh = findViewById(R.id.txtNgay_sinh);
        txtpermanentResidence = findViewById(R.id.txtpermanentResidence);
        txthometown = findViewById(R.id.txthometown);
        txtjob = findViewById(R.id.txtjob);
        txtethnic = findViewById(R.id.txtethnic);
        btsearch = findViewById(R.id.bt_search_user);
        btsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetByIdcard(TOKEN, edtSearch.getText().toString());
            }
        });


    }

    private void GetByIdcard(String token, String citizenIdCard) {
        try {
            token = "Bearer " + token;
            mAPIService.GetByIdcard(token, citizenIdCard).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                       if (response.isSuccessful()) {

                          txtHoten.setText(response.body().getData().getFirstName() + " " + response.body().getData().getLastName());
                           if (response.body().getData().getSex()) {
                               txtGioitinh.setText("Nam");
                           } else {
                               txtGioitinh.setText("Nữ");
                           }
                           txtNgaysinh.setText(response.body().getData().getDateOfBirth());
                           txtpermanentResidence.setText(response.body().getData().getPermanentResidence().getiStreesNamed() +
                                   "," + response.body().getData().getPermanentResidence().getWardName() + "," + response.body().getData().getPermanentResidence().getDistrictName()
                                   + "," + response.body().getData().getPermanentResidence().getProvinceName());
                           txtethnic.setText(response.body().getData().getEthnic());
                           txtjob.setText(response.body().getData().getJob());
                           txthometown.setText(response.body().getData().getHometown().getiStreesNamed() +
                                   "," + response.body().getData().getHometown().getWardName() + "," + response.body().getData().getHometown().getDistrictName()
                                   + "," + response.body().getData().getHometown().getProvinceName());

                       }else{
                           Toast.makeText(search_user.this,"Vui lòng kiểm tra lại số cmnd",Toast.LENGTH_LONG).show();
                       }

                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Toast.makeText(search_user.this, "Vui lòng kiểm tra lại kết nối internet rồi thử lại", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(search_user.this, IndexActivity.class);
        intent.putExtra(KEY_SEND_DATA_TOKEN, TOKEN);
        intent.putExtra(KEY_SEND_DATA_USER_ID, ID);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                break;
        }
        return true;
    }

   /* private void vitri() {

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location lastLocation = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        if (lastLocation != null) {
            LatLng latLng = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());


        }
    }

    */
}