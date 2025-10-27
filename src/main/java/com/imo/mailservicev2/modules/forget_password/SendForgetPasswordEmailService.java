package com.imo.mailservicev2.modules.forget_password;

public interface SendForgetPasswordEmailService {
  void execute(ForgetPasswordDTO forgetPasswordDTO);
}
