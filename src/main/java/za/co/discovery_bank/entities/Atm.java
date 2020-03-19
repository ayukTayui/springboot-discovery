/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.discovery_bank.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ayuk
 */
@Entity
@Table(catalog = "spring_discovery", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"NAME"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Atm.findAll", query = "SELECT a FROM Atm a"),
    @NamedQuery(name = "Atm.findByAtmId", query = "SELECT a FROM Atm a WHERE a.atmId = :atmId"),
    @NamedQuery(name = "Atm.findByName", query = "SELECT a FROM Atm a WHERE a.name = :name"),
    @NamedQuery(name = "Atm.findByLocation", query = "SELECT a FROM Atm a WHERE a.location = :location")})
public class Atm implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ATM_ID", nullable = false)
    private Integer atmId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(nullable = false, length = 10)
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(nullable = false, length = 255)
    private String location;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atmId")
    private Collection<AtmAllocation> atmAllocationCollection;

    public Atm() {
    }

    public Atm(Integer atmId) {
        this.atmId = atmId;
    }

    public Atm(Integer atmId, String name, String location) {
        this.atmId = atmId;
        this.name = name;
        this.location = location;
    }

    public Integer getAtmId() {
        return atmId;
    }

    public void setAtmId(Integer atmId) {
        this.atmId = atmId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @XmlTransient
    public Collection<AtmAllocation> getAtmAllocationCollection() {
        return atmAllocationCollection;
    }

    public void setAtmAllocationCollection(Collection<AtmAllocation> atmAllocationCollection) {
        this.atmAllocationCollection = atmAllocationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (atmId != null ? atmId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Atm)) {
            return false;
        }
        Atm other = (Atm) object;
        if ((this.atmId == null && other.atmId != null) || (this.atmId != null && !this.atmId.equals(other.atmId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "za.co.discovery_bank.entities.Atm[ atmId=" + atmId + " ]";
    }
    
}
