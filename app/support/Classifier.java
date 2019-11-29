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
		ResultSet corNula  = CassandraConnector.selectFilters("cor","NULL");
		
		ResultSet turno = CassandraConnector.selectFilters("turno",parts[1]);
		ResultSet turnoNulo  = CassandraConnector.selectFilters("turno","NULL");

		ResultSet sexo  = CassandraConnector.selectFilters("sexo",parts[2]);
		ResultSet sexoNulo  = CassandraConnector.selectFilters("sexo","NULL");
		
		ResultSet total  = CassandraConnector.countAllData();
		
		
		
		Row result1 = cor.one();
		Row result2 = turno.one();
		Row result3 = sexo.one();
		Row result4 = corNula.one();
		Row result5 = turnoNulo.one();
		Row result6 = sexoNulo.one();
		Row result7 = total.one(); 

		
		retorno += result1.getLong("coun")+",";
		retorno += result2.getLong("coun")+",";
		retorno += result3.getLong("coun")+",";
		retorno += result4.getLong("coun")+",";
		retorno += result5.getLong("coun")+",";
		retorno += result6.getLong("coun")+",";
		retorno += result7.getLong("coun");
		
		CassandraConnector.closeConnection();
		
		return retorno;
		
	}
	
	
	public String classifyData( String data ){
		
		String[] parts = data.split(",");
		
		// recupera valores dos parametros analisados
		int qntCor   = Integer.valueOf(parts[0]);
		int qntSexo  = Integer.valueOf(parts[2]);
		int qntTurno = Integer.valueOf(parts[1]);
		int qntFurtos = Integer.valueOf(parts[6]);
		int coresNulas = Integer.valueOf(parts[3]);
		int turnoNulo = Integer.valueOf(parts[4]);
		int sexoNulo = Integer.valueOf(parts[5]);
		
		
		// Classificações: Seguro (0), Pouco seguro (1), Inseguro (2)
		int sexoClassify;
		int corClassify;
		int turnoClassify;
		
		
		// (Parametro sexo) Inseguro( p >= 0.65 ), Pouco Seguro( 0.35 < p < 0.65 ) e Seguro ( p <= 0.35 )	
		if( (qntSexo/(qntFurtos-sexoNulo)) >= 0.65  ){
			sexoClassify = 2;
		} else if( (qntSexo/(qntFurtos-sexoNulo)) <= 0.35 ){
			sexoClassify = 0;
		} else {
			sexoClassify = 1;
		}
		
		
		// (Parametro cor) Inseguro( p >= 0.4 ), Pouco Seguro ( 0.1 < p < 0.4 ) e Seguro( p <= 0.1 )
		if( (qntCor/(qntFurtos-coresNulas)) >= 0.65  ){
			corClassify = 2;
		} else if( (qntCor/(qntFurtos-coresNulas)) <= 0.35 ){
			corClassify = 0;
		} else {
			corClassify = 1;
		}	
		
		// (Parametro turno) Inseguro( p >= 0.6 ), Pouco Seguro( 0.15 < p < 0.6 ) e Seguro( p <= 0.15 )
		if( (qntTurno/(qntFurtos-turnoNulo)) >= 0.6  ){
			turnoClassify = 2;
		} else if( (qntTurno/(qntFurtos-turnoNulo)) <= 0.15 ){
			turnoClassify = 0;
		} else {
			turnoClassify = 1;
		}	
		
		String classif = ""; 
		
		// soma >= 4: inseguro; soma <= 2: seguro; caso contrario: pouco seguro 
		if( (sexoClassify + corClassify + turnoClassify) >= 4 ){
			classif = "INSEGURO";
		} else if( (sexoClassify + corClassify + turnoClassify) <= 2 ) {
			classif = "SEGURO";
		} else {
			classif = "POUCO SEGURO";
		}
			
		return classif;
		

		
	}
	
	
}
