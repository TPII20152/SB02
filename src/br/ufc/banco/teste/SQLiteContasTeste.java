package br.ufc.banco.teste;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufc.banco.conta.Conta;
import br.ufc.banco.conta.ContaAbstrata;
import br.ufc.banco.conta.ContaEspecial;
import br.ufc.banco.conta.ContaImposto;
import br.ufc.banco.conta.ContaPoupanca;
import br.ufc.banco.dados.SQLiteContas;
import br.ufc.banco.dados.excecoes.CEException;
import junit.framework.TestCase;

public class SQLiteContasTeste extends TestCase {
	SQLiteContas sqliteContas;
	Conta c1,c2;
	ContaEspecial ce1,ce2;
	ContaPoupanca cp1,cp2;
	ContaImposto ci1, ci2;
	ArrayList<String> contasInseridas = new ArrayList<String>();
	
	@Before
	public void setUp() throws Exception {
		sqliteContas = new SQLiteContas();
		c1 = new Conta("1");
		c1.creditar(200);
		c2 = new Conta("2");
		c2.creditar(300);
		ce1 = new ContaEspecial("3");
		ce1.creditar(400);
		ce2 = new ContaEspecial("4");
		ce2.creditar(400);
		cp1 = new ContaPoupanca("5");
		cp1.creditar(500);
		cp2 = new ContaPoupanca("6");
		cp2.creditar(600);
		ci1 = new ContaImposto("7");
		ci1.creditar(700);
		ci2 = new ContaImposto("8");
		ci2.creditar(800);
	}

	@After
	public void tearDown() throws Exception {
		for(String numero: contasInseridas){
			sqliteContas.apagar(numero);
		}
		/*sqliteContas.apagar(c1.obterNumero());
		sqliteContas.apagar(c2.obterNumero());
		sqliteContas.apagar(ce1.obterNumero());
		sqliteContas.apagar(ce2.obterNumero());
		sqliteContas.apagar(cp1.obterNumero());
		sqliteContas.apagar(cp2.obterNumero());
		sqliteContas.apagar(ci1.obterNumero());
		sqliteContas.apagar(ci2.obterNumero());*/
	}

	@Test
	public void testInserir() {
		int quantidade = sqliteContas.numeroContas();
		try {
			sqliteContas.inserir(c1);
			contasInseridas.add(c1.obterNumero());
		} catch (CEException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(quantidade, sqliteContas.numeroContas());
	}

	@Test
	public void testProcurar() {
		try {
			sqliteContas.inserir(c1);
			contasInseridas.add(c1.obterNumero());
		} catch (CEException e) {
			System.out.println(e.getMessage());
		}
		ContaAbstrata pesquisada = sqliteContas.procurar(c1.obterNumero());
		assertNotNull(pesquisada);
		assertEquals(c1.obterNumero(), pesquisada.obterNumero());
		assertEquals(c1.obterSaldo(), pesquisada.obterSaldo());
	}

	/*@Test
	public void testInserirBonus() {
		fail("Not yet implemented");
	}

	@Test
	public void testAtualizarConta() {
		fail("Not yet implemented");
	}

	@Test
	public void testAtualizarBonus() {
		fail("Not yet implemented");
	}

	@Test
	public void testApagar() {
		fail("Not yet implemented");
	}*/

	@Test
	public void testGetTipo() {
		int tipoConta = sqliteContas.getTipo(c1);
		int tipoContaEspecial = sqliteContas.getTipo(ce1);
		int tipoContaPoupanca = sqliteContas.getTipo(cp1);
		int tipoContaImposto = sqliteContas.getTipo(ci1);
		assertEquals(1, tipoConta);
		assertEquals(2, tipoContaEspecial);
		assertEquals(3, tipoContaPoupanca);
		assertEquals(4, tipoContaImposto);
	}

	/*@Test
	public void testCriarConta() {
		fail("Not yet implemented");
	}

	@Test
	public void testCriarContaEspecial() {
		fail("Not yet implemented");
	}

	@Test
	public void testListar() {
		fail("Not yet implemented");
	}*/

	@Test
	public void testNumeroContas() {
		int quantidade = sqliteContas.numeroContas();
		assertNotNull(quantidade);
	}

}
