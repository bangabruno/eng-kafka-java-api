package br.com.api.kafka.configuration.topic;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import br.com.api.kafka.model.Mensagem;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyTopic {
    
    private final List<Mensagem> messages = new ArrayList<>();
    
    @KafkaListener(topics = "myTopic", groupId = "kafka-sandbox") 
    public void listen(Mensagem message) {
            synchronized (messages) { 
                messages.add(message); 
        }
    }

    public List<Mensagem> getMessages() {
        return messages;
    }

}