package by.kuropatin.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("notifications")
public interface NotificationClient {

    @PostMapping(path = "/api/v1/notifications/{customerName}")
    void sendNotification(@PathVariable("customerName") final String customerName);
}