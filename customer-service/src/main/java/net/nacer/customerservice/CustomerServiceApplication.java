package net.nacer.customerservice;

import net.nacer.customerservice.config.GlobalConfig;
import net.nacer.customerservice.entities.Customer;
import net.nacer.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(GlobalConfig.class)
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
	return args -> {
		Customer customer1=Customer.builder()
						.fistname("nacer")
								.lastname("AIT MAAMAR")
										.email("nacerAitmaamar@gmail.com")
											.build();
		Customer customer2=Customer.builder()
				.fistname("nacer 2")
				.lastname("AIT MAAMAR 2")
				.email("nacerAitmaamar2@gmail.com")
				.build();
		customerRepository.save(customer1);
		customerRepository.save(customer2);
	};
	}

}
