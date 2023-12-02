package com.valyx.pdfdatamanager.service;

import com.valyx.pdfdatamanager.dto.BalanceRequest;
import com.valyx.pdfdatamanager.dto.BalanceResponse;
import com.valyx.pdfdatamanager.dto.TransactionsRequest;
import com.valyx.pdfdatamanager.dto.TransactionsResponse;
import com.valyx.pdfdatamanager.models.Transaction;
import com.valyx.pdfdatamanager.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class QueryService {

    @Autowired
    private Repository repository;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY/MM/DD");
    public List<Transaction> getTransactions(){
        return repository.findAll();
    }

    public TransactionsResponse getTransactionBetween(TransactionsRequest request){

        LocalDate fromDate = LocalDate.parse(request.getFromDate());
        LocalDate toDate = LocalDate.parse(request.getToDate());
        List<Transaction> transactions = repository.findAllByDateLessThanEqualAndDateGreaterThanEqual(toDate, fromDate);

        return new TransactionsResponse(transactions);
    }
    public BalanceResponse getBalance(BalanceRequest balanceRequest){
        LocalDate date = LocalDate.parse(balanceRequest.getDate());
        Transaction transaction = repository.findFirstByDateEquals(date);
        return new BalanceResponse(transaction.getBalance());
    }
}
