package com.helloWorld.controller;

import com.helloWorld.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaProducerController {

  @Autowired private KafkaTemplate<String, Student> kafkaTemplate;

  @Value("${spring.kafka.topic}")
  private String studentTopic;

  @PostMapping("/publish")
  public String publishMessage(@RequestBody Student studentData) {

    kafkaTemplate.send(studentTopic, studentData);
    return "Below data is published\n" + studentData;
  }
}
