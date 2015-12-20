package br.ufc.banco.teste;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufc.banco.bb.excecoes.TNRException;
import br.ufc.banco.conta.Conta;
import br.ufc.banco.conta.excecoes.SIException;
import junit.framework.TestCase;

public class ContaTeste extends TestCase{

	Conta conta;
	
	@Before
	public void setUp() throws Exception {
		conta = new Conta("123");
		conta.creditar(1000);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDebitar() {
		double saldo = conta.obterSaldo();
		double debito = 100.0;
		try {
			conta.debitar(debito);
		} catch (SIException e) {
			e.printStackTrace();
		} catch (TNRException e) {
			e.printStackTrace();
		}
		assertEquals(saldo - debito, conta.obterSaldo(), 0.00001);
	}
	
	@Test(expected = TNRException.class)
	public void testDebitarNegativo() {
		double saldo = conta.obterSaldo();
		try {
			conta.debitar(-100);
		} catch (SIException e) {
			System.out.println(e.getMessage());
		} catch (TNRException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(saldo, conta.obterSaldo(), 0.00001);
	}
	
	@Test
	public void testCreditar() {
		double saldo = conta.obterSaldo();
		try {
			conta.creditar(100);
		} catch (TNRException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(saldo + 100, conta.obterSaldo(), 0.00001);
	}

	@Test(expected = TNRException.class)
	public void testCreditarNegativo() {
		double saldo = conta.obterSaldo();
		try {
			conta.creditar(-100);
		} catch (TNRException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(saldo, conta.obterSaldo(), 0.00001);
	}
}
