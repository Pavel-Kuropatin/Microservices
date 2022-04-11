package by.kuropatin.customer.service;

import by.kuropatin.clients.fraud.FraudClient;
import by.kuropatin.clients.model.response.FraudCheckResponse;
import by.kuropatin.customer.model.Customer;
import by.kuropatin.customer.model.request.CustomerRegistrationRequest;
import by.kuropatin.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(CustomerRepository repository, RestTemplate restTemplate, FraudClient fraudClient) {

    public void registerCustomer(final CustomerRegistrationRequest request) {
        final Customer customer = Customer.builder()
                .name(request.name())
                .email(request.email())
                .build();
        repository.saveAndFlush(customer);

        final FraudCheckResponse response = fraudClient.isFraudster(customer.getId());

        if (response == null) {
            throw new IllegalStateException("Fraud check response is null");
        } else if (response.isFraudster()) {
            throw new IllegalStateException("Customer is fraudster");
        }
    }
}