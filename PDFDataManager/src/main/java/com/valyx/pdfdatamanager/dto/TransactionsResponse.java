package com.valyx.pdfdatamanager.dto;

import com.valyx.pdfdatamanager.models.Transaction;

import java.util.List;

public class TransactionsResponse extends BaseError{
    private List<Transaction> transactions;

    public TransactionsResponse(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
