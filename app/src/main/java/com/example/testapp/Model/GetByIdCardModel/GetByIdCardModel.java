package com.example.testapp.Model.GetByIdCardModel;

public class GetByIdCardModel {
    private String citizenIdCard;
    public GetByIdCardModel(){}
    public GetByIdCardModel(String citizenIdCard){
        this.citizenIdCard = citizenIdCard;
    }

    public String getCitizenIdCard() {
        return citizenIdCard;
    }

    public void setCitizenIdCard(String citizenIdCard) {
        this.citizenIdCard = citizenIdCard;
    }
}
