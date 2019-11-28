package actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.actor.AbstractActor.Receive;
import kafka.Manager;

public class ClassifierActor  extends AbstractActor{

	public static Props props() {
		return Props.create(ClassifierActor.class);
	}
	
	
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(String.class, s -> {
					sender().tell(classifier(s), getSelf());
	            })
		        .match(Integer.class, msg-> {
		        	getSender().tell(msg+", I'm an actor!", self());
		        })
				.build();
	}
	
	
	
	private String classifier(String line) {
		
		
		
		return "Classificando";
		
	}
	
}
