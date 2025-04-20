package com.video.notify.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificationProcessingExceptionTest {

    @Test
    void deveCriarExcecaoComMensagem() {
        String mensagem = "Erro ao processar notificação";
        NotificationProcessingException exception = new NotificationProcessingException(mensagem);

        assertEquals(mensagem, exception.getMessage());
    }

    @Test
    void deveCriarExcecaoComMensagemECausa() {
        String mensagem = "Erro ao processar notificação";
        Throwable causa = new RuntimeException("Causa original");
        NotificationProcessingException exception = new NotificationProcessingException(mensagem, causa);

        assertEquals(mensagem, exception.getMessage());
        assertEquals(causa, exception.getCause());
    }
}
