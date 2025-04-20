package com.video.notify.exception;


import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void deveTratarNotificationProcessingException() {
        NotificationProcessingException exception = new NotificationProcessingException("Erro simulado");

        ResponseEntity<?> response = handler.handleNotificationError(exception);

        assertEquals(500, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof Map);

        Map<?, ?> body = (Map<?, ?>) response.getBody();
        assertEquals("Erro ao processar notificação", body.get("error"));
        assertEquals("Erro simulado", body.get("message"));
        assertNotNull(body.get("timestamp"));
    }

    @Test
    void deveTratarErroGenerico() {
        Exception exception = new Exception("Erro genérico");

        ResponseEntity<?> response = handler.handleGenericError(exception);

        assertEquals(500, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof Map);

        Map<?, ?> body = (Map<?, ?>) response.getBody();
        assertEquals("Erro interno inesperado", body.get("error"));
        assertEquals("Erro genérico", body.get("message"));
        assertNotNull(body.get("timestamp"));
    }
}
