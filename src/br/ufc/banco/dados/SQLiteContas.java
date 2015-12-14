package br.ufc.banco.dados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.ufc.banco.bb.util.SQLiteConnector;
import br.ufc.banco.conta.Conta;
import br.ufc.banco.conta.ContaAbstrata;
import br.ufc.banco.conta.ContaEspecial;
import br.ufc.banco.conta.ContaImposto;
import br.ufc.banco.conta.ContaPoupanca;
import br.ufc.banco.dados.excecoes.CEException;

public class SQLiteContas implements IRepositorioContas{
	private Connection connection;
	public static final String TABELA_CONTAS = "contas";
	public static final String TABELA_BONUS = "bonus";
	
	public SQLiteContas() {
		super();
	}

	  public void createDB()
	  {
		    connection = SQLiteConnector.getConnection();
	        Statement stmt = null;
	        
	          try {
				stmt = connection.createStatement();
			
	          String sqlTables = "CREATE TABLE IF NOT EXISTS contas " +
	                       "(numero INTEGER PRIMARY KEY ," +
	                       " saldo  REAL NOT NULL, " + 
	                       " tipo   INTEGER     NOT NULL) ";
	          stmt.executeUpdate(sqlTables);
	          stmt.close();
	          stmt = connection.createStatement();
				
	          String sqlBonus = "CREATE TABLE IF NOT EXISTS bonus " +
	                       "(numero INTEGER PRIMARY KEY ," +
	                       " bonus  REAL NOT NULL0 "; 
	          stmt.executeUpdate(sqlBonus);
	          stmt.close();
	          connection.close();
	          } catch (SQLException e) {
					e.printStackTrace();
				}
	        System.out.println("Tables created successfully");
	  }
	   
	  public void inserir(ContaAbstrata conta) throws CEException{
		  if(procurar(conta.obterNumero()) == null){
			  connection = SQLiteConnector.getConnection();
			    int tipo = getTipo(conta);
		        Statement stmt = null;
		        try {
		          String values = "VALUES ("+conta.obterNumero() + ", "+ conta.obterSaldo()+", "+tipo+");";
		          stmt = connection.createStatement();
		          String sql = "INSERT INTO "+  TABELA_CONTAS + "(numero,saldo,tipo) " + values;
		                       
		          stmt.executeUpdate(sql);
		          stmt.close();
		          //connection.commit();
		          connection.close();
		        } catch ( Exception e ) {
		          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		          System.exit(0);
		        }
		        System.out.println("Records created successfully");
		  } else {
			  throw new CEException(conta.obterNumero());
		  }
		  
		  
	  }
	   
	  public ContaAbstrata procurar(String numero)
	  {
		  	connection = SQLiteConnector.getConnection();
	        ContaAbstrata conta = null;
	        Statement stmt = null;
	        try {
	          stmt = connection.createStatement();
	          ResultSet rs = stmt.executeQuery( "SELECT * FROM "+TABELA_CONTAS+
	        		  							" WHERE numero = "+ numero+";");
	             
	          if(rs.next()){
	            	 String num = rs.getString("numero");
		             double  saldo = rs.getDouble("saldo");
		             int tipo = rs.getInt("tipo");
		             conta = criarConta(tipo, num);
		             if(saldo >= 0) conta.creditar(saldo); 
		             	else conta.debitar(saldo);
	             }
	          rs.close();
	          stmt.close();
	          connection.close();
	        } catch ( Exception e ) {
	          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	          //System.exit(0);
	        }
	        System.out.println("Operation done successfully");
	        return conta;
	  }
	   
	  public void atualizarConta(ContaAbstrata conta)
	  {
		connection = SQLiteConnector.getConnection();  
	    Statement stmt = null;
	    try {
	      stmt = connection.createStatement();
	      //String values = "VALUES ("
	      String sql = "UPDATE "+TABELA_CONTAS+" set saldo="+conta.obterSaldo() + " WHERE numero="+conta.obterNumero()+";";
	      stmt.executeUpdate(sql);
	      connection.commit();
	 
	      stmt.close();
	      connection.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Operation done successfully");
	  }
	   
	  public void apagar(String numero)
	  {
		    connection = SQLiteConnector.getConnection();
	        Statement stmt = null;
	        try {
	          stmt = connection.createStatement();
	          String sql = "DELETE from "+TABELA_CONTAS+" where numero="+numero+";";
	          stmt.executeUpdate(sql);
	          connection.commit();
	          stmt.close();
	          
	          /*if(getTipo(conta)==2){
	        	  stmt = connection.createStatement();
		          String sqlBonus = "DELETE from "+TABELA_BONUS+" where numero="+numero+";";
		          stmt.executeUpdate(sqlBonus);
		          connection.commit();
		          stmt.close();
	          }*/
	          
	          connection.close();
	        } catch ( Exception e ) {
	          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	          System.exit(0);
	        }
	        System.out.println("Operation done successfully");
	  }
	  public int getTipo(ContaAbstrata conta){
		  if(conta instanceof ContaEspecial){
			  return 2;
		  } else if(conta instanceof ContaPoupanca){
			  return 3;
		  }  else if(conta instanceof Conta){
			  return 1;
		  }  else {
			  return 4;
		  }
	  }
	  public ContaAbstrata criarConta(int tipo, String num){
		  
		  switch(tipo){
		  	case 1: return new Conta(num);
		  	case 2: return new ContaEspecial(num);
		  	case 3: return new ContaPoupanca(num);
		  	case 4: return new ContaImposto(num);
		  	default: return null;
		  }
	  }

	@Override
	public ContaAbstrata[] listar() {
		int tamanho = numeroContas();
		connection = SQLiteConnector.getConnection();
        ContaAbstrata conta = null;
        ContaAbstrata[] contas = new ContaAbstrata[tamanho];
        Statement stmt = null;
        if(tamanho != 0 ){
        	try {
                stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery( "SELECT * FROM "+TABELA_CONTAS+";");
                int i = 0;   
                while(rs.next()){
                  	 String num = rs.getString("numero");
      	             double  saldo = rs.getDouble("saldo");
      	             int tipo = rs.getInt("tipo");
      	             conta = criarConta(tipo, num);
      	             if(saldo >= 0) conta.creditar(saldo); 
      	             	else conta.debitar(saldo);
      	             contas[i] = conta;
      	             i++;
                   }
                rs.close();
                stmt.close();
                connection.close();
              } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
              }
              System.out.println("Operation done successfully");
        }
		return contas;
	}

	@Override
	public int numeroContas() {
		connection = SQLiteConnector.getConnection();
        int quantidade = 0;
        Statement stmt = null;
        try {
          stmt = connection.createStatement();
          ResultSet rs = stmt.executeQuery( "SELECT COUNT(*) FROM "+TABELA_CONTAS+";");
             
          if(rs.next()){
            	 quantidade = rs.getInt("count(*)");
             }
          rs.close();
          stmt.close();
          connection.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        System.out.println("Operation done successfully");
		return quantidade;
	}
}
