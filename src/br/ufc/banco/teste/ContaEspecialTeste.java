package br.ufc.banco.teste;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufc.banco.conta.ContaEspecial;
import junit.framework.TestCase;

public class ContaEspecialTeste extends TestCase{

	ContaEspecial contaEspecial;
	
	@Before
	public void setUp() throws Exception {
		contaEspecial = new ContaEspecial("1234");
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testCreditar() {
		double saldo = contaEspecial.obterSaldo();
		contaEspecial.creditar(100);
		assertEquals(saldo + 100, contaEspecial.obterSaldo());
	}

	@Test
	public void testCreditarNegativo() {
		double saldo = contaEspecial.obterSaldo();
		contaEspecial.creditar(-100);
		assertEquals(saldo, contaEspecial.obterSaldo());
	}
	
	@Test
	public void testRendeBonus() {
		contaEspecial.creditar(1000);
		double saldo = contaEspecial.obterSaldo();
		assertEquals(saldo*0.01, contaEspecial.obterBonus());
	}

	@Test
	public void testRendeBonusNegativo() {
		contaEspecial.creditar(-100);
		double saldo = contaEspecial.obterSaldo();
		assertEquals(saldo*0.01, contaEspecial.obterBonus());
	}
	
	@Test
	public void testObterBonus() {
		contaEspecial.creditar(100);
		double saldo = contaEspecial.obterSaldo();
		
		double bonus = contaEspecial.obterBonus();
		assertEquals(saldo*0.01, bonus);
		contaEspecial.rendeBonus();
		assertEquals(saldo*1.01, contaEspecial.obterSaldo());
	}

}
