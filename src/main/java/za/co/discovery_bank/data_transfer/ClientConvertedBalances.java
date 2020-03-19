/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.discovery_bank.data_transfer;

import java.math.BigDecimal;

/**
 *
 * @author ayuk
 */
public class ClientConvertedBalances {
     
    private Integer clientId;
    private Long clientAccountNumber;
    private BigDecimal displayBalance;
    private String accountType;
    private String currencyCode;
   

    public ClientConvertedBalances() {
    }

    public ClientConvertedBalances(Integer clientId, Long clientAccountNumber, BigDecimal displayBalance, String accountType, String currencyCode) {
        this.clientId = clientId;
        this.clientAccountNumber = clientAccountNumber;
        this.displayBalance = displayBalance;
        this.accountType = accountType;
        this.currencyCode = currencyCode;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }
   
    public Long getClientAccountNumber() {
        return clientAccountNumber;
    }

    public void setClientAccountNumber(Long clientAccountNumber) {
        this.clientAccountNumber = clientAccountNumber;
    }

    public BigDecimal getDisplayBalance() {
        return displayBalance;
    }

    public void setDisplayBalance(BigDecimal displayBalance) {
        this.displayBalance = displayBalance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public String toString() {
        return "ClientConvertedBalances{" + "clientAccountNumber=" + clientAccountNumber + ", displayBalance=" + displayBalance + ", accountType=" + accountType + ", currencyCode=" + currencyCode + ", clientId=" + clientId + '}';
    }

    
    
    
    
}
