package net.nacer.accountservice.clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import net.nacer.accountservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClients {

    @GetMapping("/customers/{id}")
    @CircuitBreaker(name="customerService",fallbackMethod = "getDefaultCustomer")
    Customer findCustomerById(@PathVariable Long id);

    @GetMapping("/customers")
    @CircuitBreaker(name="customerService",fallbackMethod = "getAllDefaultCustomers")
    List<Customer> allCustomers();

    default Customer getDefaultCustomer(Long id, Exception exception){
        Customer customer = new Customer();

        customer.setId(id);
        customer.setFistname("not Vailable");
        customer.setLastname("not Vailable");
        customer.setEmail("not Vailable");

        return customer;
    }

        default List<Customer> getAllDefaultCustomers (Exception exception){
        System.out.println("la la ");
        return List.of();
        }

}
