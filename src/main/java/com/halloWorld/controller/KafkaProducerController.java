package com.halloWorld.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.halloWorld.dto.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class KafkaProducerController {

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private KafkaTemplate<String, Student> kafkaTemplate;

  @Value("${spring.kafka.topic}")
  private String studentTopic;

  @PostMapping("/publish")
  public String publishMessage(@RequestBody Student studentData) throws JsonProcessingException {
    log.info("received payload :: {}", objectMapper.writeValueAsString(studentData.toString()));
    kafkaTemplate.send(studentTopic, studentData);
    return "Below data is published!\n" + studentData;
  }
}
