/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.discovery_bank.controllers;

/**
 *
 * @author ayuk
 */
public class Details {
    
    private int id;
    private String surname;
    private Long accountNumber;
    private String accountType;
    private double accountBalance;

    public Details() {
    }

    public Details(int id, String surname, Long accountNumber, String accountType, double accountBalance) {
        this.id = id;
        this.surname = surname;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.accountBalance = accountBalance;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return "Details{" + "ClientId=" + id + ", Surname=" + surname + ", Account Number=" + accountNumber + ", Account Type=" + accountType + ", Display Balance=" + accountBalance + '}';
    }
    
    
    
    
    
}
