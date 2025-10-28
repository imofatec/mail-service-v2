package com.imo.mailservicev2.modules.confirm_email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConfirmEmailListener {
  private final SendConfirmEmailService sendConfirmEmailService;

  public ConfirmEmailListener(SendConfirmEmailService sendConfirmEmailService) {
    this.sendConfirmEmailService = sendConfirmEmailService;
  }

  @RabbitListener(bindings = {
      @QueueBinding(value = @Queue(value = "${queue_name.confirm_email}", durable = "true"), exchange = @Exchange(value = "${exchange_name}", type = "topic"), key = "${routing_key.confirm_email}")
  })
  public void handle(
      @Payload
      String payload
  ) {
    ConfirmEmailDTO confirmEmailDTO = ConfirmEmailDTO.fromJSONString(payload);
    this.sendConfirmEmailService.execute(confirmEmailDTO);
  }
}
