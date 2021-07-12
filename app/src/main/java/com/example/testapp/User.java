package com.example.testapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testapp.Controller.APIService;
import com.example.testapp.Controller.ApiUtils;
import com.example.testapp.Model.AccountInfoModel;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class User extends AppCompatActivity {
    TextView txtName,txtSex,txtidentityCardNumber,txtroleName;
    private APIService mAPIService;
    ImageView imageView;
    Button btndangxuat;
    private static String KEY_SEND_DATA_TOKEN = "KEY_SEND_DATA_TOKEN";
    private static String KEY_SEND_DATA_USER_ID = "KEY_SEND_DATA_USER_ID";
    private static String TOKEN;
    private static String USER_ID;
    private AccountInfoModel accountInfoModel;
    private static String LINK_AVATAR = "http://104.154.118.39/Images/Avatar/";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAPIService = ApiUtils.getAPIService();

        Intent intent = getIntent();
        TOKEN = intent.getStringExtra(KEY_SEND_DATA_TOKEN);
        USER_ID = intent.getStringExtra(KEY_SEND_DATA_USER_ID);

        txtName = findViewById(R.id.txtName);
        txtSex = findViewById(R.id.txtSex);
        txtidentityCardNumber = findViewById(R.id.txtidentityCardNumber);
        txtroleName = findViewById(R.id.txtroleName);
        imageView = findViewById(R.id.image_user);
        getUserInfo(TOKEN);
        btndangxuat = findViewById(R.id.btn_dangxuat);
        btndangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(User.this)
                        .setTitle("Really Exit?")
                        .setMessage("Bạn thật sự muốn đăng xuất?")
                        .setNegativeButton(android.R.string.cancel, null)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent intent1 = new Intent(User.this,MainActivity.class);
                                startActivity(intent1);
                            }
                        }).create().show();

            }
        });

    }
    private void getUserInfo(String token){
        token = "Bearer " + token;
        mAPIService.getUserInfo(token).enqueue(new Callback<AccountInfoModel>() {
            @Override
            public void onResponse(Call<AccountInfoModel> call, Response<AccountInfoModel> response) {
                accountInfoModel = response.body();
                if(!accountInfoModel.getId().isEmpty()){
                    txtName.setText(accountInfoModel.getFirstName() + " " +accountInfoModel.getLastName());
                    if(accountInfoModel.getSex()){
                        txtSex.setText("Nam");
                    }else{
                        txtSex.setText("Nữ");
                    }
                    txtidentityCardNumber.setText(accountInfoModel.getIdentityCardNumber());
                    txtroleName.setText(accountInfoModel.getRoleName());
                    Picasso.with(User.this).load(LINK_AVATAR +accountInfoModel.getAvartarPath() ).into(imageView);

                }else{
                    Toast.makeText(getApplicationContext(),"Vui lòng kiểm tra lại kết nối internet rồi thử lại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AccountInfoModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Vui lòng kiểm tra lại kết nối internet rồi thử lại", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(User.this, IndexActivity.class);
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
