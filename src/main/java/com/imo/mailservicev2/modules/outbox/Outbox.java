package com.imo.mailservicev2.modules.outbox;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Document(collection = "outbox")
public class Outbox<T> {
  @MongoId(FieldType.OBJECT_ID)
  public String id;

  public T payload;

  public OutboxStatus status;

  public OutboxEvent event;

  @CreatedDate
  public LocalDateTime createdAt;

  @CreatedDate
  public LocalDateTime updatedAt;

  public Outbox(T payload, OutboxStatus status, OutboxEvent event) {
    this.payload = payload;
    this.status = status;
    this.event = event;
  }
}
