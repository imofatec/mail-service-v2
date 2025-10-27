package com.imo.mailservicev2.dtos;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public record UserDTO(
    String id,
    String name,
    String email,
    Boolean isConfirmed,
    String profilePicturePath
) {
  public static UserDTO fromJSONString(String data) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      System.out.println(data);
      return mapper.readValue(data, UserDTO.class);
    } catch (Exception e) {
      log.error(e.getMessage());
      return null;
    }
  }
}
