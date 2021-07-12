package com.example.testapp.Model.VehicleProfilesModel;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Hometown {
    @SerializedName("provinceId")
    @Expose
    private Integer provinceId;
    @SerializedName("provinceName")
    @Expose
    private String provinceName;
    @SerializedName("provinceType")
    @Expose
    private String provinceType;
    @SerializedName("provinceLocation")
    @Expose
    private Object provinceLocation;
    @SerializedName("districtId")
    @Expose
    private Integer districtId;
    @SerializedName("districtName")
    @Expose
    private String districtName;
    @SerializedName("districtLocation")
    @Expose
    private String districtLocation;
    @SerializedName("districtType")
    @Expose
    private String districtType;
    @SerializedName("wardId")
    @Expose
    private Integer wardId;
    @SerializedName("wardName")
    @Expose
    private String wardName;
    @SerializedName("wardType")
    @Expose
    private String wardType;
    @SerializedName("wardLocation")
    @Expose
    private String wardLocation;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("iStreesNamed")
    @Expose
    private String iStreesNamed;

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceType() {
        return provinceType;
    }

    public void setProvinceType(String provinceType) {
        this.provinceType = provinceType;
    }

    public Object getProvinceLocation() {
        return provinceLocation;
    }

    public void setProvinceLocation(Object provinceLocation) {
        this.provinceLocation = provinceLocation;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictLocation() {
        return districtLocation;
    }

    public void setDistrictLocation(String districtLocation) {
        this.districtLocation = districtLocation;
    }

    public String getDistrictType() {
        return districtType;
    }

    public void setDistrictType(String districtType) {
        this.districtType = districtType;
    }

    public Integer getWardId() {
        return wardId;
    }

    public void setWardId(Integer wardId) {
        this.wardId = wardId;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getWardType() {
        return wardType;
    }

    public void setWardType(String wardType) {
        this.wardType = wardType;
    }

    public String getWardLocation() {
        return wardLocation;
    }

    public void setWardLocation(String wardLocation) {
        this.wardLocation = wardLocation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getiStreesNamed() {
        return iStreesNamed;
    }

    public void setiStreesNamed(String iStreesNamed) {
        this.iStreesNamed = iStreesNamed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Hometown.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("provinceId");
        sb.append('=');
        sb.append(((this.provinceId == null)?"<null>":this.provinceId));
        sb.append(',');
        sb.append("provinceName");
        sb.append('=');
        sb.append(((this.provinceName == null)?"<null>":this.provinceName));
        sb.append(',');
        sb.append("provinceType");
        sb.append('=');
        sb.append(((this.provinceType == null)?"<null>":this.provinceType));
        sb.append(',');
        sb.append("provinceLocation");
        sb.append('=');
        sb.append(((this.provinceLocation == null)?"<null>":this.provinceLocation));
        sb.append(',');
        sb.append("districtId");
        sb.append('=');
        sb.append(((this.districtId == null)?"<null>":this.districtId));
        sb.append(',');
        sb.append("districtName");
        sb.append('=');
        sb.append(((this.districtName == null)?"<null>":this.districtName));
        sb.append(',');
        sb.append("districtLocation");
        sb.append('=');
        sb.append(((this.districtLocation == null)?"<null>":this.districtLocation));
        sb.append(',');
        sb.append("districtType");
        sb.append('=');
        sb.append(((this.districtType == null)?"<null>":this.districtType));
        sb.append(',');
        sb.append("wardId");
        sb.append('=');
        sb.append(((this.wardId == null)?"<null>":this.wardId));
        sb.append(',');
        sb.append("wardName");
        sb.append('=');
        sb.append(((this.wardName == null)?"<null>":this.wardName));
        sb.append(',');
        sb.append("wardType");
        sb.append('=');
        sb.append(((this.wardType == null)?"<null>":this.wardType));
        sb.append(',');
        sb.append("wardLocation");
        sb.append('=');
        sb.append(((this.wardLocation == null)?"<null>":this.wardLocation));
        sb.append(',');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("iStreesNamed");
        sb.append('=');
        sb.append(((this.iStreesNamed == null)?"<null>":this.iStreesNamed));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}
