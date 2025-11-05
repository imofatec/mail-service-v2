# IMO - Mail Sender v2

Este projeto é uma refatoração da [primeira versão](https://github.com/imofatec/mensageria) implementada para consumir
eventos de forma assíncrona com nossa [api principal](https://github.com/imofatec/imo/tree/develop/backend)

## Objetivo

Esse microsserviço foi criado pois nos da maior controle dos eventos relacionado a envio de email, a comunicação
assíncrona permite que não interrompamos todo fluxo da api principal para esse tipo de evento

## Queue Based

Usando RabbitMQ, cada disparo de evento é publicado por outras fontes e nosso serviço consome as mensagens
sequencialmente e de forma assíncrona para continuar o fluxo

## Tecnologias utilizadas

- **Linguagem**
    - [Java 21](https://www.java.com/pt-BR/)
- **Inicar aplicação Spring**
    - [Spring Boot](https://spring.io/projects/spring-boot)
- **Message Broker**
    - [RabbitMQ](https://www.rabbitmq.com/)
    - [Spring AMQP](https://spring.io/projects/spring-amqp)
- **Envio de email**
    - [Java Mail Sender](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-mail)
    - [Thymeleaf Spring](https://docs.spring.io/spring-framework/reference/web/webmvc-view/mvc-thymeleaf.html)
- **Banco de dados**
    - [Spring Data MongoDB](https://spring.io/projects/spring-data-mongodb)

## Requisitos

> Rodar o projeto na sua máquina

- Java 21
- MongoDB

## Setup

Rodar o projeto na sua máquina

### Profile

> Ative o perfil de desenvolvimento `src/main/resources/application.properties`

```
spring.profiles.default=prod
spring.profiles.active=dev

```

### Envs

> Crie o arquivo `.env-dev.properties` `src/main/resources` e adicione as envs

```
# database
MONGO_URI=mongodb://localhost:27017/imo
# email
EMAIL_HOST=smtp.gmail.com
EMAIL_PORT=587
EMAIL_USERNAME=email que vai enviar
EMAIL_PASSWORD=senha de app
EMAIL_CONFIRMATION_URL=http://localhost:5173/user/confirmar-email
# rabbitmq
EXCHANGE_NAME=imo.user
RABBITMQ_ADDRESS=amqp://localhost:5672
# confirmation email service
QUEUE_NAME_CONFIRM_EMAIL=imo.user.confirmation_email.notification
ROUTING_KEY_CONFIRM_EMAIL=imo.user.confirmation_email
# forget password service
QUEUE_NAME_FORGET_PASSWORD=imo.user.forget_password.notification
ROUTING_KEY_FORGET_PASSWORD=imo.user.forget_password

```

### Run

```
./mvnw spring-boot:run
```