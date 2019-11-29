package support;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class ArchiveRead {

	
	// dataset
	private File file1;
	private File file2;
	private File file3;
	
	// leitor de arquivo
	private Scanner arc1;
	private Scanner arc2;
	private Scanner arc3;
	

	/**
	 * Construtor. Referencia arquivo
	 * @throws FileNotFoundException 
	 * @throws IOException
	 */
	public ArchiveRead() throws FileNotFoundException {
		
		/**
		 * DATASETs
		 */
		// caminho do dataset
		//String fileName1 = "data/RDO_1.csv";
		//String fileName2 = "data/RDO_2.csv";
		String fileName3 = "data/RDO_3.csv";
		
		
		// dataset
		//this.file1 = new File(fileName1);
		//this.file2 = new File(fileName2);
		this.file3 = new File(fileName3);
		
		
		// leitor de arquivo
		//this.arc1 = new Scanner(file1);
		//this.arc2 = new Scanner(file2);
		this.arc3 = new Scanner(file3);
		
		
		// ignora primeira linha: cabecalho
		//arc1.nextLine();
		//arc2.nextLine();
		arc3.nextLine();
		
		
	}
	
	
	/**
	 * Recupera proxima linha do arquivo
	 * @return linha corrente
	 */
	public String getLine() {
		
		String line = "";
		
		// recupera proxima linha do arquivo
		/*if( arc1.hasNextLine() ) {
			line = arc1.nextLine();			
		} else if( arc2.hasNextLine() ){
			line = arc2.nextLine();
		} else*/ if( arc3.hasNextLine() ){
			line = arc3.nextLine();
		}
		
		System.out.println(line);
		
		return line;
		
	}
	
	
	/**
	 * Processa dados de arquivo para memória
	 */
	public String process( String line ) {
		
		String memLine = "";

		// separa linha por virgula
		String[] parts = line.split(",");
				
		int tam = parts.length;
		
		// verifica se é furto
		String rubrica = (parts[17].split(" "))[0];

		// se for, pode analisar o restante dos casos
		if("\"Furto".equals(rubrica) || "Furto".equals(rubrica) ) {
							
			 

			// recupera a coluna referente a "cor" 
			String corLine = parts[ tam-1 ].trim();
			
			
			// verifica se a cor corrente é uma das possíveis
			if( corLine.equalsIgnoreCase("PRETA")   || 
				corLine.equalsIgnoreCase("BRANCA")  || 
				corLine.equalsIgnoreCase("AMARELA") || 
				corLine.equalsIgnoreCase("PARDA")   ){
				
				memLine += corLine + ",";
				
			// verifica se cor é nula
			} else if( corLine.equalsIgnoreCase("NULL") ){
				memLine += "NULL" + ",";
			
			} else {
				memLine += "OUTRAS" + ",";
			}
				
			
			// recupera a coluna referente a "hora"
			if( !parts[14].equals("NULL")){					
				
				int hourLine = 0;
				
				// trata o formato da hora
				if( parts[14].split(":").length > 1 ){
					hourLine = Integer.parseInt((parts[14].split(":")[0]));
				} else if( parts[14].split("H").length > 1 ){
					hourLine = Integer.parseInt((parts[14].split("H")[0]));
				}
					
				
				String turnoLine = "";
				
				// classifica turno em manha, tarde, noite, madrugada
				if( hourLine >= 0 && hourLine < 6 ){
					turnoLine = "Madrugada";
				} else if( hourLine >= 6 && hourLine < 12 ){
					turnoLine = "Manha";
				} else if( hourLine >= 12 && hourLine < 18 ){
					turnoLine = "Tarde";
				} else {
					turnoLine = "Noite";
				}
				
				memLine += turnoLine + ",";
				

			} else {
				memLine += "NULL" + ",";
			}
			
			
			// recupera coluna referente ao "sexo"
			String sexoLine = parts[ tam-3 ];
			memLine += sexoLine;			
			
		}

		return memLine;	
		

	}

	
}
