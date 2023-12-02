package com.valyx.pdfdatamanager.dto;

public class TransactionsRequest {
    private String FromDate;
    private String ToDate;

    public TransactionsRequest(String fromDate, String toDate) {
        FromDate = fromDate;
        ToDate = toDate;
    }

    public String getFromDate() {
        return FromDate;
    }

    public void setFromDate(String fromDate) {
        FromDate = fromDate;
    }

    public String getToDate() {
        return ToDate;
    }

    public void setToDate(String toDate) {
        ToDate = toDate;
    }
}
