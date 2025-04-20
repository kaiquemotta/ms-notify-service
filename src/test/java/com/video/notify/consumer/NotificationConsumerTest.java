package com.video.notify.consumer;

import com.video.notify.domain.model.Notification;
import com.video.notify.domain.service.NotificationService;
import com.video.notify.exception.NotificationProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;

class NotificationConsumerTest {

    private NotificationService notificationService;
    private NotificationConsumer consumer;

    @BeforeEach
    void setUp() {
        notificationService = Mockito.mock(NotificationService.class);
        consumer = new NotificationConsumer(notificationService);
    }

    @Test
    void deveProcessarNotificacaoComSucesso() {
        Notification notification = new Notification("1", "video-1", "ERRO", "Falha", "Erro no vídeo", "usuario@email.com");

        consumer.receive(notification);

        Mockito.verify(notificationService).process(notification);
    }

    @Test
    void deveLancarExcecaoQuandoFalharProcessamento() {
        Notification notification = new Notification("1", "video-1", "ERRO", "Falha", "Erro no vídeo", "usuario@email.com");

        Mockito.doThrow(new RuntimeException("Falha interna")).when(notificationService).process(notification);

        assertThrows(NotificationProcessingException.class, () -> consumer.receive(notification));
    }
}
