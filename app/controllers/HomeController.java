package controllers;

import akka.actor.*;
import play.mvc.*;
import scala.compat.java8.FutureConverters;
import javax.inject.*;
import java.util.concurrent.CompletionStage;
import actors.*;
import messages.*;
import static akka.pattern.Patterns.ask;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {


    final ActorSystem actorSystem = ActorSystem.create("playakka");
    final ActorRef producerActor = actorSystem.actorOf(ProducerActor.props());


    public Result index() {
        return ok(views.html.index.render());
    }


    
    public CompletionStage<Result> loadData() {
        return FutureConverters.toJava(
            ask(producerActor, "Carregar dados", 10000))
                .thenApply(response -> ok(views.html.actor.render(response.toString())));
    }
    
    
    
}
