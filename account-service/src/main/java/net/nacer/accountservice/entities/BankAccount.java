package net.nacer.accountservice.entities;

import jakarta.persistence.*;
import lombok.*;
import net.nacer.accountservice.enums.AccountType;
import net.nacer.accountservice.model.Customer;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccount {
    @Id
    private String AccountId;
    private double balance;
    private LocalDate createAT;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    @Transient
    private Customer customer;
    private Long customerID;
}
