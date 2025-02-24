package br.upe.kafkademo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import br.upe.kafkademo.consumers.KafkaConsumer;
import br.upe.kafkademo.model.KafkaMessage;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class KafkaController {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
    private final KafkaConsumer kafkaConsumer;


	@PostMapping("/send")
	public String sendMessage(@RequestBody KafkaMessage message) {
		kafkaTemplate.send("teste", message.getKey(), message.getValue());
		return "Mensagem enviada com sucesso!";
	}
	
	@GetMapping("/last-message")
    public String getLastMessage() {
        String lastMessage = kafkaConsumer.getLastMessage();
        return lastMessage != null ? lastMessage : "Nenhuma mensagem consumida ainda.";
    }
}
