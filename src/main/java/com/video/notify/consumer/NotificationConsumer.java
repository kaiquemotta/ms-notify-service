package com.video.notify.consumer;

import com.video.notify.domain.model.Notification;
import com.video.notify.domain.service.NotificationService;
import com.video.notify.exception.NotificationProcessingException;
import io.awspring.cloud.sqs.annotation.SqsListener;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Profile("!test")
@Component
@Validated
public class NotificationConsumer {

    private static final Logger logger = LoggerFactory.getLogger(NotificationConsumer.class);

    private final NotificationService notificationService;

    public NotificationConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @SqsListener("${sqs.notification.queue}")
    public void receive(@Payload @Valid @NotNull Notification notification) {
        logger.info("Mensagem recebida da fila: {}", notification);

        try {
            notificationService.process(notification);
            logger.info("Notificação processada com sucesso.");
        } catch (Exception e) {
            logger.error("Erro ao processar a notificação: {}", notification, e);
            throw new NotificationProcessingException("Erro ao processar notificação", e);
        }
    }
}
