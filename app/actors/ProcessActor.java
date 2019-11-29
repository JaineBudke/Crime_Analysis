package actors;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.actor.AbstractActor.Receive;
import cassandra.CassandraConnector;
import kafka.Manager;
import support.Classifier;

public class ProcessActor extends AbstractActor{

	
	
	public static Props props() {
		return Props.create(ProcessActor.class);
	}
	
	
	
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				
				.match(String.class, s -> {
					sender().tell(process(s), getSelf());
	            })
		        .match(Integer.class, msg-> {
		        	getSender().tell(msg, self());
		        })
				.build();
	}
	
	public String process(String s){

		Classifier classif = new Classifier();
		String processados = classif.processData(s);
		return classif.classifyData(processados);

	}

	
}
