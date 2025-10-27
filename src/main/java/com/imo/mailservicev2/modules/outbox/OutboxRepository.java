package com.imo.mailservicev2.modules.outbox;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

public interface OutboxRepository<T> extends MongoRepository<Outbox<T>, String> {

  @Query("{ '_id':  ?0 }")
  @Update("{ $set:  { 'status': ?1 } }")
  void updateStatusById(String id, OutboxStatus outboxStatus);
}
