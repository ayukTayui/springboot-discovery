/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.discovery_bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.discovery_bank.entities.CreditCardLimit;

/**
 *
 * @author ayuk
 */
public interface CreditCardLimitRepository extends JpaRepository<CreditCardLimit,Integer> {
    
}
