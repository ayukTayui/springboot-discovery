/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.discovery_bank.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "client_sub_type", catalog = "spring_discovery", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClientSubType.findAll", query = "SELECT c FROM ClientSubType c"),
    @NamedQuery(name = "ClientSubType.findByClientSubTypeCode", query = "SELECT c FROM ClientSubType c WHERE c.clientSubTypeCode = :clientSubTypeCode"),
    @NamedQuery(name = "ClientSubType.findByClientTypeCode", query = "SELECT c FROM ClientSubType c WHERE c.clientTypeCode = :clientTypeCode"),
    @NamedQuery(name = "ClientSubType.findByDescription", query = "SELECT c FROM ClientSubType c WHERE c.description = :description")})
public class ClientSubType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "CLIENT_SUB_TYPE_CODE", nullable = false, length = 4)
    private String clientSubTypeCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "CLIENT_TYPE_CODE", nullable = false, length = 2)
    private String clientTypeCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(nullable = false, length = 255)
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientSubTypeCode")
    private Collection<Client> clientCollection;

    public ClientSubType() {
    }

    public ClientSubType(String clientSubTypeCode) {
        this.clientSubTypeCode = clientSubTypeCode;
    }

    public ClientSubType(String clientSubTypeCode, String clientTypeCode, String description) {
        this.clientSubTypeCode = clientSubTypeCode;
        this.clientTypeCode = clientTypeCode;
        this.description = description;
    }

    public String getClientSubTypeCode() {
        return clientSubTypeCode;
    }

    public void setClientSubTypeCode(String clientSubTypeCode) {
        this.clientSubTypeCode = clientSubTypeCode;
    }

    public String getClientTypeCode() {
        return clientTypeCode;
    }

    public void setClientTypeCode(String clientTypeCode) {
        this.clientTypeCode = clientTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<Client> getClientCollection() {
        return clientCollection;
    }

    public void setClientCollection(Collection<Client> clientCollection) {
        this.clientCollection = clientCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clientSubTypeCode != null ? clientSubTypeCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClientSubType)) {
            return false;
        }
        ClientSubType other = (ClientSubType) object;
        if ((this.clientSubTypeCode == null && other.clientSubTypeCode != null) || (this.clientSubTypeCode != null && !this.clientSubTypeCode.equals(other.clientSubTypeCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ClientSubType{" + "clientSubTypeCode=" + clientSubTypeCode + ", clientTypeCode=" + clientTypeCode + ", description=" + description + '}';
    }

    
    
}
