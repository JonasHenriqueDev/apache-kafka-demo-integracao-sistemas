package br.upe.kafkademo.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class KafkaMessage {
	private String key;
 private String value;
}
