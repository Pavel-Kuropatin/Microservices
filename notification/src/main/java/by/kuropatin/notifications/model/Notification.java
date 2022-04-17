package by.kuropatin.notifications.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "Notification")
@Table(indexes = {
        @Index(name = "idx_notification_message", columnList = "message")
})
public class Notification {

    @Id
    @SequenceGenerator(name = "notification_id_generator", sequenceName = "notification_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "notification_id_generator")
    private Long id;
    private Long customerId;
    private String customerEmail;
    private String message;
    private LocalDateTime created;
}