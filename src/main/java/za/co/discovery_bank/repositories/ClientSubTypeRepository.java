/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.discovery_bank.repositories;

import org.springframework.data.repository.CrudRepository;
import za.co.discovery_bank.entities.ClientSubType;

/**
 *
 * @author ayuk
 */
public interface ClientSubTypeRepository extends CrudRepository<ClientSubType, String>{
    
}
