package com.video.notify.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailSendExceptionTest {

    @Test
    void deveCriarExcecaoComMensagem() {
        String mensagem = "Erro ao enviar e-mail";
        EmailSendException exception = new EmailSendException(mensagem);

        assertEquals(mensagem, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void deveCriarExcecaoComMensagemECausa() {
        String mensagem = "Erro ao enviar e-mail";
        Throwable causa = new RuntimeException("Falha interna");
        EmailSendException exception = new EmailSendException(mensagem, causa);

        assertEquals(mensagem, exception.getMessage());
        assertEquals(causa, exception.getCause());
    }
}
