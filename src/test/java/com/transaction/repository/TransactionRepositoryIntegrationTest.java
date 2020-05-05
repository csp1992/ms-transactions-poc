package com.transaction.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.transaction.model.entity.Transaction;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TransactionRepositoryIntegrationTest {
	
	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private TransactionRepository transactionRepository;
 
    @Test
    public void whenFindByAccountIban_thenReturnTransaction() {
        
    	// given
        Transaction transactionTest1 = new Transaction();
        transactionTest1.setAccountIban("ES253420000000000");
        transactionTest1.setDate(new Date());
        transactionTest1.setAmount(25.30);
        transactionTest1.setFee(1.25);
        transactionTest1.setDescription("Test transaction description");        
        
        entityManager.persist(transactionTest1);
        entityManager.flush();
     
        // when
        List<Transaction> foundList = transactionRepository.findByAccountIban(transactionTest1.getAccountIban());
        Transaction found = foundList.get(0);
     
        // then
        assertThat(found.getId())
          .isEqualTo(transactionTest1.getId());
    }
    
    @Test
    public void whenFindByAccountIbanOrderByAmountDesc_thenReturnTransactionListOrderByAmountDesc() {
        
    	// given
        Transaction transactionTest1 = new Transaction();
        transactionTest1.setAccountIban("ES253420000000000");
        transactionTest1.setDate(new Date());
        transactionTest1.setAmount(25.30);
        transactionTest1.setFee(1.25);
        transactionTest1.setDescription("Test transaction1 description");        
        
        Transaction transactionTest2 = new Transaction();
        transactionTest2.setAccountIban("ES253420000000000");
        transactionTest2.setDate(new Date());
        transactionTest2.setAmount(2000.30);
        transactionTest2.setFee(1.25);
        transactionTest2.setDescription("Test transaction2 description");
        
        Transaction transactionTest3 = new Transaction();
        transactionTest3.setAccountIban("ES253420000000099");
        transactionTest3.setDate(new Date());
        transactionTest3.setAmount(2000.30);
        transactionTest3.setFee(1.25);
        transactionTest3.setDescription("Test transaction3 description");
        
        entityManager.persist(transactionTest1);
        entityManager.persist(transactionTest2);
        entityManager.persist(transactionTest3);
        entityManager.flush();
     
        // when
        List<Transaction> foundList = transactionRepository.findByAccountIbanOrderByAmountDesc(transactionTest1.getAccountIban());
        Transaction found0 = foundList.get(0);
        Transaction found1 = foundList.get(0);
     
        // then
        assertThat(found0.getAmount()<=found1.getAmount()).isTrue();
        assertTrue(foundList.size()==2);
    }
    
    @Test
    public void whenFindByAccountIbanOrderByAmountAsc_thenReturnTransactionListOrderByAmountAsc() {
        
    	// given
        Transaction transactionTest1 = new Transaction();
        transactionTest1.setAccountIban("ES253420000000000");
        transactionTest1.setDate(new Date());
        transactionTest1.setAmount(25.30);
        transactionTest1.setFee(1.25);
        transactionTest1.setDescription("Test transaction1 description");        
        
        Transaction transactionTest2 = new Transaction();
        transactionTest2.setAccountIban("ES253420000000000");
        transactionTest2.setDate(new Date());
        transactionTest2.setAmount(2000.30);
        transactionTest2.setFee(1.25);
        transactionTest2.setDescription("Test transaction2 description");
        
        Transaction transactionTest3 = new Transaction();
        transactionTest3.setAccountIban("ES253420000000099");
        transactionTest3.setDate(new Date());
        transactionTest3.setAmount(2000.30);
        transactionTest3.setFee(1.25);
        transactionTest3.setDescription("Test transaction3 description");
        
        entityManager.persist(transactionTest1);
        entityManager.persist(transactionTest2);
        entityManager.persist(transactionTest3);
        entityManager.flush();
     
        // when
        List<Transaction> foundList = transactionRepository.findByAccountIbanOrderByAmountAsc(transactionTest1.getAccountIban());
        Transaction found0 = foundList.get(0);
        Transaction found1 = foundList.get(0);
     
        // then
        assertThat(found0.getAmount()>=found1.getAmount()).isTrue();
        assertTrue(foundList.size()==2);
    }
    
    @Test
    public void whenFindAllByOrderByAmountDesc_thenReturnAllTransactionListOrderByAmountDesc() {
        
    	// given
        Transaction transactionTest1 = new Transaction();
        transactionTest1.setAccountIban("ES253420000000001");
        transactionTest1.setDate(new Date());
        transactionTest1.setAmount(25.30);
        transactionTest1.setFee(1.25);
        transactionTest1.setDescription("Test transaction1 description");        
        
        Transaction transactionTest2 = new Transaction();
        transactionTest2.setAccountIban("ES253420000000002");
        transactionTest2.setDate(new Date());
        transactionTest2.setAmount(2000.30);
        transactionTest2.setFee(1.25);
        transactionTest2.setDescription("Test transaction2 description"); 
        
        entityManager.persist(transactionTest1);
        entityManager.persist(transactionTest2);
        entityManager.flush();
     
        // when
        List<Transaction> foundList = transactionRepository.findAllByOrderByAmountDesc();
        Transaction found0 = foundList.get(0);
        Transaction found1 = foundList.get(0);
     
        // then
        assertThat(found0.getAmount()<=found1.getAmount()).isTrue();
        assertTrue(foundList.size()==2);
    }
    
    @Test
    public void whenFindAllByOrderByAmountAsc_thenReturnAllTransactionListOrderByAmountAsc() {
        
    	// given
        Transaction transactionTest1 = new Transaction();
        transactionTest1.setAccountIban("ES253420000000001");
        transactionTest1.setDate(new Date());
        transactionTest1.setAmount(25.30);
        transactionTest1.setFee(1.25);
        transactionTest1.setDescription("Test transaction1 description");        
        
        Transaction transactionTest2 = new Transaction();
        transactionTest2.setAccountIban("ES253420000000002");
        transactionTest2.setDate(new Date());
        transactionTest2.setAmount(2000.30);
        transactionTest2.setFee(1.25);
        transactionTest2.setDescription("Test transaction2 description"); 
        
        entityManager.persist(transactionTest1);
        entityManager.persist(transactionTest2);
        entityManager.flush();
     
        // when
        List<Transaction> foundList = transactionRepository.findAllByOrderByAmountAsc();
        Transaction found0 = foundList.get(0);
        Transaction found1 = foundList.get(0);
     
        // then
        assertThat(found0.getAmount()<=found1.getAmount()).isTrue();
        assertTrue(foundList.size()==2);
    }
    
    @Test
    public void whenGetTotalBalanceByAccount_thenReturnBalanceAccount() {
        
    	// given
        Transaction transactionTest1 = new Transaction();
        transactionTest1.setAccountIban("ES253420000000000");
        transactionTest1.setDate(new Date());
        transactionTest1.setAmount(25.30);
        transactionTest1.setFee(1.25);
        transactionTest1.setDescription("Test transaction1 description");        
        
        Transaction transactionTest2 = new Transaction();
        transactionTest2.setAccountIban("ES253420000000000");
        transactionTest2.setDate(new Date());
        transactionTest2.setAmount(2000.30);
        transactionTest2.setFee(1.30);
        transactionTest2.setDescription("Test transaction2 description"); 
        
        entityManager.persist(transactionTest1);
        entityManager.persist(transactionTest2);
        entityManager.flush();
     
        // when
        Double balance = transactionRepository.getTotalBalanceByAccount(transactionTest1.getAccountIban());
             
        // then        
        assertTrue(balance == (transactionTest1.getAmount()-transactionTest1.getFee())
        		+(transactionTest2.getAmount()-transactionTest2.getFee()));
    }

}
