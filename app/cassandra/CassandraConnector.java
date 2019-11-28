package cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class CassandraConnector {
		
	private static Cluster cluster;
    private static Session session;
 
    public static Cluster connect(String node) {
        return Cluster
        		.builder()
        		.addContactPoint(node)
        		.build();  
    }
    
    public static void startConnection() {
    	try {
        	cluster = connect("10.128.0.4");	
        	//cluster = connect("146.148.75.137");	
        	
    	} catch (Exception e) {
    		System.out.println("CONEXÃO INCOMPLETA");
		}
    }
    
    public static void startSession( String KeySpaceName ) {
    	try {
    		
    		session = cluster.connect(KeySpaceName);
    		
    	} catch (Exception e) {
    		
		}
    }
    
       
    public static void createKeyspace(String keyspaceName) {
    	
    	session = cluster.connect();
    	System.out.println("conectou?");
    	
    	session.execute("CREATE KEYSPACE "+keyspaceName+ " WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1}");
    	
	}
    
    public static void deleteKeyspace( String keyspaceName ) {
    	session.execute("DROP KEYSPACE "+keyspaceName);
    }
    
    public static void closeConnection() {
    	session.close();
    	cluster.close();
    }
    
    public static void createTable( String tableName, String[] columnsName, String[] dataType, int primaryKey ) {
    	
    	String cquery = "";
    	for( int i=0; i<columnsName.length; i++ ){
    		cquery += columnsName[i] +" " +dataType[i] +",";
    	}
    	
    	cquery += "PRIMARY KEY (" + columnsName[primaryKey] +")";
    	
    	session.execute("CREATE TABLE "+tableName+ " ("+cquery+")");
    	
    }
    
    public static void dropTable( String table ){
    	session.execute("DROP TABLE "+table);
    }
    
    
    public static void insertData( String table, String[] columns, String[] valores ){
    	
    	String campos = "";
    	String values = "";
    	
		campos += columns[0]+",";
    	values += Integer.parseInt(valores[0])+",";
    	
    	for( int i=1; i<columns.length; i++ ) {
    		campos += columns[i]+",";
    		
    		values += "'"+valores[i]+"',";
    		
    	}
    	
    	campos = campos.substring(0, campos.length()-1);
    	values = values.substring(0, values.length()-1);
    	
    	session.execute("INSERT INTO "+table+" ("+campos+") VALUES ("+values+")");
    	
    	
    }
    
}
