package br.upe.kafkademo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.upe.kafkademo.consumers.Consumer;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class Controller {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
    private final Consumer kafkaConsumer;

    
	@GetMapping("/last-message")
    public String getLastMessage() {
        String lastMessage = kafkaConsumer.getLastMessage();
        return lastMessage != null ? lastMessage : "Nenhuma mensagem consumida ainda.";
        
    }
}
