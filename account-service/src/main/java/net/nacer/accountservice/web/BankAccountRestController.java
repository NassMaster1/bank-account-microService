package net.nacer.accountservice.web;

import net.nacer.accountservice.clients.CustomerRestClients;
import net.nacer.accountservice.entities.BankAccount;
import net.nacer.accountservice.model.Customer;
import net.nacer.accountservice.repository.BankAccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BankAccountRestController {
    private BankAccountRepository bankAccountRepository;
    private CustomerRestClients customerRestClients;

    public BankAccountRestController(BankAccountRepository bankAccountRepository,CustomerRestClients customerRestClients) {
        this.bankAccountRepository = bankAccountRepository;
        this.customerRestClients=customerRestClients;
    }

    @GetMapping("/accounts")
    public List<BankAccount> accountList(){

        List<BankAccount> bankAccounts= bankAccountRepository.findAll();
        bankAccounts.forEach(c->{
            c.setCustomer(customerRestClients.findCustomerById(c.getCustomerID()));
        });
        return bankAccounts;
    }

    @GetMapping("/accounts/{id}")
    public BankAccount accountById(@PathVariable  String id ){
        BankAccount bankAccount = bankAccountRepository.findById(id).get();
        Customer customer=customerRestClients.  findCustomerById(bankAccount.getCustomerID());
        bankAccount.setCustomer(customer);
        return bankAccount;
    }


}
