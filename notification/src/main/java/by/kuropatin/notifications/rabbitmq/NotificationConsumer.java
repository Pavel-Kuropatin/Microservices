package by.kuropatin.notifications.rabbitmq;

import by.kuropatin.clients.notification.model.NotificationRequest;
import by.kuropatin.notifications.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class NotificationConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queue.notification}")
    public void consumer(NotificationRequest notificationRequest) {
        log.info("Consumed notification {} from queue", notificationRequest);
        notificationService.sendNotification(notificationRequest);
    }
}