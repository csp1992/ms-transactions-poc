package com.transaction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.transaction.model.entity.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, String> {

	List<Transaction> findByAccountIban(String accountIban);
	List<Transaction> findByAccountIbanOrderByAmountDesc(String accountIban);
	List<Transaction> findByAccountIbanOrderByAmountAsc(String accountIban);
	List<Transaction> findAllByOrderByAmountDesc();
	List<Transaction> findAllByOrderByAmountAsc();
	@Query(value = "SELECT sum(amount - fee) FROM Transaction WHERE accountIban = ?1")
    public Double getTotalBalanceByAccount(String accountIban);
}
