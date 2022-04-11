package by.kuropatin.fraud.controller;

import by.kuropatin.clients.model.response.FraudCheckResponse;
import by.kuropatin.fraud.service.FraudCheckHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/fraud-check")
public record FraudController(FraudCheckHistoryService service) {

    @GetMapping(path = "/{customerId}")
    public FraudCheckResponse registerCustomer(@PathVariable final Long customerId) {
        log.info("Customer fraud check {}", customerId);
        return service.isFraudulentCustomer(customerId);
    }
}