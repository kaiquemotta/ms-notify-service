package com.video.notify.domain.service;


import com.video.notify.exception.EmailSendException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmailServiceTest {

    private JavaMailSender mailSender;
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        mailSender = mock(JavaMailSender.class);
        emailService = new EmailService(mailSender);
    }

    @Test
    void deveEnviarEmailComSucesso() throws Exception {
        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

        assertDoesNotThrow(() ->
                emailService.enviar("teste@email.com", "Assunto", "Corpo do email")
        );

        verify(mailSender).send(mimeMessage);
    }

    @Test
    void deveLancarExcecaoQuandoEnviarEmailFalha() throws Exception {
        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

        // aqui usamos doAnswer para poder lançar MessagingException (checada)
        doAnswer(invocation -> {
            throw new MessagingException("Erro ao enviar");
        }).when(mailSender).send(any(MimeMessage.class));

        assertThrows(EmailSendException.class, () ->
                emailService.enviar("destinatario@email.com", "Assunto", "Corpo do e-mail")
        );
    }

}
