package com.valyx.pdfdatamanager.dto;

import com.valyx.pdfdatamanager.models.Transaction;

import java.util.List;

public class AllTransactionsResponse {
    private String Status;
    private List<Transaction> transactionList;

    public AllTransactionsResponse( String status,List<Transaction> transactionList) {
        this.transactionList = transactionList;
        Status = status;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
