/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.discovery_bank.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.discovery_bank.data_transfer.ClientFinancialDetails;
import za.co.discovery_bank.entities.Client;
import za.co.discovery_bank.entities.ClientAccount;
import za.co.discovery_bank.repositories.ClientAccountRepository;
import za.co.discovery_bank.repositories.ClientRepository;

/**
 *
 * @author ayuk
 */
@Service
public class ClientServices {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ClientAccountRepository clientAccountRepository;
    @Autowired
    ClientAccountServices clientAccountServices;
    
    ClientFinancialDetails clientFinancialDetails;

    public Object aggregateFinancialPosition() {

        List<ClientFinancialDetails> listOfFinancialPositionPerClient = new ArrayList<>();
        double aggregateLoan = 0;
        double aggregateBalance = 0;
        double totalAggregate = 0;

        List<Client> clientList = clientRepository.findAll();

        main:
        for (Client clientList1 : clientList) {

            if (clientList1.getClientId() <= 15) {
                
                List<ClientAccount> clientAccounts = clientAccountRepository.findAll();

                List<ClientAccount> accounts = new ArrayList<>();

                clientAccounts.stream().filter((clientAccount) -> (clientAccount.getClientId() == clientList1.getClientId())).forEach((clientAccount) -> {
                    accounts.add(clientAccount);
                });

                for (ClientAccount ca : accounts) {
                    if (ca.getDisplayBalance().doubleValue() <= 0) {

                        aggregateLoan += ca.getDisplayBalance().doubleValue();

                    } else {

                        aggregateBalance += ca.getDisplayBalance().doubleValue();
                    }
                }

                totalAggregate = aggregateBalance + aggregateLoan;

                clientFinancialDetails = new ClientFinancialDetails(clientList1.getTitle(), clientList1.getName(), clientList1.getSurname(),
                        clientList1.getClientId(), aggregateBalance, aggregateLoan, totalAggregate);

                listOfFinancialPositionPerClient.add(clientFinancialDetails);

                aggregateLoan = 0;
                aggregateBalance = 0;
                totalAggregate = 0;

            }
        }
        return listOfFinancialPositionPerClient;
    }

    public Object getTransactionalAccountBalances() {
        List<Client> clients = clientRepository.findAll();
        List<ClientAccount> clientAccounts = clientAccountRepository.findAll();
        List<ClientAccount> highestAccount = new ArrayList<>();
        List<ClientAccount> perAccount = new ArrayList<>();

        for (int i = 0; i < clientAccounts.size(); i++) {
            if (clientAccounts.get(i).getClientId() == clients.get(i).getClientId()) {
                perAccount.add(clientAccounts.get(i));
            }
        }
        perAccount.sort((ca1, ca2) -> ca2.getDisplayBalance().compareTo(ca1.getDisplayBalance()));
        highestAccount.add(perAccount.get(0));

        return highestAccount;
    }
    
    public Object getAllClientAccountsHighiestBalance(){
        List<Client> clients = clientRepository.findAll();
        
        List<ClientAccount> highestAccount = new ArrayList<>();
        List<ClientAccount> perAccount = new ArrayList<>();
        
        for (Client client: clients) {
          
            if(client.getClientId() <= 15){
                
            List<ClientAccount> clientAccounts = clientAccountRepository.findAll();
            
             for (int i = 0; i < clientAccounts.size(); i++) {
            if (clientAccounts.get(i).getClientId() == clients.get(i).getClientId()) {
                perAccount.add(clientAccounts.get(i));
            }
         }
        perAccount.sort((ca1, ca2) -> ca2.getDisplayBalance()
                .compareTo(ca1.getDisplayBalance()));
        highestAccount.add(perAccount.get(0));

        }
        
        
        }
        return highestAccount;
    }
    
    
    public Object getBalances(long accountNumber) {
        List<ClientAccount> customerAccountz = new ArrayList<>();
        List<ClientAccount> clientAccounts = new ArrayList<>();
        ClientAccount clientAccount = (ClientAccount) clientAccountServices.getAccountById(accountNumber);
        if (clientAccount == null) {
            return "Account Not Found";
        } else {
            clientAccounts = (List<ClientAccount>) clientAccountServices.getAllClientAccounts();
            for (ClientAccount cl : clientAccounts) {
                if (cl.getClientId() == clientAccount.getClientId()) {
                    customerAccountz.add(cl);
                }
            }
        }
        customerAccountz.sort((c1, c2) -> c2.getDisplayBalance().compareTo(c1.getDisplayBalance()));

        return customerAccountz;
    }

 
    
    
    
    
    public Object checkAllHigiestAccountsBalances(){
        List<ClientAccount> allAccounts = (List<ClientAccount>) 
                clientAccountServices.getAllClientAccounts();
        List<ClientAccount> singleClientsAccounts;
        singleClientsAccounts = new ArrayList<>();
        ClientAccount account = null;
        List<ClientAccount> allHighestAccountBalances = new ArrayList<>();
        
        for (ClientAccount acc : allAccounts ) {
                        
            for(int a = 0; a < allAccounts.size(); a++){
                if(acc.getClientId() == allAccounts.get(a).getClientId()){
                    singleClientsAccounts.add(allAccounts.get(a));
                }
            }
                    singleClientsAccounts.sort((c1, c2) -> c2.getDisplayBalance().compareTo(c1.getDisplayBalance()));
                    
                allHighestAccountBalances.add(singleClientsAccounts.get(0));
                   
                    
        }
        
        
        
        return allHighestAccountBalances;
    }
     
    
    
}
