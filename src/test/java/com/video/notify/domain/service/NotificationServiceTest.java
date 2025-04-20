package com.video.notify.domain.service;

import com.video.notify.domain.model.Notification;
import com.video.notify.exception.EmailSendException;
import com.video.notify.exception.NotificationProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NotificationServiceTest {

    private EmailService emailService;
    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        emailService = mock(EmailService.class);
        notificationService = new NotificationService(emailService);
    }

    @Test
    void deveProcessarENotificarComSucesso() {
        Notification notification = new Notification(
                "123", "vid-456", "FAILED", "Ocorreu um erro", "Erro no processamento", "usuario@email.com"
        );

        assertDoesNotThrow(() -> notificationService.process(notification));
        verify(emailService, times(1))
                .enviar(notification.email(), notification.subject(), notification.message());
    }

    @Test
    void deveLancarExcecaoSeUserIdOuEmailForNulo() {
        Notification notification = new Notification(
                null, "vid-456", "FAILED", "Ocorreu um erro", "Erro no processamento", null
        );

        assertThrows(NotificationProcessingException.class, () -> notificationService.process(notification));
        verifyNoInteractions(emailService);
    }

    @Test
    void deveLancarExcecaoSeEmailFalhar() {
        Notification notification = new Notification(
                "123", "vid-456", "FAILED", "Ocorreu um erro", "Erro no processamento", "usuario@email.com"
        );

        doThrow(new EmailSendException("Falha ao enviar")).when(emailService)
                .enviar(notification.email(), notification.subject(), notification.message());

        assertThrows(NotificationProcessingException.class, () -> notificationService.process(notification));
    }
}
