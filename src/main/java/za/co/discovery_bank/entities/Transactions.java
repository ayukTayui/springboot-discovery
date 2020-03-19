/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.discovery_bank.entities;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ayuk
 */
@Entity
@Table(catalog = "spring_discovery", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transactions.findAll", query = "SELECT t FROM Transactions t"),
    @NamedQuery(name = "Transactions.findByTransactionId", query = "SELECT t FROM Transactions t WHERE t.transactionId = :transactionId"),
    @NamedQuery(name = "Transactions.findByAccountNumber", query = "SELECT t FROM Transactions t WHERE t.accountNumber = :accountNumber"),
    @NamedQuery(name = "Transactions.findByClientAccountType", query = "SELECT t FROM Transactions t WHERE t.clientAccountType = :clientAccountType"),
    @NamedQuery(name = "Transactions.findByTransactionAmount", query = "SELECT t FROM Transactions t WHERE t.transactionAmount = :transactionAmount"),
    @NamedQuery(name = "Transactions.findByAtmId", query = "SELECT t FROM Transactions t WHERE t.atmId = :atmId"),
    @NamedQuery(name = "Transactions.findByDisplayBalance", query = "SELECT t FROM Transactions t WHERE t.displayBalance = :displayBalance"),
    @NamedQuery(name = "Transactions.findByTransRef", query = "SELECT t FROM Transactions t WHERE t.transRef = :transRef"),
    @NamedQuery(name = "Transactions.findByTransactionDate", query = "SELECT t FROM Transactions t WHERE t.transactionDate = :transactionDate"),
    @NamedQuery(name = "Transactions.findByTransactionType", query = "SELECT t FROM Transactions t WHERE t.transactionType = :transactionType")})
public class Transactions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @NotNull
    @Column(name = "transaction_id", nullable = false)
    private Integer transactionId;
    @Column(name = "account_number")
    private Long accountNumber;
    @Size(max = 255)
    @Column(name = "client_account_type", length = 255)
    private String clientAccountType;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "transaction_amount", precision = 22)
    private Double transactionAmount;
    @Column(name = "atm_id")
    private Integer atmId;
    @Column(name = "display_balance", precision = 22)
    private Double displayBalance;
    @Size(max = 255)
    @Column(name = "trans_ref", length = 255)
    private String transRef;
    @Size(max = 255)
    @Column(name = "transaction_date", length = 255)
    private String transactionDate;
    @Size(max = 255)
    @Column(name = "transaction_type", length = 255)
    private String transactionType;
    

    public Transactions() {
        LocalDate date = LocalDate.now();
        this.transactionDate = date.toString();
    }

    public Transactions(Long accountNumber, String clientAccountType, Double transactionAmount, Integer atmId, Double displayBalance, String transRef, String transactionType) {
          LocalDate date = LocalDate.now();
        this.accountNumber = accountNumber;
        this.clientAccountType = clientAccountType;
        this.transactionAmount = transactionAmount;
        this.atmId = atmId;
        this.displayBalance = displayBalance;
        this.transRef = transRef;
        this.transactionDate = date.toString();
        this.transactionType = transactionType;
      
    }
    
    
    
    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getClientAccountType() {
        return clientAccountType;
    }

    public void setClientAccountType(String clientAccountType) {
        this.clientAccountType = clientAccountType;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Integer getAtmId() {
        return atmId;
    }

    public void setAtmId(Integer atmId) {
        this.atmId = atmId;
    }

    public Double getDisplayBalance() {
        return displayBalance;
    }

    public void setDisplayBalance(Double displayBalance) {
        this.displayBalance = displayBalance;
    }

    public String getTransRef() {
        return transRef;
    }

    public void setTransRef(String transRef) {
        this.transRef = transRef;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transactionId != null ? transactionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transactions)) {
            return false;
        }
        Transactions other = (Transactions) object;
        if ((this.transactionId == null && other.transactionId != null) || (this.transactionId != null && !this.transactionId.equals(other.transactionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Transactions{" + "transactionId=" + transactionId + ", accountNumber=" + accountNumber + ", clientAccountType=" + clientAccountType + ", transactionAmount=" + transactionAmount + ", atmId=" + atmId + ", displayBalance=" + displayBalance + ", transRef=" + transRef + ", transactionDate=" + transactionDate + ", transactionType=" + transactionType + '}';
    }

    
}
