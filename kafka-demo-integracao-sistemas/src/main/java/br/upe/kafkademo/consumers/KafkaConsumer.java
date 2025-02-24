package br.upe.kafkademo.consumers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
    private String lastMessage;

    @KafkaListener(topics = "teste", groupId = "group_id")
    public void consume(String message) {
        this.lastMessage = message;
        logger.info("Mensagem consumida: {}", message);
    }

    public String getLastMessage() {
        return lastMessage;
    }
}