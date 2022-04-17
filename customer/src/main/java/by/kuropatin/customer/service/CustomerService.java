package by.kuropatin.customer.service;

import by.kuropatin.amqp.message.RabbitMQMessageProducer;
import by.kuropatin.clients.fraud.FraudClient;
import by.kuropatin.clients.notification.model.NotificationRequest;
import by.kuropatin.clients.fraud.model.FraudCheckResponse;
import by.kuropatin.customer.config.CustomerConfig;
import by.kuropatin.customer.model.Customer;
import by.kuropatin.customer.model.request.CustomerRegistrationRequest;
import by.kuropatin.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(
        CustomerRepository repository,
        FraudClient fraudClient,
        RabbitMQMessageProducer messageProducer,
        CustomerConfig config
) {

    public void registerCustomer(final CustomerRegistrationRequest request) {
        final Customer customer = Customer.builder()
                .name(request.name())
                .email(request.email())
                .build();
        repository.saveAndFlush(customer);

        final FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse == null) {
            throw new IllegalStateException("Fraud check response is null");
        } else if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Customer is fraudster");
        }

        sendMessage(customer);
    }

    private void sendMessage(final Customer customer) {
        final String message = String.format("Hello, %s!", customer.getName());
        final NotificationRequest notificationRequest = new NotificationRequest(customer.getId(), customer.getEmail(), message);
        messageProducer.publish(notificationRequest, config.getInternalExchange(), config.getInternalNotificationRoutingKey());
    }
}