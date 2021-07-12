package com.example.testapp.Model.Report;

import com.google.android.gms.common.data.SingleRefDataBufferIterator;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

import okhttp3.MultipartBody;

public class CreateViolationReportModel {
    public CreateViolationReportModel(){}

    private String Reposter;
    private String Title;
    private String Content;
    private String Location;
    private String Address;
    private String ReportDate;
    private String FileUpload;

    public CreateViolationReportModel(String reposter, String title, String content, String location, String address, String reportDate, String fileUpload) {
        this.Reposter = reposter;
        this.Title = title;
        this.Content = content;
        this.Location = location;
        this.Address = address;
        this.ReportDate = reportDate;
        this.FileUpload = fileUpload;
    }


    public String getReposter() {
        return Reposter;
    }

    public void setReposter(String reposter) {
        Reposter = reposter;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getReportDate() {
        return ReportDate;
    }

    public void setReportDate(String reportDate) {
        ReportDate = reportDate;
    }

    public String getFileUpload() {
        return FileUpload;
    }

    public void setFileUpload(String fileUpload) {
        FileUpload = fileUpload;
    }
}
