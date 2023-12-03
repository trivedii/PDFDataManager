package com.valyx.pdfdatamanager.dto;

public class BalanceResponse {
    private String closingBalance;

    private String status;

    public BalanceResponse(String closingBalance, String status ) {
        this.closingBalance = closingBalance;
        this.status = status;
    }

    public String getClosingBalance() {
        return closingBalance;
    }


    public void setClosingBalance(String closingBalance) {
        this.closingBalance = closingBalance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
