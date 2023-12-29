package net.nacer.accountservice;

import net.nacer.accountservice.clients.CustomerRestClients;
import net.nacer.accountservice.entities.BankAccount;
import net.nacer.accountservice.enums.AccountType;
import net.nacer.accountservice.repository.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BankAccountRepository bankAccountRepository, CustomerRestClients customerRestClients){
        return args -> {
            customerRestClients.allCustomers().forEach(
                    c->{
                        BankAccount bankAccount1 = BankAccount.builder()
                                .AccountId(UUID.randomUUID().toString())
                                .currency("DZ")
                                .balance(Math.random()*45100)
                                .createAT(LocalDate.now())
                                .type(AccountType.CURRENT_ACCOUNT)
                                .customerID(Long.valueOf(c.getId()))
                                .build();

                        BankAccount bankAccount2 = BankAccount.builder()
                                .AccountId(UUID.randomUUID().toString())
                                .currency("DZ")
                                .balance(Math.random()*78100)
                                .createAT(LocalDate.now())
                                .type(AccountType.SAVING_ACCOUNT)
                                .customerID(Long.valueOf(c.getId()))
                                .build();
                        bankAccountRepository.save(bankAccount1);
                        bankAccountRepository.save(bankAccount2);
                    }

            );



        };
    }

}
