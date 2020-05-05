package com.transaction.service;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.transaction.model.TransactionStatusDTO;
import com.transaction.model.entity.Transaction;
import com.transaction.repository.TransactionRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ItransactionServiceTest {
	
	@InjectMocks
	TransactionServiceImpl service;
	
	@Mock
	TransactionRepository repository;

	@Test
	public void getAllTransactionListWithoutFilter() {
		List<Transaction> list = new ArrayList<Transaction>();
		
		Transaction transactionTest1 = new Transaction();
        transactionTest1.setAccountIban("ES253420000000000");
        transactionTest1.setDate(new Date());
        transactionTest1.setAmount(25.30);
        transactionTest1.setFee(1.25);
        transactionTest1.setDescription("Test transaction1 description");
        
        Transaction transactionTest2 = new Transaction();
        transactionTest2.setAccountIban("ES253420000000005");
        transactionTest2.setDate(new Date());
        transactionTest2.setAmount(2500.30);
        transactionTest2.setFee(1.25);
        transactionTest2.setDescription("Test transaction1 description");
        
        list.add(transactionTest1);
        list.add(transactionTest2);
        
        when(repository.findAll()).thenReturn(list);
        
        List<Transaction> listTransaction = service.list(null, null);
        assertEquals(2, listTransaction.size());
	}
	
	@Test
	public void getAllTransactionListWithoutFilterOrderedByAmountDesc() {
		List<Transaction> list = new ArrayList<Transaction>();
		
		Transaction transactionTest1 = new Transaction();
        transactionTest1.setAccountIban("ES253420000000000");
        transactionTest1.setDate(new Date());
        transactionTest1.setAmount(25.30);
        transactionTest1.setFee(1.25);
        transactionTest1.setDescription("Test transaction1 description");
        
        Transaction transactionTest2 = new Transaction();
        transactionTest2.setAccountIban("ES253420000000000");
        transactionTest2.setDate(new Date());
        transactionTest2.setAmount(2500.30);
        transactionTest2.setFee(1.25);
        transactionTest2.setDescription("Test transaction1 description");
        
        list.add(transactionTest1);
        list.add(transactionTest2);
        
        when(repository.findAllByOrderByAmountDesc()).thenReturn(list);
        
        List<Transaction> listTransaction = service.list(null, "DESCENDING");
        assertEquals(2, listTransaction.size());
        assertTrue(listTransaction.get(0).getAmount()<=listTransaction.get(1).getAmount());
	}
	
	@Test
	public void getAllTransactionListWithoutFilterOrderedByAmountAsc() {
		List<Transaction> list = new ArrayList<Transaction>();
		
		Transaction transactionTest1 = new Transaction();
        transactionTest1.setAccountIban("ES253420000000000");
        transactionTest1.setDate(new Date());
        transactionTest1.setAmount(25.30);
        transactionTest1.setFee(1.25);
        transactionTest1.setDescription("Test transaction1 description");
        
        Transaction transactionTest2 = new Transaction();
        transactionTest2.setAccountIban("ES253420000000000");
        transactionTest2.setDate(new Date());
        transactionTest2.setAmount(2500.30);
        transactionTest2.setFee(1.25);
        transactionTest2.setDescription("Test transaction1 description");
        
        list.add(transactionTest2);
        list.add(transactionTest1);
        
        when(repository.findAllByOrderByAmountAsc()).thenReturn(list);
        
        List<Transaction> listTransaction = service.list(null, "ASCENDING");
        assertEquals(2, listTransaction.size());
        assertTrue(listTransaction.get(0).getAmount()>=listTransaction.get(1).getAmount());
	}
	
	@Test
	public void getAllTransactionListFilterByIbanAccount() {
		List<Transaction> list = new ArrayList<Transaction>();
		
		Transaction transactionTest1 = new Transaction();
        transactionTest1.setAccountIban("ES253420000000000");
        transactionTest1.setDate(new Date());
        transactionTest1.setAmount(25.30);
        transactionTest1.setFee(1.25);
        transactionTest1.setDescription("Test transaction1 description");
        
        Transaction transactionTest2 = new Transaction();
        transactionTest2.setAccountIban("ES253420000000000");
        transactionTest2.setDate(new Date());
        transactionTest2.setAmount(2500.30);
        transactionTest2.setFee(1.25);
        transactionTest2.setDescription("Test transaction1 description");
        
        list.add(transactionTest1);
        list.add(transactionTest2);
        
        when(repository.findByAccountIban(transactionTest1.getAccountIban())).thenReturn(list);
        
        List<Transaction> listTransaction = service.list("ES253420000000000", null);
        assertEquals(2, listTransaction.size());
	}
	
	@Test
	public void getAllTransactionListFilterByIbanAccountOrderedByAmountDesc() {
		List<Transaction> list = new ArrayList<Transaction>();
		
		Transaction transactionTest1 = new Transaction();
        transactionTest1.setAccountIban("ES253420000000000");
        transactionTest1.setDate(new Date());
        transactionTest1.setAmount(25.30);
        transactionTest1.setFee(1.25);
        transactionTest1.setDescription("Test transaction1 description");
        
        Transaction transactionTest2 = new Transaction();
        transactionTest2.setAccountIban("ES253420000000000");
        transactionTest2.setDate(new Date());
        transactionTest2.setAmount(2500.30);
        transactionTest2.setFee(1.25);
        transactionTest2.setDescription("Test transaction1 description");
        
        list.add(transactionTest1);
        list.add(transactionTest2);
        
        when(repository.findByAccountIbanOrderByAmountDesc(transactionTest1.getAccountIban())).thenReturn(list);
        
        List<Transaction> listTransaction = service.list("ES253420000000000", "DESCENDING");
        assertEquals(2, listTransaction.size());
        assertTrue(listTransaction.get(0).getAmount()<=listTransaction.get(1).getAmount());
	}
	
	@Test
	public void getAllTransactionListFilterByIbanAccountOrderedByAmountAsc() {
		List<Transaction> list = new ArrayList<Transaction>();
		
		Transaction transactionTest1 = new Transaction();
        transactionTest1.setAccountIban("ES253420000000000");
        transactionTest1.setDate(new Date());
        transactionTest1.setAmount(25.30);
        transactionTest1.setFee(1.25);
        transactionTest1.setDescription("Test transaction1 description");
        
        Transaction transactionTest2 = new Transaction();
        transactionTest2.setAccountIban("ES253420000000000");
        transactionTest2.setDate(new Date());
        transactionTest2.setAmount(2500.30);
        transactionTest2.setFee(1.25);
        transactionTest2.setDescription("Test transaction1 description");
        
        list.add(transactionTest2);
        list.add(transactionTest1);
        
        when(repository.findByAccountIbanOrderByAmountAsc(transactionTest1.getAccountIban())).thenReturn(list);
        
        List<Transaction> listTransaction = service.list("ES253420000000000", "ASCENDING");
        assertEquals(2, listTransaction.size());
        assertTrue(listTransaction.get(0).getAmount()>=listTransaction.get(1).getAmount());
	}
	
	@Test
	public void getTransactionStatusWhenTransactionFutureDateAndChannelIsInternal() {
		
		Transaction transactionTest1 = new Transaction();
        transactionTest1.setAccountIban("ES253420000000000");
        transactionTest1.setId("XXXX");
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR)+1, 10, 12);       
        transactionTest1.setDate(calendar.getTime());
        transactionTest1.setAmount(25.30);
        transactionTest1.setFee(1.25);
        transactionTest1.setDescription("Test transaction1 description");
		
        when(repository.findById(transactionTest1.getId())).thenReturn(Optional.of(transactionTest1));
        
        TransactionStatusDTO transactionStatusDTO = service.get(transactionTest1.getId(), "INTERNAL"); 
        
        assertTrue("FUTURE".equals(transactionStatusDTO.getStatus()));
        assertTrue(25.30 == transactionStatusDTO.getAmount());
        assertTrue(1.25 == transactionStatusDTO.getFee());
        assertEquals("XXXX", transactionStatusDTO.getReference());
	}
	
	@Test
	public void getTransactionStatusWhenTransactionTodayDateAndChannelIsInternal() {
		
		Transaction transactionTest1 = new Transaction();
        transactionTest1.setAccountIban("ES253420000000000");
        transactionTest1.setId("XXXX");  
        transactionTest1.setDate(new Date());
        transactionTest1.setAmount(25.30);
        transactionTest1.setFee(1.25);
        transactionTest1.setDescription("Test transaction1 description");
		
        when(repository.findById(transactionTest1.getId())).thenReturn(Optional.of(transactionTest1));
        
        TransactionStatusDTO transactionStatusDTO = service.get(transactionTest1.getId(), "INTERNAL"); 
        
        assertTrue("PENDING".equals(transactionStatusDTO.getStatus()));
        assertTrue(25.30 == transactionStatusDTO.getAmount());
        assertTrue(1.25 == transactionStatusDTO.getFee());
        assertEquals("XXXX", transactionStatusDTO.getReference());
	}
	
	@Test
	public void getTransactionStatusWhenTransactionPastDateAndChannelIsInternal() {
		
		Transaction transactionTest1 = new Transaction();
        transactionTest1.setAccountIban("ES253420000000000");
        transactionTest1.setId("XXXX");
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR)-1, 10, 12);       
        transactionTest1.setDate(calendar.getTime());
        transactionTest1.setAmount(25.30);
        transactionTest1.setFee(1.25);
        transactionTest1.setDescription("Test transaction1 description");
		
        when(repository.findById(transactionTest1.getId())).thenReturn(Optional.of(transactionTest1));
        
        TransactionStatusDTO transactionStatusDTO = service.get(transactionTest1.getId(), "INTERNAL"); 
        
        assertTrue("SETTLED".equals(transactionStatusDTO.getStatus()));
        assertTrue(25.30 == transactionStatusDTO.getAmount());
        assertTrue(1.25 == transactionStatusDTO.getFee());
        assertEquals("XXXX", transactionStatusDTO.getReference());
	}
	
	@Test
	public void getTransactionStatusWhenTransactionFutureDateAndChannelIsATM() {
		
		Transaction transactionTest1 = new Transaction();
        transactionTest1.setAccountIban("ES253420000000000");
        transactionTest1.setId("XXXX");
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR)+1, 10, 12);       
        transactionTest1.setDate(calendar.getTime());
        transactionTest1.setAmount(25.30);
        transactionTest1.setFee(1.25);
        transactionTest1.setDescription("Test transaction1 description");
		
        when(repository.findById(transactionTest1.getId())).thenReturn(Optional.of(transactionTest1));
        
        TransactionStatusDTO transactionStatusDTO = service.get(transactionTest1.getId(), "ATM"); 
        
        assertTrue("PENDING".equals(transactionStatusDTO.getStatus()));
        assertTrue(transactionTest1.getAmount()-transactionTest1.getFee() == transactionStatusDTO.getAmount());
        assertNull(transactionStatusDTO.getFee());
        assertEquals("XXXX", transactionStatusDTO.getReference());
	}
	
	@Test
	public void getTransactionStatusWhenTransactionTodayDateAndChannelIsATM() {
		
		Transaction transactionTest1 = new Transaction();
        transactionTest1.setAccountIban("ES253420000000000");
        transactionTest1.setId("XXXX");  
        transactionTest1.setDate(new Date());
        transactionTest1.setAmount(25.30);
        transactionTest1.setFee(1.25);
        transactionTest1.setDescription("Test transaction1 description");
		
        when(repository.findById(transactionTest1.getId())).thenReturn(Optional.of(transactionTest1));
        
        TransactionStatusDTO transactionStatusDTO = service.get(transactionTest1.getId(), "ATM"); 
        
        assertTrue("PENDING".equals(transactionStatusDTO.getStatus()));
        assertTrue(transactionTest1.getAmount()-transactionTest1.getFee() == transactionStatusDTO.getAmount());
        assertNull(transactionStatusDTO.getFee());
        assertEquals("XXXX", transactionStatusDTO.getReference());
	}
	
	@Test
	public void getTransactionStatusWhenTransactionPastDateAndChannelIsATM() {
		
		Transaction transactionTest1 = new Transaction();
        transactionTest1.setAccountIban("ES253420000000000");
        transactionTest1.setId("XXXX");
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR)-1, 10, 12);       
        transactionTest1.setDate(calendar.getTime());
        transactionTest1.setAmount(25.30);
        transactionTest1.setFee(1.25);
        transactionTest1.setDescription("Test transaction1 description");
		
        when(repository.findById(transactionTest1.getId())).thenReturn(Optional.of(transactionTest1));
        
        TransactionStatusDTO transactionStatusDTO = service.get(transactionTest1.getId(), "ATM"); 
        
        assertTrue("SETTLED".equals(transactionStatusDTO.getStatus()));
        assertTrue(transactionTest1.getAmount()-transactionTest1.getFee() == transactionStatusDTO.getAmount());
        assertNull(transactionStatusDTO.getFee());
        assertEquals("XXXX", transactionStatusDTO.getReference());
	}
	
	@Test
	public void getTransactionStatusWhenTransactionFutureDateAndChannelIsCLIENT() {
		
		Transaction transactionTest1 = new Transaction();
        transactionTest1.setAccountIban("ES253420000000000");
        transactionTest1.setId("XXXX");
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR)+1, 10, 12);       
        transactionTest1.setDate(calendar.getTime());
        transactionTest1.setAmount(25.30);
        transactionTest1.setFee(1.25);
        transactionTest1.setDescription("Test transaction1 description");
		
        when(repository.findById(transactionTest1.getId())).thenReturn(Optional.of(transactionTest1));
        
        TransactionStatusDTO transactionStatusDTO = service.get(transactionTest1.getId(), "CLIENT"); 
        
        assertTrue("FUTURE".equals(transactionStatusDTO.getStatus()));
        assertTrue(transactionTest1.getAmount()-transactionTest1.getFee() == transactionStatusDTO.getAmount());
        assertNull(transactionStatusDTO.getFee());
        assertEquals("XXXX", transactionStatusDTO.getReference());
	}
	
	@Test
	public void getTransactionStatusWhenTransactionTodayDateAndChannelIsCLIENT() {
		
		Transaction transactionTest1 = new Transaction();
        transactionTest1.setAccountIban("ES253420000000000");
        transactionTest1.setId("XXXX");  
        transactionTest1.setDate(new Date());
        transactionTest1.setAmount(25.30);
        transactionTest1.setFee(1.25);
        transactionTest1.setDescription("Test transaction1 description");
		
        when(repository.findById(transactionTest1.getId())).thenReturn(Optional.of(transactionTest1));
        
        TransactionStatusDTO transactionStatusDTO = service.get(transactionTest1.getId(), "CLIENT"); 
        
        assertTrue("PENDING".equals(transactionStatusDTO.getStatus()));
        assertTrue(transactionTest1.getAmount()-transactionTest1.getFee() == transactionStatusDTO.getAmount());
        assertNull(transactionStatusDTO.getFee());
        assertEquals("XXXX", transactionStatusDTO.getReference());
	}
	
	@Test
	public void getTransactionStatusWhenTransactionPastDateAndChannelIsCLIENT() {
		
		Transaction transactionTest1 = new Transaction();
        transactionTest1.setAccountIban("ES253420000000000");
        transactionTest1.setId("XXXX");
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR)-1, 10, 12);       
        transactionTest1.setDate(calendar.getTime());
        transactionTest1.setAmount(25.30);
        transactionTest1.setFee(1.25);
        transactionTest1.setDescription("Test transaction1 description");
		
        when(repository.findById(transactionTest1.getId())).thenReturn(Optional.of(transactionTest1));
        
        TransactionStatusDTO transactionStatusDTO = service.get(transactionTest1.getId(), "CLIENT"); 
        
        assertTrue("SETTLED".equals(transactionStatusDTO.getStatus()));
        assertTrue(transactionTest1.getAmount()-transactionTest1.getFee() == transactionStatusDTO.getAmount());
        assertNull(transactionStatusDTO.getFee());
        assertEquals("XXXX", transactionStatusDTO.getReference());
	}
	
	@Test
	public void createTransactionT() {
		
		Transaction transactionTest1 = new Transaction();
        transactionTest1.setAccountIban("ES253420000000000");
        transactionTest1.setId("XXXX");
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR)-1, 10, 12);       
        transactionTest1.setDate(calendar.getTime());
        transactionTest1.setAmount(25.30);
        transactionTest1.setFee(1.25);
        transactionTest1.setDescription("Test transaction1 description");
		
        when(repository.save(transactionTest1)).thenReturn(transactionTest1);
        
        Transaction transaction = service.create(transactionTest1);
        
        assertTrue("XXXX".equals(transaction.getId()));
	}
}
