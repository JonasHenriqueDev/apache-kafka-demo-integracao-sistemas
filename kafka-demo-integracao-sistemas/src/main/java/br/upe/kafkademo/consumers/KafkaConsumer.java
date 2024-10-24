package br.upe.kafkademo.consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private String lastMessage;

    @KafkaListener(topics = "teste", groupId = "group_id")
    public void consume(String message) {
        this.lastMessage = message;
        System.out.println("Consumed message: " + message);
    }

    public String getLastMessage() {
        return lastMessage;
    }
}