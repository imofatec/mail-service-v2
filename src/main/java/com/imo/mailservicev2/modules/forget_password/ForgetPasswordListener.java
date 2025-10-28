package com.imo.mailservicev2.modules.forget_password;

import com.imo.mailservicev2.modules.outbox.OutboxRepository;
import com.imo.mailservicev2.modules.outbox.OutboxStatus;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ForgetPasswordListener {
  private final SendForgetPasswordEmailService sendForgetPasswordEmailService;

  private final OutboxRepository<ForgetPasswordDTO> outboxRepository;

  public ForgetPasswordListener(
      SendForgetPasswordEmailService sendForgetPasswordEmailService,
      OutboxRepository<ForgetPasswordDTO> outboxRepository
  ) {
    this.sendForgetPasswordEmailService = sendForgetPasswordEmailService;
    this.outboxRepository = outboxRepository;
  }

  @RabbitListener(bindings = {
      @QueueBinding(value = @Queue(value = "${queue_name.forget_password}", durable = "true"), exchange = @Exchange(value = "${exchange_name}", type = "topic"), key = "${routing_key.forget_password}")
  })
  public void handle(
      @Payload
      String payload
  ) {
    ForgetPasswordDTO forgetPasswordDTO = ForgetPasswordDTO.fromJSONString(payload);

    var foundOutbox = this.outboxRepository.findById(forgetPasswordDTO.outboxId()).orElse(null);

    if (foundOutbox == null) {
      return;
    }

    this.sendForgetPasswordEmailService.execute(forgetPasswordDTO);

    this.outboxRepository.updateStatusById(foundOutbox.id, OutboxStatus.SENT);
  }
}
