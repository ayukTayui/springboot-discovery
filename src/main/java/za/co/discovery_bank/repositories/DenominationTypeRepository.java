/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.discovery_bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.discovery_bank.entities.Denomination;

/**
 *
 * @author ayuk
 */
@Repository
public interface DenominationTypeRepository extends JpaRepository<Denomination, String>{
    
}
