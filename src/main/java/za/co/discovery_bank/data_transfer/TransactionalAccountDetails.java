/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.discovery_bank.data_transfer;

/**
 *
 * @author ayuk
 */
public class TransactionalAccountDetails {
    
    private Integer clientId;
    private String clientSurname;
    private Long accountNumber;
    private String accountType;
    private Double displayBalance;

    public TransactionalAccountDetails() {
    }

    public TransactionalAccountDetails(Integer clientId, String clientSurname, Long accountNumber, String accountType, Double displayBalance) {
        this.clientId = clientId;
        this.clientSurname = clientSurname;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.displayBalance = displayBalance;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
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

    public Double getDisplayBalance() {
        return displayBalance;
    }

    public void setDisplayBalance(Double displayBalance) {
        this.displayBalance = displayBalance;
    }

    @Override
    public String toString() {
        return "TransactionalAccountDetails{" + "clientId=" + clientId + ", clientSurname=" + clientSurname + ", accountNumber=" + accountNumber + ", accountType=" + accountType + ", displayBalance=" + displayBalance + '}';
    }
    
    
}
