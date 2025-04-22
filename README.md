# 📣 Notify Service

O **Notify** é um microserviço desenvolvido em Java com Spring Boot que atua como **consumidor da fila `video-notification-queue` no Amazon SQS**. Sua função é processar mensagens relacionadas a eventos do sistema de upload e processamento de vídeos, como falhas ou notificações para o usuário final, e executar ações como envio de e-mails ou registro de alertas.

Este projeto faz parte do sistema distribuído **FIAP X Video Processor**, que utiliza arquitetura orientada a eventos e está hospedado na AWS com automação via Terraform e CI/CD com GitHub Actions.

---

## 🧱 Tecnologias e Ferramentas

| Categoria          | Ferramenta / Tecnologia                |
|-------------------|----------------------------------------|
| Linguagem          | Java 17                                |
| Framework          | Spring Boot 3.4.x                      |
| Testes             | JUnit 5, Cucumber (BDD), Mockito       |
| Mensageria         | Amazon SQS (via AWS SDK v2 + Spring Cloud AWS) |
| Persistência       | (Não aplicável neste microserviço)     |
| Build              | Maven                                  |
| Infraestrutura     | AWS (SQS, S3), Docker, Terraform       |
| Monitoramento      | Logs estruturados via SLF4J / Logback  |
| CI/CD              | GitHub Actions + SonarCloud            |

---

## 🧩 Funcionalidades

- 🔁 Consome mensagens da fila `video-notification-queue` (SQS)
- 📧 Envia e-mails em caso de falhas (em breve: integração com Slack/Webhooks)
- 🔒 Leitura segura com tratamento de Dead Letter Queue (DLQ)
- 🧪 Testes automatizados com cobertura validada via SonarCloud
- 🌱 Possui perfil de ambiente `test` com configuração isolada

---

## 📁 Estrutura de Pastas

```
notify/
├── src/
│   ├── main/
│   │   ├── java/com.video.notify/
│   │   │   ├── config/        → Beans, SQS config, listeners
│   │   │   ├── consumer/      → SqsListener que consome eventos
│   │   │   ├── domain/        → Modelo de domínio + regras de negócio
│   │   │   ├── exception/     → Erros personalizados
│   │   │   └── NotifyApplication.java
│   │   └── resources/
│   │       ├── application.yml
│   │       ├── application-test.yml
│   └── test/
│       └── java/com.video.notify/
│           ├── consumer/      → Testes unitários com Mockito
│           └── bdd/           → Cucumber steps e configuração
├── .github/workflows/deploy.yml  → CI/CD pipeline
├── Dockerfile                    → Docker build
├── pom.xml
└── README.md
```

---

## 🚀 Como executar localmente

### 🔧 Pré-requisitos

- Java 17+
- Docker
- Maven 3.8+
- (Opcional) AWS CLI configurado

### 🔌 Subir fila local com ElasticMQ

```bash
docker run -p 9324:9324 -p 9325:9325 softwaremill/elasticmq-native
```

### 🌍 Variáveis de Ambiente

Crie um arquivo `.env` ou exporte no terminal:

```bash
export AWS_ACCESS_KEY_ID=test
export AWS_SECRET_ACCESS_KEY=test
export AWS_REGION=us-east-1
export SQS_ENDPOINT=http://localhost:9324
```

### ▶️ Executar a aplicação

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=test
```

---

## 🧪 Testes

### Unitários (Mockito + JUnit 5)

```bash
./mvnw test
```

### Testes BDD com Cucumber

```bash
./mvnw verify
```

Os arquivos `.feature` estão em:

```
src/test/resources/features/
```

### Ver cobertura com Sonar

> Requer token configurado:

```bash
./mvnw clean verify sonar:sonar \
  -Dsonar.projectKey=notify-service \
  -Dsonar.organization=SEU_ORG \
  -Dsonar.host.url=https://sonarcloud.io \
  -Dsonar.login=SEU_TOKEN
```

---

## ⚙️ CI/CD com GitHub Actions

Pipeline automatizado:

- 🚧 Build e testes
- 🧼 Análise no SonarCloud
- ✅ Verificação de cobertura
- 🚀 Deploy no Heroku ou ECS (baseado na branch)

Local: `.github/workflows/deploy.yml`

---

## 🔄 Integração com Fila SQS

### Fila padrão:

```
video-notification-queue
```

### Comportamento esperado:

- A aplicação escuta mensagens JSON dessa fila.
- Quando uma notificação é processada com erro → envia para DLQ.
- Quando for sucesso → loga, processa e responde com ACK.

---

## 🧑‍💻 Contribuindo

```bash
git clone https://github.com/seu-usuario/notify-service.git
cd notify-service
git checkout -b feature/minha-feature
```

Depois de codar:

```bash
git commit -m "feat: minha contribuição"
git push origin feature/minha-feature
```

E então abra um PR 🚀

---

## 📝 Licença

Projeto acadêmico desenvolvido para a FIAP.  
Código disponível sob a Licença MIT.

---

## 👥 Autores

- Kaique Motta ([LinkedIn](https://linkedin.com/in/kaiquemotta))
- Projeto Final da Pós FIAP – Cloud Solutions Architecture
