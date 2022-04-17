package by.kuropatin.amqp.message;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class RabbitMQMessageProducer {

    private final AmqpTemplate amqpTemplate;

    public void publish(final Object payload, final String exchange, final String routingKey) {
        log.info("Publishing to {} using {}. Payload: {}", exchange, routingKey, payload);
        amqpTemplate.convertAndSend(exchange, routingKey, payload);
        log.info("Successfully published to {} using {}. Payload: {}", exchange, routingKey, payload);
    }
}