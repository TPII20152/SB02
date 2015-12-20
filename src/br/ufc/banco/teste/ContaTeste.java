package br.ufc.banco.teste;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufc.banco.conta.Conta;
import br.ufc.banco.conta.excecoes.SIException;

public class ContaTeste extends TestCase{

	Conta conta;
	
	@Before
	public void setUp() throws Exception {
		conta = new Conta("123");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDebitar() {
		double saldo = conta.obterSaldo();
		try {
			conta.debitar(100);
		} catch (SIException e) {
			e.printStackTrace();
		}
		assertEquals(saldo - 100, conta.obterSaldo(), 0.00001);
	}
	
	@Test
	public void testDebitarNegativo() {
		double saldo = conta.obterSaldo();
		try {
			conta.debitar(100);
		} catch (SIException e) {
			e.printStackTrace();
		}
		assertEquals(saldo, conta.obterSaldo(), 0.00001);
	}
	
	@Test
	public void testCreditar() {
		double saldo = conta.obterSaldo();
		conta.creditar(100);
		assertEquals(saldo + 100, conta.obterSaldo(), 0.00001);
	}

	@Test
	public void testCreditarNegativo() {
		double saldo = conta.obterSaldo();
		conta.creditar(-100);
		assertEquals(saldo, conta.obterSaldo(), 0.00001);
	}
}
