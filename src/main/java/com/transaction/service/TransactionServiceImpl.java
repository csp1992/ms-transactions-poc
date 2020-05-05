package com.transaction.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transaction.model.TransactionStatusDTO;
import com.transaction.model.entity.Transaction;
import com.transaction.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements ITransactionService {

    private static final String STATUS_INVALID = "INVALID";
	private static final String STATUS_FUTURE = "FUTURE";
	private static final String STATUS_SETTLED = "SETTLED";
	private static final String STATUS_PENDING = "PENDING";

	private static final String CHANNEL_INTERNAL = "INTERNAL";
	private static final String CHANNEL_CLIENT = "CLIENT";
	private static final String CHANNEL_ATM = "ATM";

	@Autowired
    private TransactionRepository repository;
    
    @Autowired
    private ModelMapper modelMapper;

    public TransactionStatusDTO get(String reference, String channel) {    	    	
        return mapToDTO(reference, channel);
    }    

	public List<Transaction> list(String account, String orderByAmount) {
    	List<Transaction> list = new ArrayList<Transaction>();
    	
    	if(account == null || account.isEmpty()) {    		
    		if(orderByAmount != null & "ascending".equalsIgnoreCase(orderByAmount)) {
    			list = repository.findAllByOrderByAmountAsc();
    		}else if(orderByAmount != null & "descending".equalsIgnoreCase(orderByAmount)) {
    			list = repository.findAllByOrderByAmountDesc();
    		}else {
    			Iterable<Transaction> transactions = repository.findAll();
        		transactions.forEach(list::add);
    		}    		
    	}else {
    		if(orderByAmount != null & "ascending".equalsIgnoreCase(orderByAmount)) {
    			list = repository.findByAccountIbanOrderByAmountAsc(account);
    		}else if(orderByAmount != null & "descending".equalsIgnoreCase(orderByAmount)) {
    			list = repository.findByAccountIbanOrderByAmountDesc(account);
    		}else {
    			list = repository.findByAccountIban(account);
    		}    		
    	}    
        
        return list;
    }

    public Transaction create(Transaction transaction) {    	
        return (validTransaction(transaction))?repository.save(transaction):null;
    }
    
    private Boolean validTransaction(Transaction transaction) {
    	Boolean valid = false;
    	Double totalAccountBalance = repository.getTotalBalanceByAccount(transaction.getAccountIban());
    	if(totalAccountBalance == null && (transaction.getAmount()-transaction.getFee()) >= 0){
    		valid = true;
    	}else if(totalAccountBalance != null && totalAccountBalance <= (transaction.getAmount()-transaction.getFee())){
    		valid = true;
    	}
    	
		return valid;
		
	}

	private TransactionStatusDTO mapToDTO(String reference, String channel) {
		TransactionStatusDTO transactionStatusDTO = new TransactionStatusDTO();
		Transaction transaction = repository.findById(reference).orElse(null);
		if(transaction!=null) {
			//transactionStatusDTO = modelMapper.map(transaction, TransactionStatusDTO.class);
			transactionStatusDTO.setReference(transaction.getId());
			transactionStatusDTO.setStatus(processStatus(transaction, channel));
			transactionStatusDTO.setAmount(transaction.getAmount());
			transactionStatusDTO.setFee(transaction.getFee());
			if(CHANNEL_ATM.equalsIgnoreCase(channel) || CHANNEL_CLIENT.equalsIgnoreCase(channel)) {
				transactionStatusDTO.setAmount(transaction.getAmount()-transaction.getFee());
				transactionStatusDTO.setFee(null);
			}
		}else {			
			transactionStatusDTO.setReference(reference);
			transactionStatusDTO.setStatus(STATUS_INVALID);
		}
		
		return transactionStatusDTO;
	}
    
    private String processStatus(Transaction transaction, String channel) {	
    	
    	String status = null;
    	
    	if(getDateWithoutTimeUsingCalendar(transaction.getDate()).before( getDateWithoutTimeUsingCalendar(new Date()))) {    		
    		status = STATUS_SETTLED;
    	}else if(getDateWithoutTimeUsingCalendar(transaction.getDate()).equals( getDateWithoutTimeUsingCalendar(new Date()))) {
    		status = STATUS_PENDING;
    	}else {
    		if(channel == null || CHANNEL_CLIENT.equalsIgnoreCase(channel) || CHANNEL_INTERNAL.equalsIgnoreCase(channel)) {
    			status = STATUS_FUTURE;
    		}else{    			
    			status = STATUS_PENDING;
    		}
    	}
    	
		return status;
	}
    
    private static Date getDateWithoutTimeUsingCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		return calendar.getTime();
	}
}
