/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.discovery_bank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.discovery_bank.entities.Atm;
import za.co.discovery_bank.entities.AtmAllocation;
import za.co.discovery_bank.repositories.AtmRepository;

/**
 *
 * @author ayuk
 */
@Service
public class AtmServices {
    
    @Autowired
    AtmRepository atmRepository;
    
    public Object getAllAtms(){
        return atmRepository.findAll();
    }
    
    public Object getAtmById(Integer id){
        return atmRepository.findById(id).get();
    }
      
    public Atm save(Atm atm){
    return  atmRepository.save(atm);
    }
}
