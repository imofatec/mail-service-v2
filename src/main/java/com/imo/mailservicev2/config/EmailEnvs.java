package com.imo.mailservicev2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailEnvs {
  @Value("${spring.mail.host}")
  public String HOST;

  @Value("${spring.mail.port}")
  public int PORT;

  @Value("${spring.mail.username}")
  public String USERNAME;

  @Value("${spring.mail.password}")
  public String PASSWORD;

  @Value("${email.confirmation.url}")
  public String CONFIRM_URL;
}
