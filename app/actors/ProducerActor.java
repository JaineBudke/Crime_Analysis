package actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
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
				
		Manager.runProducer();
		
		return "Produzindo";
		
	}
	
	
}
