package br.ufc.banco.teste;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufc.banco.bb.excecoes.TNRException;
import br.ufc.banco.conta.ContaImposto;
import br.ufc.banco.conta.excecoes.SIException;
import junit.framework.TestCase;

public class ContaImpostoTeste extends TestCase{
	ContaImposto cpmf;
	@Before
	public void setUp() throws Exception {
		cpmf = new ContaImposto("12345");
		cpmf.creditar(1000);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDebitar() throws TNRException, SIException {
		double saldo = cpmf.obterSaldo();
		double valor = 500.0;
		cpmf.debitar(valor);
		assertEquals(saldo - valor*1.001, cpmf.obterSaldo(), 0.000001);
	}

	@Test
	public void testDebitarNegativo() {
		double saldo = cpmf.obterSaldo();
		double valor = -500.0;
		try {
			cpmf.debitar(valor);
		} catch (TNRException | SIException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(saldo, cpmf.obterSaldo(), 0.000001);
	}
}
