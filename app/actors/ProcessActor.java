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

    
    public Integer num = 0;

    public ProcessActor() {
		SparkConf conf = new SparkConf(true)//
				.setAppName("MyApp")//
				.setMaster("spark://10.128.0.6:7077");		
   		JavaSparkContext sc = new JavaSparkContext(conf);
    	JavaRDD<Integer> numbers = sc.parallelize(Arrays.asList(14,21,88,99,455)); 
		num = numbers.first();
		sc.close();
    }
	
	public static Props props() {
		return Props.create(ProcessActor.class);
	}
	
	
	
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(String.class, s -> {
					sender().tell(s+", I'm an actor using Spark! The first element of the RDD is "+ String.valueOf(num), self());
				})
		        .build();
	}
	
	/*

<<<<<<< HEAD
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
=======
>>>>>>> 1be2e2a5aef77a5f7d52fc51554b883f06ea19f9
*/
}
