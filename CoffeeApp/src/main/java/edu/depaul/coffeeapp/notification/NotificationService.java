package edu.depaul.coffeeapp.notification;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification sendNotification(Notification notification) throws Exception {
        throw new Exception("not implemented yet");
    }

    public List<Notification> getNotifications(Long receiverId) throws Exception {
        throw new Exception("not implemented yet");
    }

    public void delete(Notification notification) throws Exception {
        throw new Exception("not implemented yet");
    }

}
