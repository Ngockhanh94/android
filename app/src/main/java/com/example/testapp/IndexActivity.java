package com.example.testapp;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testapp.Controller.APIService;
import com.example.testapp.Controller.ApiUtils;
import com.example.testapp.Model.AccountInfoModel;
import com.example.testapp.Model.GetListViolationHistoriesModel.Datum;
import com.example.testapp.Model.GetListViolationHistoriesModel.ListViolationHistoriesRequestModel;
import com.example.testapp.Model.GetListViolationHistoriesModel.ListViolationHistoriesResponseModel;
import com.example.testapp.Model.VehicleProfilesModel.VehicleProfileRequestModel;
import com.example.testapp.Model.VehicleProfilesModel.VehicleProfiles;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IndexActivity extends AppCompatActivity  {
    private static String KEY_SEND_DATA_TOKEN = "KEY_SEND_DATA_TOKEN";
    private static String KEY_SEND_DATA_USER_ID = "KEY_SEND_DATA_USER_ID";
    private static String TOKEN;
    private static String USER_ID;
    private APIService mAPIService;

    private ImageView hinhAnh;
    private TextView title, plate, registrationDate, isDealt;
    private static String LINK_AVATAR = "http://104.154.118.39/Images/Avatar/";



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        mAPIService = ApiUtils.getAPIService();

        Intent intent = getIntent();
        TOKEN = intent.getStringExtra(KEY_SEND_DATA_TOKEN);
        USER_ID = intent.getStringExtra(KEY_SEND_DATA_USER_ID);

        //Lấy danh sách vi phạm trong 1 tháng gần nhất
        LocalDateTime ndate = LocalDateTime.now();
        LocalDateTime sDate = ndate.minusMonths(1);
        String startDate = sDate.toString();
        String endDate = ndate.toString();
        ListViolationHistoriesRequestModel listViolationHistoriesRequestModel = new ListViolationHistoriesRequestModel(startDate,endDate);
        GetListViolationHistories(TOKEN, listViolationHistoriesRequestModel);
        //Lấy danh sách vi phạm trong 1 tháng gần nhất



    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_app,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.search_user:
                Intent intent = new Intent(IndexActivity.this,search_user.class);
                intent.putExtra(KEY_SEND_DATA_TOKEN, TOKEN);
                intent.putExtra(KEY_SEND_DATA_USER_ID,USER_ID);
                this.startActivity(intent);
                break;
            case R.id.search_phuongtien:
                Intent intent_report = new Intent(IndexActivity.this,search_phuong_tien.class);
                intent_report.putExtra(KEY_SEND_DATA_TOKEN, TOKEN);
                intent_report.putExtra(KEY_SEND_DATA_USER_ID,USER_ID);
                this.startActivity(intent_report);
                break;
            case R.id.report:
                Intent intent_report1 = new Intent(IndexActivity.this,report.class);
                intent_report1.putExtra(KEY_SEND_DATA_TOKEN, TOKEN);
                intent_report1.putExtra(KEY_SEND_DATA_USER_ID,USER_ID);
                this.startActivity(intent_report1);
                break;
            case R.id.user:
                Intent intent_user = new Intent(IndexActivity.this,User.class);
                intent_user.putExtra(KEY_SEND_DATA_TOKEN, TOKEN);
                intent_user.putExtra(KEY_SEND_DATA_USER_ID,USER_ID);
                this.startActivity(intent_user);
                break;

        }
        return true;
    }



    private void GetListViolationHistories(String token, ListViolationHistoriesRequestModel model) {
        try {

            token = "Bearer " + token;
            String contentLength = String.valueOf(model.toString().getBytes(StandardCharsets.US_ASCII).length);
            mAPIService.GetListViolationHistories(contentLength, token, model).enqueue(new Callback<ListViolationHistoriesResponseModel>() {
                @Override
                public void onResponse(Call<ListViolationHistoriesResponseModel> call, Response<ListViolationHistoriesResponseModel> response) {

                    if(response.isSuccessful()) {
                        if(response.body().getIsSuccess()){
                            final ListView listView = (ListView) findViewById(R.id.listView);
                            listView.setAdapter(new CustomListAdapter(getApplicationContext(), response.body().getData()));

                            // When the user clicks on the ListItem
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                @Override
                                public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                                    Object o = listView.getItemAtPosition(position);
                                    Datum country = (Datum) o;
                                    Intent intent_item = new Intent(IndexActivity.this,detail_report.class);
                                    intent_item.putExtra("user_id",USER_ID);
                                    intent_item.putExtra("id",country.getId());
                                    intent_item.putExtra("title",country.getTitle());
                                    intent_item.putExtra("plate",country.getPlate());
                                    intent_item.putExtra("registrationDate",country.getRegistrationDate());
                                    intent_item.putExtra("location",country.getLocation());
                                    intent_item.putExtra("image",country.getImages());
                                    intent_item.putExtra("isDealt",country.getIsDealt());
                                    intent_item.putExtra("KEY_SEND_DATA_TOKEN",TOKEN);
                                    startActivity(intent_item);

                                }
                            });
                            //tạo xong view
                        }else{
                            Toast.makeText(getApplicationContext(),"Không tìm thấy vi phạm nào", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                @Override
                public void onFailure(Call<ListViolationHistoriesResponseModel> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"Vui lòng kiểm tra lại kết nối internet rồi thử lại", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent a = new Intent(Intent.ACTION_MAIN);
                        a.addCategory(Intent.CATEGORY_HOME);
                        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(a);
                    }
                }).create().show();


    }

}
