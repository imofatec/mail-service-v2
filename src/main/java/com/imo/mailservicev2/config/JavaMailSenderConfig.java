package com.imo.mailservicev2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class JavaMailSenderConfig {
  private final EmailEnvs envs;

  public JavaMailSenderConfig(EmailEnvs envs) {
    this.envs = envs;
  }

  @Bean
  public JavaMailSender javaMailSender() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost(envs.HOST);
    mailSender.setPort(envs.PORT);
    mailSender.setUsername(envs.USERNAME);
    mailSender.setPassword(envs.PASSWORD);

    Properties props = mailSender.getJavaMailProperties();
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.debug", "debug");

    return mailSender;
  }
}
