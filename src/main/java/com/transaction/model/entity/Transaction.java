package com.transaction.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
public class Transaction {
	
	@JsonProperty("reference")
	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	
	@JsonProperty("account_iban")
    @NotBlank
    private String accountIban;
	
	@JsonProperty("date")
    private Date date;
	
	@JsonProperty("amount")
    @NotNull
    private Double amount;
	
	@JsonProperty("fee")    
    private Double fee;
	
	@JsonProperty("description")    
    private String description;	

}
