/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.discovery_bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.discovery_bank.entities.AtmAllocation;
import za.co.discovery_bank.entities.Transactions;
import za.co.discovery_bank.services.AtmAllocationServices;
import za.co.discovery_bank.services.ClientAccountServices;
import za.co.discovery_bank.services.ClientServices;
import za.co.discovery_bank.services.CurrencyConversionRateService;
import za.co.discovery_bank.services.TransactionServices;

/**
 *
 * @author ayuk
 */
@RestController
@RequestMapping("/discovery_project/")
public class TransactionsController {

    @Autowired
    ClientAccountServices clientAccountServices;
    @Autowired
    CurrencyConversionRateService conversionRateServices;
    @Autowired
    TransactionServices transactionServices;
    @Autowired
    ClientServices clientServices;
    @Autowired
    AtmAllocationServices allocationServices;
   
     @GetMapping("allBalances/{id}")
    public Object displayAllClientsAccountBalances(@PathVariable(name = "id") long accountNumber) {
      
        return clientServices.getBalances(accountNumber);
    }
      @GetMapping("allBalancesConvertedToRands/{id}")
    public Object getConvertedClientAccountsToRand(@PathVariable(name = "id") long accountNumber) {
        
        return conversionRateServices.balanceConversion(accountNumber);
    }
   
    
    @GetMapping("Withdrawals")
    public Object withdraw(@RequestBody Transactions trans){
        
        return transactionServices.withdrawCash(trans);
    }
   
   
    
    @GetMapping("allClientsFinancialPosition")
    public Object financialPosition(){
        
        return clientServices.aggregateFinancialPosition();
    }
    
    @GetMapping("dispensingMachine")
    public Object cashDispenser(@RequestBody Transactions trans){
        
        return allocationServices.cashDispenser2(trans);
                        }


    @GetMapping("highestAccountBalance")
    public Object highestAccountBalance(){    
    return clientServices.checkAllHigiestAccountsBalances();
    }   

    
    
}
