# ms-transactions-poc
Microservices for the control of transactions in a company.

For run the service, run the SpringBootAplication, Application.java.

Exist Two class for test the operative:
-ITransactionServiceTest.Java (For test the service method)
-TransactionRepositoryIntegrationTest (For test the repository and the integration with the BBDD).

Also, is possible comunnicate with the applicatión via REST.

# Create Transaction
POST localhost:8080/transaction

body: 
{"reference":"12345A" "account_iban":"ES9820385778983000760236", "date":"2019-07-16T16:55:42.000Z", "amount":193.38, "fee":3.18, "description":"Restaurant payment" } 

# Transaction search
GET localhost:8080/transactionSearch
 OPTIONALS PARAM:
 account_iban, orderByAmount(ascending,descending)
 
# Transaction status
GET localhost:8080/transactionStatus
 -REQUIRED PARAM:
 reference
 -Optional PARAM:
 channel("INTERNAL", "CLIENT", "ATM")

