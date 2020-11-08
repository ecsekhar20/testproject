package com.abc.aiservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.abc.aiservice.model.InvoiceData;

public interface InvoiceRepo extends MongoRepository<InvoiceData, Integer> {

}
