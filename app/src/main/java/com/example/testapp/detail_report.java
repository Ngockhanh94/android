package com.example.testapp;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.testapp.Controller.APIService;
import com.example.testapp.Controller.ApiUtils;
import com.example.testapp.Model.HandlingViolations.HandlingViolationsModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class detail_report extends AppCompatActivity implements OnMapReadyCallback {
    private String id;
    private String title;
    private String plate;
    private String registrationDate;
    private String location;
    private String image;
    private boolean isDealt;
    private static String KEY_SEND_DATA_TOKEN = "KEY_SEND_DATA_TOKEN";
    private static String KEY_SEND_DATA_USER_ID = "KEY_SEND_DATA_USER_ID";
    private String TOKEN,USER_ID;

    Button btxuly;
    ImageView imageView;
    TextView txtTitle,txtPlate,txtRegistrationDate,txtIsdealt;
    private GoogleMap mMap;
    String [] map;
    private static String LINK_VIOLATION_HISTORY_IMAGE = "http://104.154.118.39/Images/ViolationHistoryImage/";
    private APIService mAPIService;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_report);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        btxuly = findViewById(R.id.btXuly);
        id = intent.getStringExtra("id");
        title = intent.getStringExtra("title");
        plate = intent.getStringExtra("plate");
        registrationDate = intent.getStringExtra("registrationDate");
        location = intent.getStringExtra("location");
        image = intent.getStringExtra("image");
        isDealt = intent.getBooleanExtra("isDealt", true);

        mAPIService = ApiUtils.getAPIService();

        TOKEN = intent.getStringExtra(KEY_SEND_DATA_TOKEN);
        USER_ID = intent.getStringExtra("user_id");

        map = location.split(",");
        txtTitle = (TextView) findViewById(R.id.txtName);
        txtTitle.setText(title);
        txtPlate = findViewById(R.id.txtplate);
        txtPlate.setText(plate);
        txtRegistrationDate = findViewById(R.id.txtSex);
        txtRegistrationDate.setText(registrationDate);
        txtIsdealt = findViewById(R.id.txtroleName);
        if(isDealt){
            txtIsdealt.setText("Đã xử lý");
            txtIsdealt.setTextColor(ContextCompat.getColor(detail_report.this, R.color.cl_da_xu_ly));
            btxuly.setVisibility(View.GONE);
        }else {
            txtIsdealt.setText("Chưa xử lý");
            txtIsdealt.setTextColor(ContextCompat.getColor(detail_report.this, R.color.cl_chua_xu_ly));
            btxuly.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    new AlertDialog.Builder(detail_report.this)
                            .setTitle("XÁC NHẬN")
                            .setMessage("Bạn có chắc chắn xác nhận xử lý")
                            .setNegativeButton(android.R.string.cancel, null)
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface arg0, int arg1) {
                                    GetHandlingViolations(TOKEN, id);
                                    txtIsdealt.setText("Đã xử lý");
                                    txtIsdealt.setTextColor(ContextCompat.getColor(detail_report.this, R.color.cl_da_xu_ly));
                                    btxuly.setVisibility(View.GONE);
                                }
                            }).create().show();

                }
            });
        }
        imageView = findViewById(R.id.image_user);
        Picasso.with(detail_report.this).load(LINK_VIOLATION_HISTORY_IMAGE +image ).into(imageView);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragmet);
        supportMapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady( GoogleMap googleMap) {
        mMap = googleMap;
        LatLng vipham = new LatLng(Double.parseDouble(map[0]), Double.parseDouble(map[1]));
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.addMarker(new MarkerOptions()
                .position(vipham)
                .title("Vị trí vi phạm"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(vipham,15));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(15));
        mMap.setTrafficEnabled(true);


    }
    private void GetHandlingViolations(String token, String violationId) {
        try {
            token = "Bearer " + token;
            mAPIService.GetHandlingViolations(token, violationId).enqueue(new Callback<HandlingViolationsModel>() {
                @Override
                public void onResponse(Call<HandlingViolationsModel> call, Response<HandlingViolationsModel> response) {

                    if(response.isSuccessful()) {
                        if(response.body().getIsSuccess()){
                            Toast.makeText(getApplicationContext(), response.body().getMessage().toString() ,Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(),response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                @Override
                public void onFailure(Call<HandlingViolationsModel> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"Vui lòng kiểm tra lại kết nối internet rồi thử lại", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(detail_report.this, IndexActivity.class);
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
