package by.kuropatin.clients.fraud;

import by.kuropatin.clients.model.response.NotificationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("notifications")
public interface NotificationClient {

    @PostMapping(path = "/api/v1/notifications/{customerName}")
    NotificationResponse saveNotification(@PathVariable("customerName") final String customerName);
}