Feature: Processamento de notificações via SQS

  Scenario: Processar notificação com sucesso
    Given uma notificação válida
    When a notificação é recebida pela fila
    Then a notificação deve ser processada com sucesso

  Scenario: Falha ao processar notificação
    Given uma notificação inválida que causa erro no processamento
    When a notificação é recebida pela fila
    Then deve ser lançada uma exceção de processamento de notificação
