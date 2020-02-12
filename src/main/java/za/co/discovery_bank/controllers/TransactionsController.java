/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.discovery_bank.controllers;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.discovery_bank.entities.Atm;
import za.co.discovery_bank.entities.AtmAllocation;
import za.co.discovery_bank.entities.Client;
import za.co.discovery_bank.entities.ClientAccount;
import za.co.discovery_bank.entities.CreditCardLimit;
import za.co.discovery_bank.entities.Currency;
import za.co.discovery_bank.entities.CurrencyConversionRate;
import za.co.discovery_bank.entities.Denomination;
import za.co.discovery_bank.entities.DenominationType;
import za.co.discovery_bank.entities.Transaction;

/**
 *
 * @author ayuk
 */
@RestController
@RequestMapping("/discovery/")
public class TransactionsController {

    @Autowired
    Atm atm;

    List<Atm> atms;

    @Autowired
    AtmAllocation atmLocation;
    List<AtmAllocation> atmLocations;

    @Autowired
    ClientAccount clientAccount;

    List<Client> clients;

    @Autowired
    Client client;
    List<ClientAccount> clientAccounts;

    @Autowired
    CreditCardLimit creditCardLimit;
    List<CreditCardLimit> creditCardLimits;

    @Autowired
    Currency currency;
    List<Currency> currencies;

    @Autowired
    CurrencyConversionRate conversionRate;

    List<CurrencyConversionRate> conversionRates;

    @Autowired
    Transaction transaction;

    BigDecimal balance;

    int count;

    EntityManagerFactory emf;
    EntityManager em;

    @Autowired
    AtmAllocation allocation;
    List<AtmAllocation> allocations;

    @Autowired
    Denomination denomination;
    List<Denomination> denominations;

    @Autowired
    DenominationType denomintaionType;
    List<DenominationType> denomintaionTypes;

    public TransactionsController() {

    }

    public ClientAccount id(Long id) {

        ClientAccount hjhj = em.find(ClientAccount.class, id);

        return hjhj;

    }

    @GetMapping("balance/{id}")
    public Object balanceDisplay(@PathVariable(name = "id") Long accountNumber) {

        emf = Persistence.createEntityManagerFactory("discovery");
        em = emf.createEntityManager();

        em.getTransaction().begin();

        clientAccount = em.find(ClientAccount.class, accountNumber);

        //this.id(clientAccount.getClientAccountNumber());
        if (clientAccount == null) {
            return "nOT foUND";
        } else {

            clientAccounts = em.createQuery("SELECT a FROM ClientAccount a"
                    + " WHERE a.clientId =" + clientAccount.getClientId()).getResultList();

            Comparator<ClientAccount> com = (ClientAccount o1, ClientAccount o2) -> {

                if (o1.getDisplayBalance().doubleValue() == o2.getDisplayBalance().doubleValue()) {
                    return 0;
                } else if (o1.getDisplayBalance().doubleValue() > o2.getDisplayBalance().doubleValue()) {
                    return -1;
                } else {
                    return 1;
                }

            };
            System.out.println("\n Before Sortint  \n");
            System.out.println(clientAccounts);
            Collections.sort(this.clientAccounts, com);

            System.out.print("\n After Sortint  \n");
            clientAccounts.stream().forEach((c) -> {
                System.out.print(c);
            });
            return this.clientAccounts;
        }

    }

    @GetMapping("balanceConversion")
    public Object balanceConversion(@RequestBody Transaction transaction) {

        emf = Persistence.createEntityManagerFactory("discovery");
        em = emf.createEntityManager();

        em.getTransaction().begin();

        clientAccount = em.find(ClientAccount.class, transaction.getAccountNumber());

        //this.id(clientAccount.getClientAccountNumber());
        if (clientAccount == null) {
            return "nOT foUND";
        } else {

            clientAccounts = em.createQuery("SELECT a FROM ClientAccount a"
                    + " WHERE a.clientId ="
                    + clientAccount.getClientId()).getResultList();

            for (int a = 0; a < clientAccounts.size(); a++) {
                conversionRate = em.find(CurrencyConversionRate.class, clientAccounts
                        .get(a).getCurrencyCode());
                if (conversionRate.getConversionIndicator().equals("/")) {

                    balance = balance.valueOf(clientAccounts.get(a).
                            getDisplayBalance().doubleValue()
                            / conversionRate.getRate().doubleValue());

                    clientAccounts.get(a).setDisplayBalance(balance);

                }

            }

            Comparator<ClientAccount> com = (ClientAccount o1, ClientAccount o2) -> {

                if (o1.getDisplayBalance().doubleValue() == o2.getDisplayBalance().doubleValue()) {
                    return 0;
                } else if (o1.getDisplayBalance().doubleValue() > o2.getDisplayBalance().doubleValue()) {
                    return -1;
                } else {
                    return 1;
                }

            };
            System.out.println("\n Before Sortint  \n");
            System.out.println(clientAccounts);
            Collections.sort(this.clientAccounts, com);

            System.out.print("\n After Sortint  \n");
            clientAccounts.stream().forEach((c) -> {
                System.out.print(c);
            });

            return clientAccounts;
        }

    }

    /**
     *
     * @GetMapping("/withdrawals") public String withdrawals(@RequestBody
     * Transaction transaction) { emf =
     * Persistence.createEntityManagerFactory("discovery"); em =
     * emf.createEntityManager(); em.getTransaction().begin();
     *
     * // Checking If the Amount is Valid if (false) { return "Invalid Amount
     * Coins Not Allowed!!!"; } else { clientAccount =
     * em.find(ClientAccount.class, transaction.getAccountNumber());
     *
     * // checking if the Account Number Exist if (clientAccount != null) {
     *
     * List<CreditCardLimit> limits = em.createQuery("SELECT ccl FROM
     * CreditCardLimit ccl").getResultList(); for (CreditCardLimit limit :
     * limits) { if (limit.getClientAccountNumber().getClientAccountNumber() ==
     * transaction.getAccountNumber()) { this.creditCardLimit = limit; }
     *
     * }
     *
     * this.atm = em.find(Atm.class, transaction.getAtmId());
     *
     * if (atm != null) {
     *
     * this.allocations = em.createQuery("SELECT a FROM AtmAllocation a WHERE
     * a.atmId =" + transaction.getAtmId()).getResultList();
     *
     * List<Denomination> denos = em.createQuery("SELECT de FROM Denomination
     * de").getResultList(); //List<BigDecimal> denov = em.createQuery("SELECT
     * d.value FROM Denomination d").getResultList(); denos.sort((d1, d2) ->
     * d2.getValue().compareTo(d1.getValue())); int[] counts = new
     * int[denos.size()]; double bigDecimal = 0.0; for (int i = 0; i < denos.size(); i++) {
     * this.denomination = denos.get(i);
     * if (transaction.getAmount() >= denos.get(i).getValue().doubleValue()) {
     *
     * counts[i] = (int) (transaction.getAmount() /
     * denos.get(i).getValue().doubleValue()); int count = counts[i];
     * transaction.setAmount(transaction.getAmount() %
     * denos.get(i).getValue().doubleValue()); this.allocation =
     * this.allocations.stream().filter((aa) -> aa.getDenominationId() ==
     * denomination.getDenominationId()).collect(Collectors.toList()).get(0);
     * this.allocation.setCount(this.allocation.getCount() - count); } }
     *
     * List<String> summary = new ArrayList<>(); for (int k = 0; k < counts.length; k++) {
     * if (counts[k] != 0) {
     * summary.add(denos.get(k).getValue() + " * " + counts[k] + " = " + (denos.get(k).getValue().doubleValue() * counts[k]) + "\n");
     * bigDecimal += counts[k] * denos.get(k).getValue().doubleValue();
     * }
     * }
     * if (clientAccount.getAccountTypeCode().equalsIgnoreCase("CCRD")) {
     * if (transaction.getAmount() >
     * creditCardLimit.getAccountLimit().doubleValue()) {
     *
     * return "Exceed Limit Try a Lesser Amount"; } else { try { double balance
     * = clientAccount.getDisplayBalance().doubleValue() - bigDecimal;
     *
     * clientAccount.setDisplayBalance(BigDecimal.valueOf(balance));
     *
     * this.transaction = transaction;
     *
     * em.persist(allocation); em.persist(clientAccount); //
     * em.persist(this.transaction);
     *
     * em.getTransaction().commit();
     *
     * // System.err.println("Successfull"); return summary.toString();
     *
     * } catch (Exception e) { em.getTransaction().rollback(); } }
     *
     * } else if (clientAccount.getAccountTypeCode().equalsIgnoreCase("CHQ")) {
     *
     * try { if (transaction.getAmount() > 10000) {
     *
     * return "Insufficient Fund";
     *
     * } else {
     *
     * double balance = clientAccount.getDisplayBalance().doubleValue() -
     * bigDecimal;
     *
     * clientAccount.setDisplayBalance(BigDecimal.valueOf(balance));
     *
     * this.transaction = transaction;
     *
     * em.persist(clientAccount); em.persist(allocation);
     * //em.persist(this.transaction);
     *
     * em.getTransaction().commit();
     *
     * System.err.println("Successfull");
     *
     * return summary.toString(); // to See the Changes
     *
     * }
     * } catch (Exception e) { em.getTransaction().rollback(); } } else if
     * (clientAccount.getAccountTypeCode().equalsIgnoreCase("SVGS")) {
     *
     * if (clientAccount.getDisplayBalance().doubleValue() <
     * transaction.getAmount()) {
     *
     * return "Insufficient Fund";
     *
     * } else {
     *
     * double balance = clientAccount.getDisplayBalance().doubleValue() -
     * bigDecimal;
     *
     * clientAccount.setDisplayBalance(BigDecimal.valueOf(balance));
     *
     * this.transaction = transaction;
     *
     * em.persist(this.clientAccount); em.persist(allocation);
     * //em.persist(this.transaction);
     *
     * em.getTransaction().commit();
     *
     * System.err.println("Successfull"); return summary.toString(); } } }
     *
     * } else { return "Account Number does't Exist"; }
     *
     * }// End Of If Statement To Check Valid Amount
     *
     * return null;
     *
     * }
     *
     *
     */
    ////////////////////////new version of the method above////////////////
    @GetMapping("withdrawals")
    public String withdrawals(@RequestBody Transaction transaction) {

        emf = Persistence.createEntityManagerFactory("discovery");
        em = emf.createEntityManager();
        em.getTransaction().begin();

        // Checking If the Amount is Valid
        if (false) {
            return "Invalid Amount Coins Not Allowed!!!";
        } else {
            clientAccount = em.find(ClientAccount.class, transaction.getAccountNumber());

            // checking if the Account Number Exist
            if (clientAccount != null) {

                List<CreditCardLimit> limits = em.createQuery("SELECT ccl FROM CreditCardLimit ccl").getResultList();
                for (CreditCardLimit limit : limits) {
                    if (limit.getClientAccountNumber().getClientAccountNumber() == transaction.getAccountNumber()) {
                        this.creditCardLimit = limit;
                    }

                }
///////////////////////////////////////////////////

                this.atm = em.find(Atm.class, transaction.getAtmId());
                if (atm != null) {
                    this.atmLocations = em.createQuery("SELECT a FROM AtmAllocation a WHERE a.atmId =" + transaction.getAtmId()).getResultList();

                    int count = 0;
                    double value = 0;

                    denominations = em.createQuery("select d from Denomination d ").getResultList();
                    denominations.sort((d1, d2) -> d2.getValue().compareTo(d1.getValue()));

                    //Double[] values = new Double[denominations.size()];
                    Integer[] counts = new Integer[denominations.size()];

                    for (int i = 0; i < denominations.size(); i++) {

                        //  denominations.get(i).getValue().doubleValue();
                        if (atmLocations.get(i).getCount() != 0) {

                            if (transaction.getAmount() >= denominations.get(i).getValue().doubleValue()) {
                                counts[i] = (int) (transaction.getAmount() / denominations.get(i).getValue().doubleValue());
                                transaction.setAmount(transaction.getAmount() % denominations.get(i).getValue().doubleValue());
                                atmLocations.get(i).setCount(counts[i] - atmLocations.get(i).getCount());
                                value = denominations.get(i).getValue().doubleValue();

                                denomination = denominations.get(i);
                                allocation = atmLocations.get(i);
                                count = counts[i];

                                System.out.println(denominations.get(i).getValue().doubleValue() + "*" + count);

                            }
                        }

                    }

           // count = counts[a];
                    em.persist(this.denomination);
                    em.persist(this.allocation);

                    em.getTransaction().commit();

///////////////////////
                    if (clientAccount.getAccountTypeCode().equalsIgnoreCase("CCRD")) {
                        if (transaction.getAmount() > creditCardLimit.getAccountLimit().doubleValue()) {

                            return "Exit Limit Try a Lesser Amount";
                        } else {
                            try {
                                double balance = clientAccount.getDisplayBalance().doubleValue() - transaction.getAmount();

                                clientAccount.setDisplayBalance(BigDecimal.valueOf(balance));

                                this.transaction = transaction;

                                em.persist(allocation);
                                em.persist(clientAccount);
                                //em.persist(this.transaction);

                                em.getTransaction().commit();

                                // System.err.println("Successfull");
                                return value + "*" + count;

                            } catch (Exception e) {
                                em.getTransaction().rollback();
                            }
                        }

                    } else if (clientAccount.getAccountTypeCode().equalsIgnoreCase("CHQ")) {

                        try {
                            if (transaction.getAmount() > 10000) {

                                return "Insufficient Fund";

                            } else {

                                double balance = clientAccount.getDisplayBalance().doubleValue() - transaction.getAmount();

                                clientAccount.setDisplayBalance(BigDecimal.valueOf(balance));

                                this.transaction = transaction;

                                em.persist(clientAccount);
                                em.persist(allocation);
                                //em.persist(this.transaction);

                                System.err.println("Successfull");

                                return this.clientAccount.toString();  // to See the Changes

                            }
                        } catch (Exception e) {
                            em.getTransaction().rollback();
                        }
                    } else if (clientAccount.getAccountTypeCode().equalsIgnoreCase("SVGS")) {

                        if (clientAccount.getDisplayBalance().doubleValue() < transaction.getAmount()) {

                            return "Insufficient Fund";

                        } else {

                            double balance = clientAccount.getDisplayBalance().doubleValue() - transaction.getAmount();

                            clientAccount.setDisplayBalance(BigDecimal.valueOf(balance));

                            this.transaction = transaction;

                            em.persist(this.clientAccount);
                            em.persist(allocation);
                            //em.persist(this.transaction);

                            //em.getTransaction().commit();
                            System.err.println("Successfull");
                            return value + "*" + count;
                        }
                    }
                }

            } else {
                return "Account Number does't Exist";
            }

        }// End Of If Statement To Check Valid Amount

        return null;

    }

}
