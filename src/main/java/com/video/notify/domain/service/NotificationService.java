package com.video.notify.domain.service;

import com.video.notify.domain.model.Notification;
import com.video.notify.exception.NotificationProcessingException;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void process(Notification notification) {
        try {
            if (notification.userId() == null) {
                throw new NotificationProcessingException("Usuário não informado na notificação.");
            }

            // lógica da notificação (simulação):
            System.out.println("Enviando notificação para usuário: " + notification.userId());

        } catch (Exception e) {
            throw new NotificationProcessingException("Falha ao processar notificação", e);
        }
    }
}
