package by.kuropatin.notifications.service;

import by.kuropatin.clients.notification.model.NotificationRequest;
import by.kuropatin.notifications.model.Notification;
import by.kuropatin.notifications.repository.NotificationsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public record NotificationService(NotificationsRepository repository) {

    public void sendNotification(final NotificationRequest request) {
        final Notification notification = Notification.builder()
                .customerId(request.customerId())
                .customerEmail(request.customerEmail())
                .message(request.message())
                .created(LocalDateTime.now())
                .build();
        repository.saveAndFlush(notification);
        log.info(String.format("New notification with id = %s has been sent", notification.getId()));
    }
}