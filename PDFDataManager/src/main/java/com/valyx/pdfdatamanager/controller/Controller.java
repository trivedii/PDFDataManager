package com.valyx.pdfdatamanager.controller;
import com.valyx.pdfdatamanager.dto.*;
import com.valyx.pdfdatamanager.models.Transaction;
import com.valyx.pdfdatamanager.service.QueryService;
import com.valyx.pdfdatamanager.service.SavetoDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping
public class Controller {
    @Autowired
    private SavetoDB savetoDB;

    @Autowired
    private QueryService queryService;




    @PostMapping("/gmail/attachments/{subject}")
    public AttachmentResponse getAttachment(@PathVariable("subject") String subject) throws IOException {
            return savetoDB.save(subject);
    }

    @GetMapping("/get/allTransactions")
    public List<Transaction> getAllTransactions(){
        return queryService.getTransactions();
    }

    @GetMapping("/get/transactionsBetween")
    public TransactionsResponse getTransactionsBetween(@RequestParam String fromDate, String toDate){
        return queryService.getTransactionBetween(new TransactionsRequest(fromDate, toDate));
    }

    @GetMapping("get/ClosingBalance")
    public BalanceResponse getClosingBalance(@RequestParam String date){
        return queryService.getBalance(new BalanceRequest(date));
    }
}

