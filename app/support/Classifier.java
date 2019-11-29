package support;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

import cassandra.CassandraConnector;

public class Classifier {

	
	public String processData( String message ) {
		
		String[] parts = message.split(",");
		
		String retorno = "";
		
		CassandraConnector.startConnection();
		CassandraConnector.startSession("furtos");
		System.out.println("fez a conexao");

		
		ResultSet cor  = CassandraConnector.selectFilters("cor",parts[0]);
		
		System.out.println("filtro cor");
		ResultSet turno = CassandraConnector.selectFilters("turno",parts[1]);
		System.out.println("filtro turno");

		ResultSet sexo  = CassandraConnector.selectFilters("sexo",parts[2]);
		System.out.println("filtro sexo");
		
		
		Row result1 = cor.one();
		Row result2 = turno.one();
		Row result3 = sexo.one();

		
		retorno += result1.getLong("coun")+",";
		retorno += result2.getLong("coun")+",";
		retorno += result3.getLong("coun");
		
		CassandraConnector.closeConnection();
		
		return retorno;
		
	}
	
	
}
