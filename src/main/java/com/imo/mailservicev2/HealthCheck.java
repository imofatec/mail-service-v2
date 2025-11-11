package com.imo.mailservicev2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

  @GetMapping()
  public ResponseEntity<String> handle() {
    return ResponseEntity.ok("OK");
  }
}
