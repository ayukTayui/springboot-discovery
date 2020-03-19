/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.discovery_bank.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.discovery_bank.entities.ClientAccount;
import za.co.discovery_bank.entities.CreditCardLimit;
import za.co.discovery_bank.entities.Transactions;
import za.co.discovery_bank.repositories.ClientAccountRepository;

/**
 *
 * @author ayuk
 */
@Service
public class ClientAccountServices {

    @Autowired
    ClientAccountRepository clientAccountRepository;
    @Autowired
    CurrencyConversionRateService conversionRateService;
    @Autowired
    CreditCardLimitServices cardServices;
 
        
    public Object getAllClientAccounts() {

        return clientAccountRepository.findAll();
    }

    public Object getAccountById(long accountNumber) {
        return clientAccountRepository.findById(accountNumber).get();
    }
    

  
    
}
