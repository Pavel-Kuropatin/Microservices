package by.kuropatin.notifications.controller;

import by.kuropatin.clients.notification.model.NotificationRequest;
import by.kuropatin.notifications.service.NotificationService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notifications")
public record NotificationController(NotificationService service) {

    @PostMapping(path = "/{customerName}")
    public void sendNotification(@PathVariable("customerName") final NotificationRequest request) {
        service.sendNotification(request);
    }
}