package br.ufc.banco.database;

import br.ufc.banco.conta.Conta;
import br.ufc.banco.conta.ContaEspecial;
import br.ufc.banco.conta.ContaImposto;
import br.ufc.banco.conta.ContaPoupanca;

public class TesteDao {

	public static void main(String[] args) {
		//SQLiteConnection sqLiteConnection;
		ContaDAO contaDao = new ContaDAO(SQLiteConnection.getConnection());
		//contaDao.createDB();
		Conta conta = new Conta("123");
		conta.creditar(100);
		ContaEspecial contaEspecial= new ContaEspecial("234");
		contaEspecial.creditar(300);
		ContaPoupanca contaPoupanca = new ContaPoupanca("345");
		contaPoupanca.creditar(1000);
		//ContaImposto contaImposto = new ContaImposto("456");
		//contaImposto.creditar(500);
		
		//contaDao.inserirConta(contaImposto);
		contaDao.inserirConta(conta);
		contaDao.inserirConta(contaPoupanca);
		contaDao.inserirConta(contaEspecial);
	}

}
