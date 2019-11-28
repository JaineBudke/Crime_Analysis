package actors;

import cassandra.CassandraConnector;
import akka.actor.AbstractActor;
import akka.actor.Props;
import kafka.Manager;
import akka.actor.AbstractActor.Receive;

public class ConsumerActor extends AbstractActor {

	public static Props props() {
		return Props.create(ConsumerActor.class);
	}
	
	@Override
	public Receive createReceive() {
		return receiveBuilder()		
			.match(String.class, s -> {
				sender().tell(consumer(s)+", I'm an actor!", self());
	        })
			.build();
	}
	
	private String consumer(String line) {
		
		
		
		Manager.runConsumer();
		
		
		return "Consumindo";
		
	}
	
}
