package br.ufc.banco.database;

import br.ufc.banco.conta.Conta;
import br.ufc.banco.conta.ContaEspecial;
import br.ufc.banco.conta.ContaImposto;
import br.ufc.banco.conta.ContaPoupanca;
import br.ufc.banco.dados.excecoes.CEException;

public class TesteDao {

	public static void main(String[] args) {
		//SQLiteConnection sqLiteConnection;
		SQLiteContas contaDao = new SQLiteContas();
		//contaDao.createDB();
		Conta conta = new Conta("123");
		conta.creditar(100);
		ContaEspecial contaEspecial= new ContaEspecial("234");
		contaEspecial.creditar(300);
		ContaPoupanca contaPoupanca = new ContaPoupanca("345");
		contaPoupanca.creditar(1000);
		ContaImposto contaImposto = new ContaImposto("456");
		contaImposto.creditar(500);
		
		try {
			contaDao.inserir(contaImposto);
			contaDao.inserir(conta);
			contaDao.inserir(contaPoupanca);
			contaDao.inserir(contaEspecial);
		} catch (CEException e) {
			e.printStackTrace();
		}
		//contaDao.apagar("123");
		//contaDao.apagar("456");
	}

}
