package by.kuropatin.customer.service;

import by.kuropatin.customer.model.Customer;
import by.kuropatin.customer.model.request.CustomerRegistrationRequest;
import by.kuropatin.customer.model.response.FraudCheckResponse;
import by.kuropatin.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(CustomerRepository repository, RestTemplate restTemplate) {

    public void registerCustomer(final CustomerRegistrationRequest request) {
        final Customer customer = Customer.builder()
                .name(request.name())
                .email(request.email())
                .build();
        repository.saveAndFlush(customer);
        final FraudCheckResponse response = restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );
        if (response == null) {
            throw new IllegalStateException("Fraud check response is null");
        } else if (response.isFraudster()) {
            throw new IllegalStateException("Customer is fraudster");
        }
    }
}