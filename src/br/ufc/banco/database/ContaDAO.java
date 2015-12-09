package br.ufc.banco.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.ufc.banco.conta.Conta;
import br.ufc.banco.conta.ContaAbstrata;
import br.ufc.banco.conta.ContaEspecial;
import br.ufc.banco.conta.ContaImposto;
import br.ufc.banco.conta.ContaPoupanca;

public class ContaDAO {
	//private SQLiteConnection sqLiteConnection;
	private Connection connection;
	public static final String TABELA_CONTAS = "contas";
	public static final String TABELA_BONUS = "bonus";
	
	public ContaDAO(Connection connection) {
		super();
		this.connection = connection;
	}

	/*public static void main( String args[] )
	  {
	      connectDB();
	      createDB();
	      insertDB();
	      selectDB();
	      //updateDB();
	      //deleteDB();
	  }*/
	   
	  public void connectDB()
	  {
	      //Connection c = null;
	        /*try {
	          Class.forName("org.sqlite.JDBC");
	          connection = DriverManager.getConnection("jdbc:sqlite:myBlog.sqlite");
	        } catch ( Exception e ) {
	          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	          System.exit(0);
	        }
	        */System.out.println("Opened database successfully");
	  }
	  public void createDB()
	  {
	      //Connection connection = null;
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
	   
	  public void inserirConta(ContaAbstrata conta)
	  {
	      //Connection connnection = null;
		    int tipo = getTipo(conta);
	        Statement stmt = null;
	        try {
	          /*Class.forName("org.sqlite.JDBC");
	          connnection = DriverManager.getConnection("jdbc:sqlite:myBlog.sqlite");
	          connnection.setAutoCommit(false);
	          System.out.println("Opened database successfully");*/
	          String values = "VALUES ("+conta.obterNumero() + ", "+ conta.obterSaldo()+", "+tipo+");";
	          stmt = connection.createStatement();
	          String sql = "INSERT INTO "+  TABELA_CONTAS + "(numero,saldo,tipo) " + values;
	                       
	          stmt.executeUpdate(sql);
	          stmt.close();
	          connection.commit();
	          connection.close();
	        } catch ( Exception e ) {
	          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	          System.exit(0);
	        }
	        System.out.println("Records created successfully");
	  }
	   
	  public ContaAbstrata buscarConta(int numero)
	  {
	        ContaAbstrata conta = null;
	        Statement stmt = null;
	        try {
	          /*Class.forName("org.sqlite.JDBC");
	          connection = DriverManager.getConnection("jdbc:sqlite:myBlog.sqlite");
	          connection.setAutoCommit(false);
	          System.out.println("Opened database successfully");*/
	 
	          stmt = connection.createStatement();
	          ResultSet rs = stmt.executeQuery( "SELECT * FROM "+TABELA_CONTAS+
	        		  							"WHERE numero= "+ numero+";");
	             rs.next();
	             String num = rs.getString("numero");
	             double  saldo = rs.getDouble("message");
	             int tipo = rs.getInt("tipo");
	             conta = criarConta(tipo, num);
	             if(saldo >= 0) conta.creditar(saldo); 
	             	else conta.debitar(saldo);
	             /*System.out.println( "ID : " + id );
	             System.out.println( "Name : " + name );
	             System.out.println( "Message : " + message );
	             System.out.println( "Date Added : " + date_added );
	             System.out.println();*/
	          
	          rs.close();
	          stmt.close();
	          connection.close();
	        } catch ( Exception e ) {
	          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	          System.exit(0);
	        }
	        System.out.println("Operation done successfully");
	        return conta;
	  }
	   
	  public void atualizarConta(ContaAbstrata conta)
	  {
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
	   
	  public void apagarConta(ContaAbstrata conta)
	  {
	        Statement stmt = null;
	        try {
	          stmt = connection.createStatement();
	          String sql = "DELETE from "+TABELA_CONTAS+" where numero="+conta.obterNumero()+";";
	          stmt.executeUpdate(sql);
	          connection.commit();
	          stmt.close();
	          
	          if(getTipo(conta)==2){
	        	  stmt = connection.createStatement();
		          String sqlBonus = "DELETE from "+TABELA_BONUS+" where numero="+conta.obterNumero()+";";
		          stmt.executeUpdate(sqlBonus);
		          connection.commit();
		          stmt.close();
	          }
	          
	          connection.close();
	        } catch ( Exception e ) {
	          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	          System.exit(0);
	        }
	        System.out.println("Operation done successfully");
	  }
	  public int getTipo(ContaAbstrata conta){
		  if(conta instanceof Conta){
			  return 1;
		  } else if(conta instanceof ContaEspecial){
			  return 2;
		  }  else if(conta instanceof ContaPoupanca){
			  return 3;
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
}
