package com.imo.mailservicev2.modules.confirm_email;

import com.imo.mailservicev2.dtos.UserDTO;

public interface SendConfirmEmailService {
  void execute(UserDTO userDTO);
}
