package com.kafka.kevin.Teste.controllers;

import com.kafka.kevin.Teste.services.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class ApacheKafkaWebController {

  @Autowired
  KafkaSender kafkaSender;

  @GetMapping(value = "/producer")
  public String producer(@RequestParam("message") String message) {
    kafkaSender.send(message);

    return "Mensagem enviada!";
  }
}
