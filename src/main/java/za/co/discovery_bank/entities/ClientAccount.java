/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.discovery_bank.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ayuk
 */
@Entity
@Table(name = "client_account", catalog = "spring_discovery", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClientAccount.findAll", query = "SELECT c FROM ClientAccount c"),
    @NamedQuery(name = "ClientAccount.findByClientAccountNumber", query = "SELECT c FROM ClientAccount c WHERE c.clientAccountNumber = :clientAccountNumber"),
    @NamedQuery(name = "ClientAccount.findByDisplayBalance", query = "SELECT c FROM ClientAccount c WHERE c.displayBalance = :displayBalance")})
public class ClientAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CLIENT_ACCOUNT_NUMBER", nullable = false)
    private Long clientAccountNumber;
    @Column(name = "DISPLAY_BALANCE", precision = 18, scale = 3)
    private BigDecimal displayBalance;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CLIENT_ID", nullable = false)
    private int clientId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "ACCOUNT_TYPE_CODE", nullable = false, length = 10)
    private String accountTypeCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "CURRENCY_CODE", nullable = false, length = 3)
    private String currencyCode;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientAccountNumber")
    private Collection<CreditCardLimit> creditCardLimitCollection;

    public ClientAccount() {
    }

    public ClientAccount(Long clientAccountNumber) {
        this.clientAccountNumber = clientAccountNumber;
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

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getAccountTypeCode() {
        return accountTypeCode;
    }

    public void setAccountTypeCode(String accountTypeCode) {
        this.accountTypeCode = accountTypeCode;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @XmlTransient
    public Collection<CreditCardLimit> getCreditCardLimitCollection() {
        return creditCardLimitCollection;
    }

    public void setCreditCardLimitCollection(Collection<CreditCardLimit> creditCardLimitCollection) {
        this.creditCardLimitCollection = creditCardLimitCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clientAccountNumber != null ? clientAccountNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClientAccount)) {
            return false;
        }
        ClientAccount other = (ClientAccount) object;
        if ((this.clientAccountNumber == null && other.clientAccountNumber != null) || (this.clientAccountNumber != null && !this.clientAccountNumber.equals(other.clientAccountNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ClientAccount{" + "clientAccountNumber=" + clientAccountNumber + ", displayBalance=" + displayBalance + ", clientId=" + clientId + ", accountTypeCode=" + accountTypeCode + ", currencyCode=" + currencyCode + '}';
    }

    
    
}
