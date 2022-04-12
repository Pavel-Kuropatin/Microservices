package by.kuropatin.notifications.service;

import by.kuropatin.clients.model.response.NotificationResponse;
import by.kuropatin.notifications.model.Notification;
import by.kuropatin.notifications.repository.NotificationsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public record NotificationService(NotificationsRepository repository) {

    public NotificationResponse saveNotification(final String name) {
        final Notification notification = Notification.builder()
                .message(String.format("New customer created. Welcome, %s!", name))
                .created(LocalDateTime.now())
                .build();
        repository.saveAndFlush(notification);
        log.info(String.format("New notification with id = %s was saved", notification.getId()));
        return new NotificationResponse(notification.getMessage());
    }
}