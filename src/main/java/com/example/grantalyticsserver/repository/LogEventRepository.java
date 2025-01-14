package com.example.grantalyticsserver.repository;

import com.example.grantalyticsserver.model.LogEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface LogEventRepository extends MongoRepository<LogEvent, String> {

}