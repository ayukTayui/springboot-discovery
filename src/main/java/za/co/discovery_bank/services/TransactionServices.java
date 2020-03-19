/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.discovery_bank.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.discovery_bank.entities.Atm;
import za.co.discovery_bank.entities.AtmAllocation;
import za.co.discovery_bank.entities.ClientAccount;
import za.co.discovery_bank.entities.CreditCardLimit;
import za.co.discovery_bank.entities.Denomination;
import za.co.discovery_bank.entities.Transactions;
import za.co.discovery_bank.repositories.TransactionRepository;

/**
 *
 * @author ayuk
 */
@Service
public class TransactionServices {

    @Autowired
    ClientAccountServices clientAccountService;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    DenominationServices denominationServices;
    @Autowired
    AtmAllocationServices allocationServices;
    @Autowired
    AtmServices atmServices;
    @Autowired
    CreditCardLimitServices cardLimitServices;
    
    
    public Object withdrawCash(Transactions trans){
        List<String> result = new ArrayList<>();
        CreditCardLimit cardLimit = new CreditCardLimit();
        Denomination denomination = new Denomination();
        AtmAllocation atmAllocation = new AtmAllocation();
        ClientAccount account = new ClientAccount();
        String msg = null;
        
        int count = 0;
        double value = 0;
        ////
        if(trans.getTransactionAmount() <= 0){
            return "Invalid Amount";
        }else {
            
        account = (ClientAccount) clientAccountService.getAccountById(trans.getAccountNumber());
            
            if(account != null){
            
                List<CreditCardLimit> limits = (List<CreditCardLimit>) cardLimitServices.getAllCardLimits();
               
                for (CreditCardLimit limit : limits) {
                    if(limit.getClientAccountNumber() == trans.getAccountNumber()){
                    cardLimit = limit;    
                    }
                }
                Atm atm = (Atm) atmServices.getAtmById(trans.getAtmId());
                
                if(atm != null){
                    
                    List<AtmAllocation> locations = (List<AtmAllocation>) allocationServices.getAllAllocations();
                    
                    List<Denomination> sortedDenominations = (List<Denomination>) denominationServices.sortAllDenominations();
                   
                        int[] counts = new int[sortedDenominations.size()];
                        
                    for (int i=0; i<sortedDenominations.size(); i++) {
                        
                        Denomination sortedDenomination = sortedDenominations.get(i);
                        
                        if(trans.getTransactionAmount() >= sortedDenomination.getValue().doubleValue()){
                            
                            counts[i] = (int) (trans.getTransactionAmount() / sortedDenomination.getValue().doubleValue());
                            
                            trans.setTransactionAmount(trans.getTransactionAmount() % sortedDenomination.getValue().doubleValue());
                            
                            locations.get(i).setCount(counts[i] - locations.get(i).getCount());
                            
                            count = counts[i];
                            value = sortedDenominations.get(i).getValue().doubleValue();
                            denomination = sortedDenomination;
                            atmAllocation = locations.get(i);
                            trans = new Transactions(trans.getAccountNumber()
                                    ,account.getAccountTypeCode(),
                                    trans.getTransactionAmount(),
                                    trans.getAtmId(),account.getDisplayBalance()
                                            .doubleValue(),trans.getTransRef() 
                                    ,"Withdrawals" );
                          
               // allocationServices.saveLocation(atmAllocation);
                transactionRepository.save(trans); 

                            System.out.println(sortedDenominations.get(i).getValue() + "*" + counts[i]);
                        }
                        
                    }
                    
                }
                
                 if (account.getAccountTypeCode().equalsIgnoreCase("CCRD")){
                     
                     if (trans.getTransactionAmount() > cardLimit.getAccountLimit().doubleValue()){
                         
                         return "Your Exceeded Credit Limits Try A Lesser Amount";
                     }else{
                         
                         double balance = account.getDisplayBalance().doubleValue() - trans.getTransactionAmount();
                         account.setDisplayBalance(BigDecimal.valueOf(balance));
                         
                         clientAccountService.clientAccountRepository.save(account);
//                          allocationServices.saveLocation(atmAllocation);
                          transactionRepository.save(trans);
                          
                          msg = String.valueOf(trans.getAccountNumber());
                          String a = "******" + msg.substring(6);
                          msg = a;
                     
                          return trans.getTransactionAmount() + "Was withraw from this account number " + a;
                     }
                     
                 }else if(account.getAccountTypeCode().equalsIgnoreCase("CHQ")){
                     
                     if(trans.getTransactionAmount() > 10000){
                         
                       return "Insufficient Fund";
                     }else {
                         
                         double balance = account.getDisplayBalance().doubleValue()
                                 - trans.getTransactionAmount();
                                account.setDisplayBalance(BigDecimal.valueOf(balance));
                              
                         clientAccountService.clientAccountRepository.save(account);
                         // allocationServices.saveLocation(atmAllocation);
                          transactionRepository.save(trans); 
                          
                          return account.toString();
                     }
                 }else if(account.getAccountTypeCode().equalsIgnoreCase("SVGS")){
                     if(account.getDisplayBalance().doubleValue() < trans.getTransactionAmount()){
                         
                         return "Insufficient Fund";
                     }else {
                         
                         
                            double balance = account.getDisplayBalance().doubleValue() - trans.getTransactionAmount();

                            account.setDisplayBalance(BigDecimal.valueOf(balance));
                            
                          clientAccountService.clientAccountRepository.save(account);
                         // allocationServices.saveLocation(atmAllocation);
                          transactionRepository.save(trans); 
                         
                          System.out.println("Successful Operation");
                          
                           msg = String.valueOf(trans.getAccountNumber());
                          String b = "******" + msg.substring(6);
                          msg = b;
                          
                          return trans.getTransactionAmount() + "Was withraw from this account number " + b;
                     } 
                 }
                
            }else {
                
                return "Account Does Not Exist";
            }
            
        }
        return "Operation Failed";
    }
       
    
   
}
