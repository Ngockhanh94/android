package com.example.testapp.Model.VehicleProfilesModel;

public class VehicleProfileRequestModel {
    private String userOfficialId;
    private String plateOrVehicleIDCard;

    public VehicleProfileRequestModel(){}
    public VehicleProfileRequestModel(String userOfficialId, String plateOrVehicleIDCard){
        this.userOfficialId = userOfficialId;
        this.plateOrVehicleIDCard = plateOrVehicleIDCard;
    }

    public String getUserOfficialId() {
        return userOfficialId;
    }

    public void setUserOfficialId(String userOfficialId) {
        this.userOfficialId = userOfficialId;
    }

    public String getPlateOrVehicleIDCard() {
        return plateOrVehicleIDCard;
    }

    public void setPlateOrVehicleIDCard(String plateOrVehicleIDCard) {
        this.plateOrVehicleIDCard = plateOrVehicleIDCard;
    }
}
