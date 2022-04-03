package by.kuropatin.customer.service;

import by.kuropatin.customer.model.Customer;
import by.kuropatin.customer.model.request.CustomerRegistrationRequest;
import by.kuropatin.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository repository) {

    public void registerCustomer(final CustomerRegistrationRequest request) {
        final Customer customer = Customer.builder()
                .name(request.name())
                .email(request.email())
                .build();
        repository.save(customer);
    }
}