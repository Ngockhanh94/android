package com.example.testapp.Model.GetListViolationHistoriesModel;

public class ListViolationHistoriesRequestModel {
    private String dateStart;
    private String dateEnd;

    public ListViolationHistoriesRequestModel(){}

    public ListViolationHistoriesRequestModel(String dateStart, String dateEnd){
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }
}
