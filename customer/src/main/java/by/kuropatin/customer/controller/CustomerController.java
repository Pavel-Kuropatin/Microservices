package by.kuropatin.customer.controller;

import by.kuropatin.customer.model.request.CustomerRegistrationRequest;
import by.kuropatin.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/customers")
public record CustomerController(CustomerService service) {

    @PostMapping()
    public void registerCustomer(@RequestBody final CustomerRegistrationRequest request) {
        log.info("New customer registration {}", request);
        service.registerCustomer(request);
    }
}