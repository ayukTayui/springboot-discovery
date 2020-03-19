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
public class ClientFinancialDetails {
    
   private String title;
   private String clientName;
   private String clientSurname;
   private Integer clientId;
   private Double aggregateBalance;
   private Double aggregateLoan;
   private Double totalAggregate;

    public ClientFinancialDetails() {
    }

    public ClientFinancialDetails(String title, String clientName, String clientSurname, Integer clientId, Double aggregateBalance, Double aggregateLoan, Double totalAggregate) {
        this.title = title;
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.clientId = clientId;
        this.aggregateBalance = aggregateBalance;
        this.aggregateLoan = aggregateLoan;
        this.totalAggregate = totalAggregate;
    }

   
   
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Double getAggregateBalance() {
        return aggregateBalance;
    }

    public void setAggregateBalance(Double aggregateBalance) {
        this.aggregateBalance = aggregateBalance;
    }

    public Double getAggregateLoan() {
        return aggregateLoan;
    }

    public void setAggregateLoan(Double aggregateLoan) {
        this.aggregateLoan = aggregateLoan;
    }

    public Double getTotalAggregate() {
        return totalAggregate;
    }

    public void setTotalAggregate(Double totalAggregate) {
        this.totalAggregate = totalAggregate;
    }

    @Override
    public String toString() {
        return "ClientDetails{" + "title=" + title + ", clientName=" + clientName + ", clientSurname=" + clientSurname + ", clientId=" + clientId + ", aggregateBalance=" + aggregateBalance + ", aggregateLoan=" + aggregateLoan + ", totalAggregate=" + totalAggregate + '}';
    }
   
  
    
}
