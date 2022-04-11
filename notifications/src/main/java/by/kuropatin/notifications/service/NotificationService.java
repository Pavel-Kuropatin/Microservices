package by.kuropatin.notifications.service;

import by.kuropatin.clients.model.response.NotificationResponse;
import by.kuropatin.notifications.model.Notification;
import by.kuropatin.notifications.repository.NotificationsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record NotificationService(NotificationsRepository repository) {

    public NotificationResponse saveNotification(final String name) {
        final Notification notification = Notification.builder()
                .message(String.format("New customer created. Welcome, %s!", name))
                .created(LocalDateTime.now())
                .build();
        repository.save(notification);
        return new NotificationResponse(notification.getMessage());
    }
}