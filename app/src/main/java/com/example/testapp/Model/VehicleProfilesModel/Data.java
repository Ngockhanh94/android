package com.example.testapp.Model.VehicleProfilesModel;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Data {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("ownerIdCard")
    @Expose
    private String ownerIdCard;
    @SerializedName("chassis")
    @Expose
    private String chassis;
    @SerializedName("engine")
    @Expose
    private String engine;
    @SerializedName("modelCode")
    @Expose
    private String modelCode;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("capacity")
    @Expose
    private Integer capacity;
    @SerializedName("plate")
    @Expose
    private String plate;
    @SerializedName("registrationDate")
    @Expose
    private String registrationDate;
    @SerializedName("vehicleIDCard")
    @Expose
    private String vehicleIDCard;
    @SerializedName("vehicleType")
    @Expose
    private String vehicleType;
    @SerializedName("owner")
    @Expose
    private Owner owner;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwnerIdCard() {
        return ownerIdCard;
    }

    public void setOwnerIdCard(String ownerIdCard) {
        this.ownerIdCard = ownerIdCard;
    }

    public String getChassis() {
        return chassis;
    }

    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getVehicleIDCard() {
        return vehicleIDCard;
    }

    public void setVehicleIDCard(String vehicleIDCard) {
        this.vehicleIDCard = vehicleIDCard;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Data.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("ownerIdCard");
        sb.append('=');
        sb.append(((this.ownerIdCard == null)?"<null>":this.ownerIdCard));
        sb.append(',');
        sb.append("chassis");
        sb.append('=');
        sb.append(((this.chassis == null)?"<null>":this.chassis));
        sb.append(',');
        sb.append("engine");
        sb.append('=');
        sb.append(((this.engine == null)?"<null>":this.engine));
        sb.append(',');
        sb.append("modelCode");
        sb.append('=');
        sb.append(((this.modelCode == null)?"<null>":this.modelCode));
        sb.append(',');
        sb.append("brand");
        sb.append('=');
        sb.append(((this.brand == null)?"<null>":this.brand));
        sb.append(',');
        sb.append("color");
        sb.append('=');
        sb.append(((this.color == null)?"<null>":this.color));
        sb.append(',');
        sb.append("capacity");
        sb.append('=');
        sb.append(((this.capacity == null)?"<null>":this.capacity));
        sb.append(',');
        sb.append("plate");
        sb.append('=');
        sb.append(((this.plate == null)?"<null>":this.plate));
        sb.append(',');
        sb.append("registrationDate");
        sb.append('=');
        sb.append(((this.registrationDate == null)?"<null>":this.registrationDate));
        sb.append(',');
        sb.append("vehicleIDCard");
        sb.append('=');
        sb.append(((this.vehicleIDCard == null)?"<null>":this.vehicleIDCard));
        sb.append(',');
        sb.append("vehicleType");
        sb.append('=');
        sb.append(((this.vehicleType == null)?"<null>":this.vehicleType));
        sb.append(',');
        sb.append("owner");
        sb.append('=');
        sb.append(((this.owner == null)?"<null>":this.owner));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}