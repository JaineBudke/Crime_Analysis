package kafka;

import java.io.FileNotFoundException;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.datastax.driver.core.Session;

import cassandra.CassandraConnector;
import support.ArchiveRead;

public class Manager {

	
	
	
	public static void runConsumer() {
		
		
		
		Consumer<Long, String> consumer = ConsumerCreator.createConsumer();

		int noMessageToFetch = 0;

		// enquanto tiver mensagem pra ser consumida
		while (true) {
			
			// guarda uma lista de ConsumerRecord recebido pelo kafka
			final ConsumerRecords<Long, String> consumerRecords = consumer.poll(1000);
			
			
			
			// verifica se há mensagem para receber
			if (consumerRecords.count() == 0) {
				noMessageToFetch++;
				if (noMessageToFetch > IKafkaConstants.MAX_NO_MESSAGE_FOUND_COUNT)
					break;
				else
					continue;
			}
			
			
			// percorre a lista de mensagens consumidas do servidor kafka
			consumerRecords.forEach(record -> {
				
				System.out.println(record);
				
				// AQUI FICA O PROCESSAMENTO DAS MENSAGENS, OU SEJA, MANDAR PRO CASSANDRA
				String[] parts = record.value().split(",");
				
				// ARMAZENAR NO CASSANDRA
				String[] colunasInsert = {"furtos_id", "cor", "turno", "sexo"};
				String[] valoresInsert = {parts[0], parts[1], parts[2], parts[3]};
				
				CassandraConnector.insertData("FurtosTable", colunasInsert, valoresInsert);
								
				
			});
			
			consumer.commitAsync();
			

			
			
		}
		consumer.close();
	}
	
	
	public static void runProducer() {
		
		
		Producer<Long, String> producer = ProducerCreator.createProducer();
		
		
		ArchiveRead archive = null;
		try {
			archive = new ArchiveRead();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		String line;
		
		int id = 0;
		
		
		// enquanto tiver linhas
		while( (line = archive.getLine()) != "" ) {			
		
			String message = archive.process(line);
			if( !message.equals("") ) {
				
				message = String.valueOf(id)+","+message;
				
				// par chave(nome do tópico)/valor a ser enviado pro kafka.
				final ProducerRecord<Long, String> record = new ProducerRecord<Long, String>(IKafkaConstants.TOPIC_NAME,
						message);
				
				try {
					
					// envia dado pro servidor kafka
					RecordMetadata metadata = producer.send(record).get();
					System.out.println("Record sent with key " + message + " to partition " + metadata.partition()
							+ " with offset " + metadata.offset());
					
				} catch (ExecutionException e) {
					System.out.println("Error in sending record");
					System.out.println(e);
				} catch (InterruptedException e) {
					System.out.println("Error in sending record");
					System.out.println(e);
				}
		
				
			}
			
			id += 1;
			
		}
		
	}
	
	
}
