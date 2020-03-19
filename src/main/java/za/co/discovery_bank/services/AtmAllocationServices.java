/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.discovery_bank.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.discovery_bank.entities.Atm;
import za.co.discovery_bank.entities.AtmAllocation;
import za.co.discovery_bank.entities.Denomination;
import za.co.discovery_bank.entities.Transactions;
import za.co.discovery_bank.repositories.AtmAllocationRepository;

/**
 *
 * @author ayuk
 */
@Service
public class AtmAllocationServices  implements AtmAllocationRepository{

    @Autowired
    AtmAllocationRepository atmAllocationRepository;
    @Autowired
    DenominationServices denominationServices;
    @Autowired
    AtmServices atmServices;

    int[] countz;
    double[] valuez;
    Transactions transaction;

    public Object getAllAllocations() {
        return atmAllocationRepository.findAll();
    }

    public Object getAllocationById(Integer id) {
        return atmAllocationRepository.findById(id).get();
    }

   // public AtmAllocation saveLocation(AtmAllocation allocation) {

      //  return atmAllocationRepository.save(allocation);
  //  }

    public Object cashDispenser(Transactions trans) {

        Denomination denomination = new Denomination();
        AtmAllocation atmAllocation = new AtmAllocation();

        String msg = null;

        if (trans.getTransactionAmount() <= 0) {
            return "Not A Valid Amount";
        } else {

            Atm atm = (Atm) atmServices.getAtmById(trans.getAtmId());

            if (atm != null) {

                List<AtmAllocation> locations = (List<AtmAllocation>) this.getAllAllocations();

                List<Denomination> sortedDenominations = (List<Denomination>) denominationServices.sortAllDenominations();

                int[] counts = new int[sortedDenominations.size()];

                countz = new int[counts.length];
                valuez = new double[sortedDenominations.size()];

                for (int i = 0; i < sortedDenominations.size(); i++) {

                    Denomination sortedDenos = sortedDenominations.get(i);

                    if (trans.getTransactionAmount() >= sortedDenos.getValue().doubleValue()) {

                        counts[i] = (int) (trans.getTransactionAmount() / sortedDenos.getValue().doubleValue());

                        trans.setTransactionAmount(trans.getTransactionAmount() % sortedDenos.getValue().doubleValue());

                        locations.get(i).setCount(counts[i] - locations.get(i).getCount());

                        valuez[i] = sortedDenominations.get(i).getValue().doubleValue();

                        denomination = sortedDenos;
                        atmAllocation = locations.get(i);

                     //   this.saveLocation(atmAllocation);
                        System.out.println(sortedDenominations.get(i).getValue() + "*" + counts[i]);
                    }

                }
            }

            msg = msg.valueOf(trans.getAccountNumber());
            String a = "******" + msg.substring(6, 9);
            msg = a;
        }

        return trans.getTransactionAmount() + "was withdrawn from this account number" + msg;
    }

    public Object cashDispenser2(Transactions trans) {

        Denomination denomination = new Denomination();
        AtmAllocation atmAllocation = new AtmAllocation();

        Map<String, Integer> map = new TreeMap<String, Integer>();

        String msg = null;

        if (trans.getTransactionAmount() <= 0) {
            return "Not A Valid Amount";
        } else {

            Atm atm = (Atm) atmServices.getAtmById(trans.getAtmId());

            if (atm != null) {

                List<AtmAllocation> locations = (List<AtmAllocation>) this.getAllAllocations();

                List<Denomination> sortedDenominations = (List<Denomination>) denominationServices.sortAllDenominations();

                int counts = 0;
                double amount = trans.getTransactionAmount();

                for (int i = 0; i < sortedDenominations.size(); i++) {

                    if (amount >= sortedDenominations.get(i).getValue().doubleValue()) {

                        counts = (int) (amount / sortedDenominations.get(i).getValue().doubleValue());

                        amount = amount % sortedDenominations.get(i).getValue().doubleValue();

                        //  atmAllocation = locations.stream().filter((at) -> at.getDenominationId() ==  sortedDenominations().get(i).getDenominationId()).collect(Collectors.toList()).get(0);
                        for (int a = 0; a < locations.size(); a++) {
                            if (locations.get(a).getDenominationId() == sortedDenominations.get(a).getDenominationId()) {
                                atmAllocation = locations.get(a);
                                break;
                            }
                        }
                        atmAllocation.setCount(atmAllocation.getCount() - counts);

                        this.save(atmAllocation);

                        map.put(sortedDenominations.get(i).getValue().toString(), counts);
                    }

                }

                System.out.println(map);
            }

            msg = String.valueOf(trans.getAccountNumber());
            msg = "******" + msg.substring(6, 9);

        }

        return trans.getTransactionAmount() + "was withdrawn from this account number" + msg;
    }

    @Override
    public <S extends AtmAllocation> S save(S s) {
  
        return atmAllocationRepository.save(s);
    }

    @Override
    public <S extends AtmAllocation> Iterable<S> saveAll(Iterable<S> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional<AtmAllocation> findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existsById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<AtmAllocation> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<AtmAllocation> findAllById(Iterable<Integer> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(AtmAllocation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll(Iterable<? extends AtmAllocation> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
