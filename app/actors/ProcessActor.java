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

public class ProcessActor extends AbstractActor{

	
	
	public static Props props() {
		return Props.create(ProcessActor.class);
	}
	
	
	
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				
				.match(String.class, s -> {
					sender().tell(process(s)+", I'm an actor!", getSelf());
	            })
				/*.match(String.class, msg-> {
					consumerActor.tell(msg, getSelf());
				})*/
		        .match(Integer.class, msg-> {
		        	getSender().tell(msg, self());
		        })
				.build();
	}
	
	public String process(String s){

		String[] parts = s.split(",");
		
		String retorno = "";
		
		CassandraConnector.startConnection();
		CassandraConnector.startSession("furtos");
		
		ResultSet cor  = CassandraConnector.selectFilters("cor",parts[0]);
		ResultSet turno = CassandraConnector.selectFilters("turno",parts[1]);
		ResultSet sexo  = CassandraConnector.selectFilters("sexo",parts[2]);
		
		
		Row result1 = cor.one();
		Row result2 = turno.one();
		Row result3 = sexo.one();
		
		retorno += result1.getInt(0)+",";
		retorno += result2.getInt(0)+",";
		retorno += result3.getInt(0);
		
		CassandraConnector.closeConnection();
		
		
		return retorno;

	}

	
}
