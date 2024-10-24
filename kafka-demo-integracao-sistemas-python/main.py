from flask import Flask, jsonify
from kafka import KafkaConsumer
import threading

app = Flask(__name__)

# Configuração do consumidor Kafka
consumer = KafkaConsumer(
    'teste',  # Nome do tópico
    bootstrap_servers=['localhost:9092'],
    auto_offset_reset='earliest',
    enable_auto_commit=True,
    group_id='group_id_3',  
    value_deserializer=lambda x: x.decode('utf-8') if x is not None else None
)

# Armazenar a última mensagem consumida
last_message = None

def consume_messages():
    global last_message
    for message in consumer:
        last_message = message.value

# Inicia a thread do consumidor
threading.Thread(target=consume_messages, daemon=True).start()

@app.route('/kafka/python/last-message', methods=['GET'])
def get_last_message():
    if last_message is None:
        return jsonify({'error': 'Envie uma mensagem primeiro'}), 404
    return jsonify({'message': last_message})

if __name__ == '__main__':
    app.run(debug=True)
