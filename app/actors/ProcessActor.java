package actors;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.actor.AbstractActor.Receive;
import kafka.Manager;

public class ProcessActor extends AbstractActor{

    public ProcessActor() {
    	
    }
	
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
		        	getSender().tell(msg+", I'm an actor!", self());
		        })        
				.build();
	}
	
	
	private String process(String line) {
		

		// Processar mensagem
		String[] parts = line.split(",");
		
		SparkConf conf = new SparkConf()//
				.setAppName("MyApp")//
				.setMaster("spark://10.128.0.6:7077");		
		JavaSparkContext sc = new JavaSparkContext(conf);
    	JavaRDD<Integer> numbers = sc.parallelize(Arrays.asList(14,21,88,99,455)); 
    	int num = numbers.first();
    	sc.close();
        
		
		return "Processou: "+parts[0]+" e "+parts[1]+" e "+ parts[2] + " Spark:"+num;
		
	}

}
