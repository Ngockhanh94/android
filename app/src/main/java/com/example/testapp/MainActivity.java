package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.testapp.Controller.APIService;
import com.example.testapp.Controller.ApiUtils;
import com.example.testapp.Model.AccountInfoModel;
import com.example.testapp.Model.LoginModel.LoginRequestModel;
import com.example.testapp.Model.LoginModel.LoginResponseModel;

import java.nio.charset.StandardCharsets;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private APIService mAPIService;
    private EditText tb_username;
    private EditText tb_password;
    private Button bt_login;
    private LinearLayout loginLayout;
    private LoginResponseModel userResponse;
    private AccountInfoModel accountInfoModel;
    private static String KEY_SEND_DATA_TOKEN = "KEY_SEND_DATA_TOKEN";
    private static String KEY_SEND_DATA_USER_ID = "KEY_SEND_DATA_USER_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tb_username = (EditText) findViewById(R.id.tb_user_name);
        tb_password = (EditText) findViewById(R.id.tb_passwd);
        bt_login = (Button) findViewById(R.id.bt_login);
        loginLayout = (LinearLayout) findViewById(R.id.login_layout);
        mAPIService = ApiUtils.getAPIService();

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkLoginInput()){
                    LoginRequestModel model = new LoginRequestModel(tb_username.getText().toString().trim(), tb_password.getText().toString().trim());
                    login(model);
                }
            }
        });
    }

    private boolean checkLoginInput(){
        if(tb_username.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Tên đăng nhập không được để trống", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(tb_password.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void login(LoginRequestModel model) {
        String contentLength = String.valueOf(model.toString().getBytes(StandardCharsets.US_ASCII).length);
        mAPIService.loginCallAPI(contentLength, model).enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {

                if(response.isSuccessful()) {
                    if(response.body().getIsSuccess()){
                        userResponse = response.body();
                        Intent intent = new Intent(MainActivity.this, IndexActivity.class);
                        intent.putExtra(KEY_SEND_DATA_TOKEN, userResponse.getToken());
                        intent.putExtra(KEY_SEND_DATA_USER_ID, userResponse.getId());
                        startActivity(intent);

                    }else{
                        Toast.makeText(getApplicationContext(),"Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Vui lòng kiểm tra lại kết nối internet rồi thử lại", Toast.LENGTH_SHORT).show();
            }
        });
    }
}