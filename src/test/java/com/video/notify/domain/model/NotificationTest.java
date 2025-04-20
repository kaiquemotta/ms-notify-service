
package com.video.notify.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificationTest {

    @Test
    void deveCriarNotificacaoCorretamente() {
        Notification notification = new Notification(
                "user-123",
                "video-456",
                "ERRO",
                "Erro ao processar vídeo",
                "Falha no processamento",
                "email@teste.com"
        );

        assertEquals("user-123", notification.userId());
        assertEquals("video-456", notification.videoId());
        assertEquals("ERRO", notification.status());
        assertEquals("Erro ao processar vídeo", notification.message());
        assertEquals("Falha no processamento", notification.subject());
        assertEquals("email@teste.com", notification.email());
    }
}
