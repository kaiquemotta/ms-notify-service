package com.video.notify.domain.service;

import com.video.notify.domain.model.Notification;
import com.video.notify.exception.NotificationProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final EmailService emailService;

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public NotificationService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void process(Notification notification) {
        try {
            if (notification.userId() == null || notification.email() == null) {
                throw new NotificationProcessingException("Usuário ou e-mail não informado na notificação.");
            }
            emailService.enviar(
                    notification.email(),
                    notification.subject(),
                    notification.message()
            );
            logger.info("Notificação enviada para: {}", notification.email());
        } catch (Exception e) {
            throw new NotificationProcessingException("Falha ao processar notificação", e);
        }
    }
}
