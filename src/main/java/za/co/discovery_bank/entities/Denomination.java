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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @NamedQuery(name = "Denomination.findAll", query = "SELECT d FROM Denomination d"),
    @NamedQuery(name = "Denomination.findByDenominationId", query = "SELECT d FROM Denomination d WHERE d.denominationId = :denominationId"),
    @NamedQuery(name = "Denomination.findByValue", query = "SELECT d FROM Denomination d WHERE d.value = :value"),
    @NamedQuery(name = "Denomination.findByDenominationTypeCode", query = "SELECT d FROM Denomination d WHERE d.denominationTypeCode = :denominationTypeCode")})
public class Denomination implements Serializable,Comparable<Denomination> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DENOMINATION_ID", nullable = false)
    private Integer denominationId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false, precision = 65, scale = 4)
    private BigDecimal value;
    @Size(max = 1)
    @Column(name = "DENOMINATION_TYPE_CODE", length = 1)
    private String denominationTypeCode;

    public Denomination() {
    }

    public Denomination(Integer denominationId) {
        this.denominationId = denominationId;
    }

    public Denomination(Integer denominationId, BigDecimal value) {
        this.denominationId = denominationId;
        this.value = value;
    }

    public Integer getDenominationId() {
        return denominationId;
    }

    public void setDenominationId(Integer denominationId) {
        this.denominationId = denominationId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getDenominationTypeCode() {
        return denominationTypeCode;
    }

    public void setDenominationTypeCode(String denominationTypeCode) {
        this.denominationTypeCode = denominationTypeCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (denominationId != null ? denominationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Denomination)) {
            return false;
        }
        Denomination other = (Denomination) object;
        if ((this.denominationId == null && other.denominationId != null) || (this.denominationId != null && !this.denominationId.equals(other.denominationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Denomination{" + "denominationId=" + denominationId + ", value=" + value + ", denominationTypeCode=" + denominationTypeCode + '}';
    }

    @Override
    public int compareTo(Denomination o) {
        
        
                if (o.getValue().doubleValue() == this.getValue().doubleValue()) {
                    return 0;
                } else if (o.getValue().doubleValue() < this.getValue().doubleValue()) {
                    return -1;
                } else {
                    return 1;
                }
        
        
       
    }

    
    
}
