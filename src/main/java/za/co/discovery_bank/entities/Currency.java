/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.discovery_bank.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.stereotype.Service;

/**
 *
 * @author ayuk
 */
@Entity
@Service
@Table(catalog = "discovery", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Currency.findAll", query = "SELECT c FROM Currency c"),
    @NamedQuery(name = "Currency.findByCurrencyCode", query = "SELECT c FROM Currency c WHERE c.currencyCode = :currencyCode"),
    @NamedQuery(name = "Currency.findByDecimalPlaces", query = "SELECT c FROM Currency c WHERE c.decimalPlaces = :decimalPlaces"),
    @NamedQuery(name = "Currency.findByDescription", query = "SELECT c FROM Currency c WHERE c.description = :description")})
public class Currency implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "CURRENCY_CODE", nullable = false, length = 3)
    private String currencyCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DECIMAL_PLACES", nullable = false)
    private int decimalPlaces;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(nullable = false, length = 255)
    private String description;

    public Currency() {
    }

    public Currency(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Currency(String currencyCode, int decimalPlaces, String description) {
        this.currencyCode = currencyCode;
        this.decimalPlaces = decimalPlaces;
        this.description = description;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public int getDecimalPlaces() {
        return decimalPlaces;
    }

    public void setDecimalPlaces(int decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (currencyCode != null ? currencyCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Currency)) {
            return false;
        }
        Currency other = (Currency) object;
        if ((this.currencyCode == null && other.currencyCode != null) || (this.currencyCode != null && !this.currencyCode.equals(other.currencyCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Currency{" + "currencyCode=" + currencyCode + ", decimalPlaces=" + decimalPlaces + ", description=" + description + '}';
    }

    
    
}
