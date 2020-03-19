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
import za.co.discovery_bank.data_transfer.ClientConvertedBalances;
import za.co.discovery_bank.entities.ClientAccount;
import za.co.discovery_bank.entities.CurrencyConversionRate;
import za.co.discovery_bank.repositories.ClientAccountRepository;
import za.co.discovery_bank.repositories.CurrencyConversionRateRepository;

/**
 *
 * @author ayuk
 */
@Service
public class CurrencyConversionRateService {

    @Autowired
    CurrencyConversionRateRepository conversionRateRepository;
    @Autowired
    ClientAccountRepository clientAccountRepository;
    
    ClientConvertedBalances clientConvertedBalance;
   
    public Object getAllAccounts() {
        return clientAccountRepository.findAll();
    }
    
    public Object getCurrencyById(String code){
        return conversionRateRepository.findById(code).get();
    }

    public Object getClientAccountById(Long id) {
        return clientAccountRepository.findById(id).get();
    }

    public Object balanceConversion(Long accountNumber) {
        List<ClientConvertedBalances> convertedAccounts = new ArrayList<>();
        List<ClientAccount> accounts = new ArrayList<>();
        List<ClientAccount> clientAccounts = (List<ClientAccount>) this.getAllAccounts();
        ClientAccount clientAccount = (ClientAccount) this.getClientAccountById(accountNumber);
        BigDecimal balance = null;

        if (clientAccount == null) {

            return "Account Does Not Exist";
        } else {
            for (ClientAccount clientAccount1 : clientAccounts) {
               CurrencyConversionRate ccr = (CurrencyConversionRate) this.getCurrencyById(clientAccount1.getCurrencyCode());
                if (clientAccount1.getClientId() == clientAccount.getClientId()) {
                balance = balance.valueOf(clientAccount1.getDisplayBalance().doubleValue() / ccr.getRate().doubleValue());
                clientAccount1.setDisplayBalance(balance);
                accounts.add(clientAccount1);
                }
            }
        }
        accounts.sort((c1, c2) -> c2.getDisplayBalance().compareTo(c1.getDisplayBalance()));

        for (ClientAccount account: accounts) {
            
            clientConvertedBalance = new ClientConvertedBalances(account.getClientId(), 
                    account.getClientAccountNumber(),account.getDisplayBalance(),account.getAccountTypeCode(),"ZAR");
        convertedAccounts.add(clientConvertedBalance);
        }
        
        return convertedAccounts;

    }

}
