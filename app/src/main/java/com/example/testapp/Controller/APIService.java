package com.example.testapp.Controller;

import android.content.Context;

import com.example.testapp.Model.AccountInfoModel;
import com.example.testapp.Model.GetByIdCardModel.GetByIdCardModel;
import com.example.testapp.Model.GetByIdCardModel.UserResponse;
import com.example.testapp.Model.GetByIdCardModel.Userinfo;
import com.example.testapp.Model.GetListViolationHistoriesModel.ListViolationHistoriesRequestModel;
import com.example.testapp.Model.GetListViolationHistoriesModel.ListViolationHistoriesResponseModel;
import com.example.testapp.Model.HandlingViolations.HandlingViolationsModel;
import com.example.testapp.Model.LoginModel.LoginRequestModel;
import com.example.testapp.Model.LoginModel.LoginResponseModel;
import com.example.testapp.Model.Report.CreateViolationReportModel;
import com.example.testapp.Model.Report.ViolationReport;
import com.example.testapp.Model.VehicleProfilesModel.VehicleProfileRequestModel;
import com.example.testapp.Model.VehicleProfilesModel.VehicleProfiles;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface APIService {


    @Headers({ "Content-Type: application/json", "accept: */*", "host: 104.154.118.39"})
    @POST("/api/UserOfficial/UserOfficialAuthenticate")
    Call<LoginResponseModel> loginCallAPI(
            @Header("content-Length") String contentLength,
            @Body LoginRequestModel model
    );

    @Headers({ "Content-Type: application/json", "accept: */*", "host: 104.154.118.39"})
    @GET("/api/UserOfficial/GetOfficialInfo")
    Call<AccountInfoModel> getUserInfo(
            @Header("Authorization") String token
    );

    @Headers({ "Content-Type: application/json", "accept: */*", "host: 104.154.118.39" , "Connection: keep-alive"})
    @POST("/api/VehicleProfile/GetVehicleProfiles")
    Call<VehicleProfiles> GetVehicleProfile(
            @Header("content-Length") String contentLength,
            @Header("Authorization") String token,
            @Body VehicleProfileRequestModel model
    );

    @Headers({ "Content-Type: application/json", "accept: */*", "host: 104.154.118.39" , "Connection: keep-alive"})
    @POST("/api/ViolationHistory/GetListViolationHistories")
    Call<ListViolationHistoriesResponseModel> GetListViolationHistories(
            @Header("content-Length") String contentLength,
            @Header("Authorization") String token,
            @Body ListViolationHistoriesRequestModel model
    );
    @Headers({"Content-Type: application/json", "accept: */*", "host: 104.154.118.39"})
    @GET("/api/ViolationHistory/HandlingViolations")
    Call<HandlingViolationsModel> GetHandlingViolations(
            @Header("Authorization") String token,
            @Query("violationId") String violationId
    );

    @Headers({ "Content-Type: application/json","accept: */*", "host: 104.154.118.39"})
    @GET("/api/Citizen/GetByIdCard")
    Call<UserResponse> GetByIdcard(
            @Header("Authorization") String token,
            @Query("citizenIdCard") String citizenIdCard
    );


    @Headers({"Content-Type: application/json","accept: */*", "host: 104.154.118.39" , "Connection: keep-alive"})
    @POST("api/ReportViolations/CreateReportOneImage")
    Call<ViolationReport> CreateViolationReport(
            @Header("content-Length") String contentLength,
            @Header("Authorization") String token,
            @Body CreateViolationReportModel model
    );

}
