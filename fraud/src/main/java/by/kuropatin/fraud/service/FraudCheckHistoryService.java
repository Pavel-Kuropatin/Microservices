package by.kuropatin.fraud.service;

import by.kuropatin.clients.fraud.model.FraudCheckResponse;
import by.kuropatin.fraud.model.FraudCheckHistory;
import by.kuropatin.fraud.repository.FraudCheckHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record FraudCheckHistoryService(FraudCheckHistoryRepository repository) {

    public FraudCheckResponse isFraudulentCustomer(final Long customerId) {
        final FraudCheckHistory check = FraudCheckHistory.builder()
                .customerId(customerId)
                .isFraudster(false)
                .created(LocalDateTime.now())
                .build();
        repository.save(check);
        return new FraudCheckResponse(false);
    }
}