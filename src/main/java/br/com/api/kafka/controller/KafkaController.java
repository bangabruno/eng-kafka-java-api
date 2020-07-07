package br.com.api.kafka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.kafka.configuration.topic.MyTopic;
import br.com.api.kafka.model.Mensagem;

@RestController
@RequestMapping("/kafka/v1")
public class KafkaController {

    private KafkaTemplate<String, Mensagem> template;

    @Autowired
    private MyTopic consumer;

    public KafkaController(KafkaTemplate<String, Mensagem> template, MyTopic consumer) {
        this.template = template;
        this.consumer = consumer;
    }

    @PostMapping(path = "/mytopic", consumes = "application/json", produces = "application/json")
    public void produce(@RequestBody Mensagem data) {
        template.send("myTopic", data);
    }

    @GetMapping(path = "/mytopic", consumes = "application/json", produces = "application/json")
    public List<Mensagem> getMessages() {
        return consumer.getMessages();
    }

}