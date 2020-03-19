/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.discovery_bank.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.discovery_bank.entities.Denomination;
import za.co.discovery_bank.repositories.DenominationRepository;

/**
 *
 * @author ayuk
 */
@Service
public class DenominationServices {
    
    @Autowired
    DenominationRepository denominationRepository;
    
    public Object getAllDenominations(){
        return denominationRepository.findAll();
    }
    
    public Object getDenominationById(Integer id){
        return denominationRepository.findById(id).get();
    }
    
    public Object sortAllDenominations(){
       List<Denomination> denominations = (List<Denomination>) this.getAllDenominations();
       denominations.sort((d1, d2) -> d2.getValue().compareTo(d1.getValue()));
        return denominations;
    }
    
    public Denomination saveDenominations(Denomination deno){
        
        return denominationRepository.save(deno);
    }
}
