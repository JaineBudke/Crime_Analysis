package actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import cassandra.CassandraConnector;
import kafka.Manager;
import messages.ResultRequest;

public class ProducerActor extends AbstractActor{

	public static Props props() {
		return Props.create(ProducerActor.class);
	}
	

	ActorRef consumerActor = getContext().actorOf(ConsumerActor.props(), "consumer");

	
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(String.class, s -> {
					sender().tell(producer(s)+", I'm an actor!", getSelf());
	            })
				/*.match(String.class, msg-> {
					consumerActor.tell(msg, getSelf());
				})*/
		        .match(Integer.class, msg-> {
		        	getSender().tell(msg, self());
		        })
				.build();
	}
	
	private String producer(String line) {
				
		CassandraConnector.startConnection();
		
		CassandraConnector.createKeyspace("Furtos");
		CassandraConnector.startSession("furtos");
		
		String[] colunasFurtos = {"furtos_id", "cor", "turno", "sexo"};
		String[] tipoDataFurtos = {"int", "text", "text", "text"};

		CassandraConnector.createTable("FurtosTable", colunasFurtos, tipoDataFurtos, 0);

		CassandraConnector.closeConnection();
		
		Manager.runProducer();
		
		return "Produzindo";
		
	}
	
	
}
