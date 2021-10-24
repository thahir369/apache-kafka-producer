package com.helloWorld.controller;

import com.helloWorld.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaProducerController {

  @Autowired private KafkaTemplate<String, Student> kafkaTemplate;

  private static final String TOPIC = "student-topic";

  @PostMapping("/publish")
  public String publishMessage(@RequestBody Student studentData) {

    kafkaTemplate.send(TOPIC, studentData);
    return "Below data is published\n" + studentData;
  }
}
