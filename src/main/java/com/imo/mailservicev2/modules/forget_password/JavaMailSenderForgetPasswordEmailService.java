package com.imo.mailservicev2.modules.forget_password;

import com.imo.mailservicev2.config.EmailEnvs;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Slf4j
@Service
public class JavaMailSenderForgetPasswordEmailService implements SendForgetPasswordEmailService {
  private final EmailEnvs envs;

  private final JavaMailSender mailSender;

  private final TemplateEngine templateEngine;

  public JavaMailSenderForgetPasswordEmailService(
      EmailEnvs envs,
      JavaMailSender mailSender,
      TemplateEngine templateEngine
  ) {
    this.envs = envs;
    this.mailSender = mailSender;
    this.templateEngine = templateEngine;
  }

  @Override
  public void execute(ForgetPasswordDTO dto) {
    MimeMessage message = mailSender.createMimeMessage();
    try {
      MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

      helper.setFrom(envs.USERNAME);
      helper.setTo(dto.email());
      helper.setSubject("IMO - Recuperar senha");

      String htmlContent = this.getTemplate(dto);

      helper.setText(htmlContent, true);
      this.mailSender.send(message);
      log.info("email sent");
    } catch (Exception e) {
      log.error(e.getMessage());
    }
  }

  private String getTemplate(ForgetPasswordDTO dto) {
    Context context = new Context();

    context.setVariable("code", dto.code());

    return this.templateEngine.process("forget_password", context);
  }
}
