package com.video.notify.bdd;

import com.video.notify.consumer.NotificationConsumer;
import com.video.notify.domain.model.Notification;
import com.video.notify.domain.service.NotificationService;
import com.video.notify.exception.NotificationProcessingException;
import io.cucumber.java.en.*;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class NotificationConsumerSteps {

    private NotificationService notificationService;
    private NotificationConsumer notificationConsumer;
    private Notification notification;
    private Exception exception;

    @Given("uma notificação válida")
    public void uma_notificacao_valida() {
        notificationService = mock(NotificationService.class);
        notificationConsumer = new NotificationConsumer(notificationService);
        notification = new Notification(
                "user123", "video456", "OK",
                "Upload concluído", "Sucesso", "email@teste.com"
        );
    }

    @Given("uma notificação inválida que causa erro no processamento")
    public void uma_notificacao_invalida_que_causa_erro_no_processamento() {
        notificationService = mock(NotificationService.class);
        notificationConsumer = new NotificationConsumer(notificationService);
        notification = new Notification(
                "user123", "video456", "ERRO",
                "Falha no upload", "Erro", "email@teste.com"
        );
        doThrow(new RuntimeException("Erro interno"))
                .when(notificationService).process(notification);
    }

    @When("a notificação é recebida pela fila")
    public void a_notificacao_e_recebida_pela_fila() {
        try {
            notificationConsumer.receive(notification);
        } catch (Exception e) {
            this.exception = e;
        }
    }

    @Then("a notificação deve ser processada com sucesso")
    public void a_notificacao_deve_ser_processada_com_sucesso() {
        verify(notificationService, times(1)).process(notification);
        assertNull(exception);
    }

    @Then("deve ser lançada uma exceção de processamento de notificação")
    public void deve_ser_lancada_uma_excecao_de_processamento_de_notificacao() {
        assertNotNull(exception);
        assertTrue(exception instanceof NotificationProcessingException);
        verify(notificationService, times(1)).process(notification);
    }
}
