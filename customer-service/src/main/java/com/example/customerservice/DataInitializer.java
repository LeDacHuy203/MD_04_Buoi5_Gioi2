package com.example.customerservice;

import com.example.customerservice.entity.Customer;
import com.example.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(CustomerRepository customerRepository, JdbcTemplate jdbcTemplate) {
        return args -> {
            try {
                jdbcTemplate.execute(
                    "INSERT INTO customers (id, name, full_name, email, password, phone, address) " +
                    "VALUES (1, 'Nguyen Van A', 'Nguyen Van A', 'customer1@gmail.com', '123456', '0912345678', 'Ha Noi') " +
                    "ON CONFLICT (id) DO NOTHING"
                );
            } catch (Exception ignored) {
            }

            try {
                if (customerRepository.count() == 0) {
                    customerRepository.save(Customer.builder()
                            .name("Nguyen Van A")
                            .fullName("Nguyen Van A")
                            .email("nguyenvana@gmail.com")
                            .password("123456")
                            .phone("0912345678")
                            .address("Ha Noi")
                            .build());
                }
            } catch (Exception ignored) {
            }
        };
    }
}
