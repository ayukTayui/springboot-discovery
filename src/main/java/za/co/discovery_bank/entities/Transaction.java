/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.discovery_bank.entities;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.stereotype.Service;

/**
 *
 * @author ayuk
 */
@Entity
@Service
@Table(name = "TRANSACTION")
public class Transaction implements Serializable {

    @Id
    @Column(name = "TRANSACTION_ID")
    private int id;
    @Column(name = "TRANSACTION_TYPE")
    private String transactionType;
    @Column(name = "ACCOUNT_NUMBER ")
    private Long accountNumber;
    @Column(name = "CLIENT_ACCOUNT_TYPE")
    private String accountType;
    @Column(name = "TRANSACTION_AMOUNT ")
    private double amount;
    @Column(name = "DISPLAY_BALANCE ")
    private double currentBalance;
    @Column(name = "TRANS_REF")
    private String transReferences;
    @Column(name = " TRANSACTION_DATE ")
    private String transactionDate;
    private int atmId;

    public Transaction(int id, String transactionType, Long accountNumber, String accountType, double amount, double currentBalance, String transReferences, int atmId) {
        this.id = id;
        this.transactionType = transactionType;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.currentBalance = currentBalance;
        this.transReferences = transReferences;
        this.atmId = atmId;
        
         LocalDate transactionDate = LocalDate.now();
        this.transactionDate = transactionDate.toString();

    }

    

    public Transaction() {
        
        
        LocalDate transactionDate = LocalDate.now();
        this.transactionDate= transactionDate.toString();

    }

    public int getAtmId() {
        return atmId;
    }

    public void setAtmId(int atmId) {
        this.atmId = atmId;
    }

    
    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getTransReferences() {
        return transReferences;
    }

    public void setTransReferences(String transReferences) {
        this.transReferences = transReferences;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transaction)) {
            return false;
        }
        Transaction other = (Transaction) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Transaction{" + "id=" + id + ", transactionType=" + transactionType + ", accountNumber=" + accountNumber + ", accountType=" + accountType + ", amount=" + amount + ", currentBalance=" + currentBalance + ", transReferences=" + transReferences + ", transactionDate=" + transactionDate + '}';
    }

    
}
