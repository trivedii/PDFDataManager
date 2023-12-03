package com.valyx.pdfdatamanager.service;

import com.valyx.pdfdatamanager.dto.*;
import com.valyx.pdfdatamanager.models.Transaction;
import com.valyx.pdfdatamanager.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class QueryService {

    @Autowired
    private Repository repository;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY/MM/DD");

    public AllTransactionsResponse getTransactions(){
        List<Transaction> allTransactions = repository.findAll();
        if(allTransactions.isEmpty()){
            return new AllTransactionsResponse("No transactions Found!",allTransactions);
        }
        return new AllTransactionsResponse("Success",allTransactions);
    }

    public TransactionsResponse getTransactionBetween(TransactionsRequest request){
        try {
            LocalDate fromDate = LocalDate.parse(request.getFromDate());
            LocalDate toDate = LocalDate.parse(request.getToDate());
            List<Transaction> transactions = repository.findAllByDateLessThanEqualAndDateGreaterThanEqual(toDate, fromDate);

            if (transactions.isEmpty()) {
                return new TransactionsResponse(transactions, "No transactions found for the given date window!");
            }
            return new TransactionsResponse(transactions, "success");
        }
        catch (Exception e){
            return new TransactionsResponse(new ArrayList<>(), "Date Input format is either empty or invalid, Acceptable date format are YYYY-MM-DD");
        }
    }
    public BalanceResponse getBalance(BalanceRequest balanceRequest){
        try {
            LocalDate date = LocalDate.parse(balanceRequest.getDate());
            Transaction transaction = repository.findFirstByDateLessThanEqual(date);
            if(ObjectUtils.isEmpty(transaction)){
                return new BalanceResponse(transaction.getBalance(),"no transaction records found!");
            }
            return new BalanceResponse(transaction.getBalance(),"success");
        }
        catch (Exception e){
            return new BalanceResponse("","Date Input is either empty or invalid, Acceptable date format is YYYY-MM-DD");
        }
    }
}
