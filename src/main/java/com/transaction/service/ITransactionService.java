package com.transaction.service;

import java.util.List;

import com.transaction.model.TransactionStatusDTO;
import com.transaction.model.entity.Transaction;

public interface ITransactionService {
	
	public TransactionStatusDTO get(String reference, String channel);
	public List<Transaction> list(String account, String orderByAmount);
	public Transaction create(Transaction transaction);

}
