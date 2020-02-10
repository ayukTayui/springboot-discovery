/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.discovery_bank.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import za.co.discovery_bank.entities.Atm;
import za.co.discovery_bank.entities.AtmAllocation;
import za.co.discovery_bank.entities.Denomination;

/**
 *
 * @author ayuk
 */

public class AtmService{
    
    private int atmId;
    private BigDecimal amount;
    private Long accountNumber;
    
    
    EntityManagerFactory emf;
    EntityManager em;
    
   
    List<Denomination> denominations;
    List <AtmAllocation> atmLocations;
    List<Atm> atms ;
    
    Map<Double, Integer> counts = new TreeMap<>();

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Denomination> getDenominations() {
        return denominations;
    }

    public void setDenominations(List<Denomination> denominations) {
        this.denominations = denominations;
    }

    public List<AtmAllocation> getAtmLocations() {
        return atmLocations;
    }

    public void setAtmLocations(List<AtmAllocation> atmLocations) {
        this.atmLocations = atmLocations;
    }

    public List<Atm> getAtms() {
        return atms;
    }

    public void setAtms(List<Atm> atms) {
        this.atms = atms;
    }

    public Map<Double, Integer> getCounts() {
        return counts;
    }

    public void setCounts(Map<Double, Integer> counts) {
        this.counts = counts;
    }
    
    public int getAtmId() {
        return atmId;
    }

    public void setAtmId(int atmId) {
        this.atmId = atmId;
    }

   
	
      public List<Denomination> denoMethod(){
     emf = Persistence.createEntityManagerFactory("discovery");
        em = emf.createEntityManager();  
            
        this.denominations = em.createQuery("SELECT d FROM Denomination d").getResultList();
        
            Collections.sort(denominations);
            
     return this.denominations;
    
}  
  
    
    public Object dispenser(BigDecimal amount){
        emf = Persistence.createEntityManagerFactory("discovery");
        em = emf.createEntityManager();  
       
        List<Denomination> denos = new ArrayList<>();
        denos = denoMethod();
        this.amount = amount;
        
              for (int i = 0; i < denos.size(); i++) {
          
                  if(this.amount.doubleValue() > denos.get(i).getValue().doubleValue() ||
                          this.amount.doubleValue() == denos.get(i).getValue().doubleValue()){
                      
                      Double balance = denos.get(i).getValue().doubleValue()
                              / this.amount.doubleValue();
                      
                      return balance;
                  }
                  else {
                      
                      return null;
                  }
        }
 
        
        
        return "Not Good " + amount;
                    
    }
    
    
    
    public BigDecimal dispenser(AtmService service){ 
        emf = Persistence.createEntityManagerFactory("discovery");
        em = emf.createEntityManager();  
         List<Denomination> denos = new ArrayList<>();
        denos = denoMethod();
        
        for (int i = 0; i < denos.size(); i++) {
            
            if(service.getAmount().doubleValue() == 
                    denos.get(i).getValue().doubleValue()){
                
                if(service.getAmount().doubleValue() > 
                        denos.get(i).getValue().doubleValue()){
                    
                  Double  amount = service.getAmount().doubleValue() / 
                          denos.get(i).getValue().doubleValue();
                  
                  service.setAmount(BigDecimal.valueOf(amount));
                  
                }
            }
            
        }
        
        return service.getAmount();
    }
    
}
