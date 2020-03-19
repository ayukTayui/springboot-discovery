/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.discovery_bank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.discovery_bank.repositories.CurrencyRepository;

/**
 *
 * @author ayuk
 */
@Service
public class CurrencyServices {
    
    @Autowired
    CurrencyRepository currencyRepository;
    
    public Object getAllCurrencies(){
        
        return currencyRepository.findAll();
    }
    
    public Object getCurrencyById(String id){
       
        return currencyRepository.findById(id).get();
    }
}
