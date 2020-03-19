/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.discovery_bank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.discovery_bank.repositories.CreditCardLimitRepository;

/**
 *
 * @author ayuk
 */
@Service
public class CreditCardLimitServices {

    @Autowired
    CreditCardLimitRepository cardLimitRepository;
    
    public Object getAllCardLimits(){
        return cardLimitRepository.findAll();
    }
    
    public Object getCardLimitsById(Integer id){
        return cardLimitRepository.findById(id);
    }
}
