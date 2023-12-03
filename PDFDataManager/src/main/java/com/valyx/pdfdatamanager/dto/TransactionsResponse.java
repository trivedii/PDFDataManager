package com.valyx.pdfdatamanager.dto;

import com.valyx.pdfdatamanager.models.Transaction;

import java.util.List;

public class TransactionsResponse {
    private List<Transaction> transactions;
    private String error;
    public TransactionsResponse(List<Transaction> transactions, String status) {
        this.transactions = transactions;
        this.error = status;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
