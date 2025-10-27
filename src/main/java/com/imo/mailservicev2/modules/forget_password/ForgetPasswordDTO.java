package com.imo.mailservicev2.modules.forget_password;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public record ForgetPasswordDTO(
    String outboxId,
    String userId,
    String email,
    String code
) {
  public static ForgetPasswordDTO fromJSONString(String data) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.readValue(data, ForgetPasswordDTO.class);
    } catch (Exception e) {
      log.error(e.getMessage());
      return null;
    }
  }
}
