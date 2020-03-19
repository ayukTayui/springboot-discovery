/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.discovery_bank.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ayuk
 */
@Entity
@Table(name = "credit_card_limit", catalog = "spring_discovery", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CreditCardLimit.findAll", query = "SELECT c FROM CreditCardLimit c"),
    @NamedQuery(name = "CreditCardLimit.findByAccountLimit", query = "SELECT c FROM CreditCardLimit c WHERE c.accountLimit = :accountLimit"),
    @NamedQuery(name = "CreditCardLimit.findByCreditCardLimitId", query = "SELECT c FROM CreditCardLimit c WHERE c.creditCardLimitId = :creditCardLimitId")})
public class CreditCardLimit implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
     @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREDIT_CARD_LIMIT_ID", nullable = false)
    private Integer creditCardLimitId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACCOUNT_LIMIT", nullable = false, precision = 18, scale = 3)
    private BigDecimal accountLimit;
   
    @Basic(optional = false)
    @NotNull
    @Column(name = "CLIENT_ACCOUNT_NUMBER", nullable = false)
    private long clientAccountNumber;

    public CreditCardLimit() {
    }

    public CreditCardLimit(Integer creditCardLimitId) {
        this.creditCardLimitId = creditCardLimitId;
    }

    public CreditCardLimit(Integer creditCardLimitId, BigDecimal accountLimit) {
        this.creditCardLimitId = creditCardLimitId;
        this.accountLimit = accountLimit;
    }

    public BigDecimal getAccountLimit() {
        return accountLimit;
    }

    public void setAccountLimit(BigDecimal accountLimit) {
        this.accountLimit = accountLimit;
    }

    public Integer getCreditCardLimitId() {
        return creditCardLimitId;
    }

    public void setCreditCardLimitId(Integer creditCardLimitId) {
        this.creditCardLimitId = creditCardLimitId;
    }

    public long getClientAccountNumber() {
        return clientAccountNumber;
    }

    public void setClientAccountNumber(long clientAccountNumber) {
        this.clientAccountNumber = clientAccountNumber;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (creditCardLimitId != null ? creditCardLimitId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CreditCardLimit)) {
            return false;
        }
        CreditCardLimit other = (CreditCardLimit) object;
        if ((this.creditCardLimitId == null && other.creditCardLimitId != null) || (this.creditCardLimitId != null && !this.creditCardLimitId.equals(other.creditCardLimitId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "za.co.discovery_bank.entities.CreditCardLimit[ creditCardLimitId=" + creditCardLimitId + " ]";
    }
    
}
