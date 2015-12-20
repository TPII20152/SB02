package br.ufc.banco.teste;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufc.banco.bb.excecoes.TNRException;
import br.ufc.banco.conta.ContaPoupanca;

public class ContaPoupancaTeste extends TestCase{
	ContaPoupanca poupanca;
	@Before
	public void setUp() throws Exception {
		poupanca = new ContaPoupanca("123456");
		poupanca.creditar(100);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRendeJuros() {
		double saldo = poupanca.obterSaldo();
		double taxa = 0.02;
		try {
			poupanca.rendeJuros(taxa);
		} catch (TNRException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(saldo*(1+taxa), poupanca.obterSaldo());
	}

	@Test(expected = TNRException.class)
	public void testRendeJurosTaxaNegativa() {
		double saldo = poupanca.obterSaldo();
		double taxa = -0.02;
		try {
			poupanca.rendeJuros(taxa);
		} catch (TNRException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(saldo, poupanca.obterSaldo());
	}
}
