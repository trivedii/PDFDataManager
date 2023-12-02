package com.valyx.pdfdatamanager.dto;

public class BalanceRequest {
    private String date;

    public BalanceRequest(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
