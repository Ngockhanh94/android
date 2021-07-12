package com.example.testapp;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import com.example.testapp.Controller.APIService;
import com.example.testapp.Controller.ApiUtils;
import com.example.testapp.Model.Report.CreateViolationReportModel;
import com.example.testapp.Model.Report.RealPathUtil;
import com.example.testapp.Model.Report.ViolationReport;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;


import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class report extends AppCompatActivity implements LocationListener {

    public static final String TAG = report.class.getName();
    private static final String KEY_SEND_DATA_TOKEN = "KEY_SEND_DATA_TOKEN";
    private static final String KEY_SEND_DATA_USER_ID = "KEY_SEND_DATA_USER_ID";
    private String TOKEN,USER_ID;
    private APIService mAPIService;

    protected LocationManager locationManager;
    private ProgressDialog progressDialog;

    EditText txtTile_report,txtContent,txtTime_report,txtLocation_report,txtAddress,txtImage_report;
    CheckBox checkBox;
    Button btChose_image,btReport;
    ImageView imageView;
    private static final int My_requet = 10;
    private Uri mUri;

   /* private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.e(TAG,"onActivityResult");
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        if(data == null){
                            return;
                        }
                        Uri uri = data.getData();
                        mUri = uri;
                        try{
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                            imageView.setImageBitmap(bitmap);
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }

                }
            });


    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressDialog = new ProgressDialog(report.this);
        progressDialog.setMessage("Vui lòng chờ...");
        mAPIService = ApiUtils.getAPIService();

        txtTile_report = findViewById(R.id.txttitel_report);
        txtTime_report = findViewById(R.id.txtTime_report);
        txtContent = findViewById(R.id.txtContent);
        txtLocation_report = findViewById(R.id.txtLocation_report);
        txtAddress = findViewById(R.id.txtAddress);
        txtImage_report = findViewById(R.id.txtImage_report);
        btChose_image = findViewById(R.id.btselectimage);
        btReport = findViewById(R.id.btreport);
        imageView = findViewById(R.id.image_report);
        checkBox = findViewById(R.id.checkBox);
        Intent intent = getIntent();
        TOKEN = intent.getStringExtra(KEY_SEND_DATA_TOKEN);
        USER_ID = intent.getStringExtra(KEY_SEND_DATA_USER_ID);
        LocalDateTime ndate = LocalDateTime.now();
        txtTime_report.setText(String.valueOf(ndate));


        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

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
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);

        btChose_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRequestPermission();

            }
        });
        btReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkreport()){
                    new AlertDialog.Builder(report.this)
                            .setTitle("Xác nhận!")
                            .setMessage("Tất cả thông tin đều đúng")
                            .setNegativeButton(android.R.string.cancel, null)
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface arg0, int arg1) {
                                    progressDialog.show();
                                    LocalDateTime ndate = LocalDateTime.now();
                                    String Reposter = USER_ID;
                                    String Title = String.valueOf(txtTile_report.getText());
                                    String Content = String.valueOf(txtContent.getText());
                                    String Location = String.valueOf(txtLocation_report.getText());
                                    String Address = String.valueOf(txtAddress.getText());
                                    String ReportDate = ndate.toString();
                                    String strReadPath = RealPathUtil.getRealPath(report.this,mUri);
                                    File file = new File(strReadPath);
                                    String fileup = file.getName();
                                    CreateViolationReportModel createViolationReportModel = new CreateViolationReportModel(Reposter,Title,Content,Location,Address,ReportDate,fileup);
                                    CreateViolationReport(TOKEN,createViolationReportModel);
/*
                                    RequestBody Reposterpost =RequestBody.create(MediaType.parse("multipart/from-data"),Reposter);
                                    RequestBody Titlepost =RequestBody.create(MediaType.parse("multipart/from-data"),Title);
                                    RequestBody Contentpost =RequestBody.create(MediaType.parse("multipart/from-data"),Content);
                                    RequestBody Locationpost =RequestBody.create(MediaType.parse("multipart/from-data"),Location);
                                    RequestBody Addresspost =RequestBody.create(MediaType.parse("multipart/from-data"),Address);
                                    RequestBody ReportDatepost =RequestBody.create(MediaType.parse("multipart/from-data"),ReportDate);

                                    String strReadPath = RealPathUtil.getRealPath(report.this,mUri);
                                    File FileUpload = new File(strReadPath);
                                    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/from-data"),FileUpload);
                                    MultipartBody.Part part = MultipartBody.Part.createFormData("FileUpload",FileUpload.getName(),requestBody);
                                    String token;
                                    token = "Bearer " + TOKEN;
                                    Long tsLong = System.currentTimeMillis()/1000;
                                    String fileName = tsLong.toString() + "-" + FileUpload.getName();
                                    CreateViolationReportModel model1 = new CreateViolationReportModel(Reposter,Title,Content,Location,Address,ReportDate,FileUpload);

                                    MultipartBody body = new MultipartBody.Builder().addFormDataPart("Reposter",USER_ID)
                                            .addFormDataPart("Title",String.valueOf(txtTile_report.getText()))
                                            .addFormDataPart("Content",String.valueOf(txtContent.getText()))
                                            .addFormDataPart("Location",String.valueOf(txtLocation_report.getText()))
                                            .addFormDataPart("Address",String.valueOf(txtAddress.getText()))
                                            .addFormDataPart("ReportDate",ndate.toString())
                                            .addFormDataPart("FileUpload",fileName,requestBody).build();
                                    */
/*
                   mAPIService.CreateViolationReport("multipart/from-data; boundary=", ,token
                            ,Reposterpost,Titlepost,Contentpost,Locationpost
                            ,Addresspost,ReportDatepost,part)
                            .enqueue(new Callback<ViolationReport>() {
                        @Override
                        public void onResponse(Call<ViolationReport> call, Response<ViolationReport> response) {
                            progressDialog.dismiss();
                            Toast.makeText(report.this,response.body().getMessage(),Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void onFailure(Call<ViolationReport> call, Throwable t) {
                            progressDialog.dismiss();
                            txtAddress.setText("false rồi");
                            Toast.makeText(report.this, "Vui lòng kiểm tra lại kết nối internet rồi thử lại", Toast.LENGTH_SHORT).show();
                        }
                    });

 */

 /*                                   try{
                                        mAPIService.CreateViolationReport(body.contentType(),String.valueOf(body.contentLength()),token,body).enqueue(new Callback<ViolationReport>() {
                                            @Override
                                            public void onResponse(Call<ViolationReport> call, Response<ViolationReport> response) {
                                                progressDialog.dismiss();
                                                new AlertDialog.Builder(report.this)
                                                        .setTitle("Báo cáo thành công!")
                                                        .setMessage("Bạn có muốn tiếp tục báo cáo khác?")
                                                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                onBackPressed();
                                                            }
                                                        })
                                                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                                                            public void onClick(DialogInterface arg0, int arg1) {
                                                                Intent intent = new Intent(report.this,report.class);
                                                                intent.putExtra(KEY_SEND_DATA_TOKEN,TOKEN);
                                                                intent.putExtra(KEY_SEND_DATA_USER_ID, USER_ID);
                                                                startActivity(intent);
                                                            }
                                                        }).create().show();
                                            }
                                            @Override
                                            public void onFailure(Call<ViolationReport> call, Throwable t) {
                                                progressDialog.dismiss();
                                            }
                                        });
                                    }catch (Exception e){
                                        Toast.makeText(getApplicationContext(), "Lỗi quần què gì đây: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

  */
                   /* new AsyncTask<Integer, Void, Void>(){
                        @Override
                        protected Void doInBackground(Integer... integers) {
                            try {
                                Channel channel = connectFTP();
                                String strReadPath = RealPathUtil.getRealPath(report.this,mUri);
                                File FileUpload = new File(strReadPath);
                                ChannelSftp sftp = (ChannelSftp) channel;
                                sftp.cd(strReadPath);
                                sftp.put(strReadPath, "/Webservice/wwwroot/Images/ReportImage");
                                progressDialog.dismiss();
                            } catch (JSchException | SftpException e) {
                                e.printStackTrace();
                            }
                            return null;
                        }
                    }.execute(1);

                    */
                                }
                            }).create().show();

                }
            }
        });
    }

    public Channel connectFTP() throws JSchException {
        String user = "huongpn";
        String password = "76210119";
        String host = "104.154.118.39";
        int port = 21;

        JSch ssh = new JSch();
        Session session = ssh.getSession(user, host, port);
        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.setPassword(password);
        session.connect();
        Channel channel = session.openChannel("sftp");
        channel.connect();
        return channel;
    }

    @Override
    public void onLocationChanged(Location location) {
        double a =  location.getLatitude();
        double b =  location.getLongitude();
        txtLocation_report.setText(String.valueOf(a) +"," + String.valueOf(b));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }

    private void onClickRequestPermission() {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            openGallery();
            return;
        }
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
            openGallery();
        }else {
            String [] permision = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permision,My_requet);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == My_requet){
            if(grantResults.length>0 && grantResults[0] ==PackageManager.PERMISSION_GRANTED){
            openGallery();
            }
        }
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
       // mActivityResultLauncher.launch(Intent.createChooser(intent,"Select Picture"));
        startActivityForResult(Intent.createChooser(intent,
               "Select Picture"
               ),2);



    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(report.this, IndexActivity.class);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            imageView.setImageURI(data.getData());
            Uri uri = data.getData();
            mUri = uri;
            String strReadPath = RealPathUtil.getRealPath(report.this, mUri);
            File file = new File(strReadPath);
            txtImage_report.setText(file.getName());
    }

    private void CreateViolationReport(String token, CreateViolationReportModel model){
        try {
            progressDialog.show();
            token = "Bearer " + token;
            String contentLength = String.valueOf(model.toString().getBytes(StandardCharsets.US_ASCII).length);
            mAPIService.CreateViolationReport(contentLength,token, model).enqueue(new Callback<ViolationReport>() {
                @Override
                public void onResponse(Call<ViolationReport> call, Response<ViolationReport> response) {
                    try {
                        if (response.body().getIsSuccess()) {
                            progressDialog.dismiss();
                            new AlertDialog.Builder(report.this)
                                    .setTitle("Báo cáo thành công!")
                                    .setMessage("Bạn có muốn tiếp tục báo cáo khác?")
                                    .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            onBackPressed();
                                        }
                                    })
                                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                                        public void onClick(DialogInterface arg0, int arg1) {
                                            Intent intent = new Intent(report.this,report.class);
                                            intent.putExtra(KEY_SEND_DATA_TOKEN,TOKEN);
                                            intent.putExtra(KEY_SEND_DATA_USER_ID, USER_ID);
                                            startActivity(intent);
                                        }
                                    }).create().show();
                        }
                    }catch (Exception e){
                        Toast.makeText(report.this,"Vui lòng kiểm tra lại kết nối internet rồi thử lại ",Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(Call<ViolationReport> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(report.this, "Vui lòng kiểm tra lại kết nối internet rồi thử lại", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    private boolean checkreport(){
        if(txtContent.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Nội dung không được để trống", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(txtTile_report.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Tiêu đề không được để trống", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(txtTime_report.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Thời gian không được để trống", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(txtAddress.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Địa chỉ không được để trống", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(txtLocation_report.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Vị trí không được để trống", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(txtImage_report.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Ảnh không được để trống", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(checkBox.isChecked() == false){
            Toast.makeText(getApplicationContext(),"Vui lòng đồng ý với cam kết", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}


