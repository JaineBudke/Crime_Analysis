package actors;

import static akka.actor.SupervisorStrategy.restart;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.japi.pf.DeciderBuilder;
import messages.ResultRequest;
import scala.concurrent.duration.Duration;


public class LoadMasterActor extends AbstractActor  {

	
	static public Props props() {
	    return Props.create(LoadMasterActor.class);
	}
	

	ActorRef producerActor = getContext().actorOf(ProducerActor.props(), "producer");
	ActorRef consumerActor = getContext().actorOf(ConsumerActor.props(), "consumer");

	
	
	@Override
	public Receive createReceive() {
		
		return receiveBuilder()	
				
			.match(String.class, msg-> {
				producerActor.tell(msg, getSelf());
			})
            .match(ResultRequest.class, s -> {
            	producerActor.forward(s, getContext());
            })
			.build();
			
	}
	
	
	
	private static SupervisorStrategy supervisorStrategy =  
			new OneForOneStrategy(          // Ou AllForOneStrategy
				10,                                                     //  Tentativas
				Duration.create("60 seconds"),   //  Limite de tempo
	 			DeciderBuilder
				    .match(RuntimeException.class, ex -> SupervisorStrategy.stop())
				    .match(NullPointerException.class, ex -> SupervisorStrategy.restart())
				    .match(ArithmeticException.class, ex -> SupervisorStrategy.resume()) 
				    .build()
			);
	
	@Override
	public SupervisorStrategy supervisorStrategy() {
		return supervisorStrategy;  
	}
	
	
}
