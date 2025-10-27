package com.imo.mailservicev2.modules.confirm_email;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public record ConfirmEmailDTO(
    String id,
    String name,
    String email,
    Boolean isConfirmed,
    String profilePicturePath
) {
  public static ConfirmEmailDTO fromJSONString(String data) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.readValue(data, ConfirmEmailDTO.class);
    } catch (Exception e) {
      log.error(e.getMessage());
      return null;
    }
  }
}
