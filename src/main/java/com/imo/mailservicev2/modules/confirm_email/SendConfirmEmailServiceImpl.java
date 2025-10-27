package com.imo.mailservicev2.modules.confirm_email;

import com.imo.mailservicev2.config.EmailEnvs;
import com.imo.mailservicev2.dtos.UserDTO;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Slf4j
@Service
public class SendConfirmEmailServiceImpl implements SendConfirmEmailService {
  private final EmailEnvs envs;

  private final JavaMailSender mailSender;

  private final TemplateEngine templateEngine;

  public SendConfirmEmailServiceImpl(
      EmailEnvs envs,
      JavaMailSender mailSender,
      TemplateEngine templateEngine
  ) {
    this.envs = envs;
    this.mailSender = mailSender;
    this.templateEngine = templateEngine;
  }

  @Override
  public void execute(UserDTO userDTO) {
    MimeMessage message = mailSender.createMimeMessage();
    try {
      MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

      helper.setFrom(envs.USERNAME);
      helper.setTo(userDTO.email());

      helper.setSubject("IMO - Confirme sua conta");

      String htmlContent = this.getTemplate(userDTO);

      helper.setText(htmlContent, true);
      this.mailSender.send(message);
    } catch (Exception e) {
      log.error(e.getMessage());
    }
  }

  private String getTemplate(UserDTO user) {
    Context context = new Context();

    context.setVariable("name", user.name());
    context.setVariable("confirmationURL", this.envs.CONFIRM_URL);

    return this.templateEngine.process("confirmation_email", context);
  }
}
