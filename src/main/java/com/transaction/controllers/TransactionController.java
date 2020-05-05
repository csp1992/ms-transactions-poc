package com.transaction.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.model.TransactionStatusDTO;
import com.transaction.model.ChannelsEnum;
import com.transaction.model.entity.Transaction;
import com.transaction.service.ITransactionService;

@RestController
public class TransactionController {    
    
    @Autowired
    private ITransactionService transactionService;
    
    
    @RequestMapping(value = "/transaction", method = RequestMethod.POST)
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody Transaction transaction) {
        Transaction transactionCreated = transactionService.create(transaction);
        return new ResponseEntity(transactionCreated, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/transactionSearch", method = RequestMethod.GET)
    public ResponseEntity<Transaction> listTransaction(@RequestParam(value = "account_iban", required = false) String account, @RequestParam(value = "orderByAmount", required = false) String orderByAmount) {
        List<Transaction> transactions = transactionService.list(account, orderByAmount);
        return new ResponseEntity(transactions, HttpStatus.OK);
    }

    @RequestMapping(value = "/transactionStatus", method = RequestMethod.GET)
    public ResponseEntity<TransactionStatusDTO> transactionById(@RequestParam(value = "reference") String reference, @RequestParam(value = "channel", required = false) ChannelsEnum channel) {
    	TransactionStatusDTO transaction = transactionService.get(reference, channel.toString());
        return new ResponseEntity(transaction, HttpStatus.OK);
    }
    
    
    
}
