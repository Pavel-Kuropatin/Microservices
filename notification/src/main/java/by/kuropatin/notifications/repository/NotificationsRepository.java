package by.kuropatin.notifications.repository;

import by.kuropatin.notifications.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationsRepository extends JpaRepository<Notification, Long> {


}