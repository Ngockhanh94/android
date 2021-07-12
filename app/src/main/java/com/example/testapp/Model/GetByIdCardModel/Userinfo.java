package com.example.testapp.Model.GetByIdCardModel;

import javax.annotation.Generated;

import com.example.testapp.Model.VehicleProfilesModel.Hometown;
import com.example.testapp.Model.VehicleProfilesModel.PermanentResidence;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Userinfo {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("sex")
    @Expose
    private Boolean sex;
    @SerializedName("dateOfBirth")
    @Expose
    private String dateOfBirth;
    @SerializedName("image")
    @Expose
    private Object image;
    @SerializedName("identityCardNumber")
    @Expose
    private String identityCardNumber;
    @SerializedName("hometownId")
    @Expose
    private String hometownId;
    @SerializedName("hometown")
    @Expose
    private Hometown hometown;
    @SerializedName("permanentResidenceId")
    @Expose
    private String permanentResidenceId;
    @SerializedName("permanentResidence")
    @Expose
    private PermanentResidence permanentResidence;
    @SerializedName("job")
    @Expose
    private String job;
    @SerializedName("ethnic")
    @Expose
    private String ethnic;
    @SerializedName("avartarPath")
    @Expose
    private Object avartarPath;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public String getIdentityCardNumber() {
        return identityCardNumber;
    }

    public void setIdentityCardNumber(String identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
    }

    public String getHometownId() {
        return hometownId;
    }

    public void setHometownId(String hometownId) {
        this.hometownId = hometownId;
    }

    public Hometown getHometown() {
        return hometown;
    }

    public void setHometown(Hometown hometown) {
        this.hometown = hometown;
    }

    public String getPermanentResidenceId() {
        return permanentResidenceId;
    }

    public void setPermanentResidenceId(String permanentResidenceId) {
        this.permanentResidenceId = permanentResidenceId;
    }

    public PermanentResidence getPermanentResidence() {
        return permanentResidence;
    }

    public void setPermanentResidence(PermanentResidence permanentResidence) {
        this.permanentResidence = permanentResidence;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEthnic() {
        return ethnic;
    }

    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
    }

    public Object getAvartarPath() {
        return avartarPath;
    }

    public void setAvartarPath(Object avartarPath) {
        this.avartarPath = avartarPath;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Userinfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("firstName");
        sb.append('=');
        sb.append(((this.firstName == null)?"<null>":this.firstName));
        sb.append(',');
        sb.append("lastName");
        sb.append('=');
        sb.append(((this.lastName == null)?"<null>":this.lastName));
        sb.append(',');
        sb.append("sex");
        sb.append('=');
        sb.append(((this.sex == null)?"<null>":this.sex));
        sb.append(',');
        sb.append("dateOfBirth");
        sb.append('=');
        sb.append(((this.dateOfBirth == null)?"<null>":this.dateOfBirth));
        sb.append(',');
        sb.append("image");
        sb.append('=');
        sb.append(((this.image == null)?"<null>":this.image));
        sb.append(',');
        sb.append("identityCardNumber");
        sb.append('=');
        sb.append(((this.identityCardNumber == null)?"<null>":this.identityCardNumber));
        sb.append(',');
        sb.append("hometownId");
        sb.append('=');
        sb.append(((this.hometownId == null)?"<null>":this.hometownId));
        sb.append(',');
        sb.append("hometown");
        sb.append('=');
        sb.append(((this.hometown == null)?"<null>":this.hometown));
        sb.append(',');
        sb.append("permanentResidenceId");
        sb.append('=');
        sb.append(((this.permanentResidenceId == null)?"<null>":this.permanentResidenceId));
        sb.append(',');
        sb.append("permanentResidence");
        sb.append('=');
        sb.append(((this.permanentResidence == null)?"<null>":this.permanentResidence));
        sb.append(',');
        sb.append("job");
        sb.append('=');
        sb.append(((this.job == null)?"<null>":this.job));
        sb.append(',');
        sb.append("ethnic");
        sb.append('=');
        sb.append(((this.ethnic == null)?"<null>":this.ethnic));
        sb.append(',');
        sb.append("avartarPath");
        sb.append('=');
        sb.append(((this.avartarPath == null)?"<null>":this.avartarPath));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}