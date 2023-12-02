package com.valyx.pdfdatamanager.dto;

public class BalanceResponse extends BaseError{
    private String closingBalance;

    public BalanceResponse(String closingBalance) {
        this.closingBalance = closingBalance;
    }

    public String getClosingBalance() {
        return closingBalance;
    }


    public void setClosingBalance(String closingBalance) {
        this.closingBalance = closingBalance;
    }
}
