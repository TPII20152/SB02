package br.ufc.banco.teste;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufc.banco.bb.excecoes.TNRException;
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
		try {
			contaEspecial.creditar(100);
		} catch (TNRException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(saldo + 100, contaEspecial.obterSaldo());
	}

	@Test(expected = TNRException.class)
	public void testCreditarNegativo() {
		double saldo = contaEspecial.obterSaldo();
		try {
			contaEspecial.creditar(-100);
		} catch (TNRException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(saldo, contaEspecial.obterSaldo());
	}
	
	@Test
	public void testBonus() {
		try {
			contaEspecial.creditar(1000);
		} catch (TNRException e) {
			System.out.println(e.getMessage());
		}
		double saldo = contaEspecial.obterSaldo();
		assertEquals(saldo*0.01, contaEspecial.obterBonus());
	}

	@Test(expected = TNRException.class)
	public void testBonusNegativo() {
		double bonus = contaEspecial.obterBonus();
		try {
			contaEspecial.creditar(-100);
		} catch (TNRException e) {
			System.out.println(e.getMessage());
		}
		//double saldo = contaEspecial.obterSaldo();
		assertEquals(bonus, contaEspecial.obterBonus());
	}
	
	@Test
	public void testObterBonus() {
		double saldo = 0.0;
		try {
			contaEspecial.creditar(100);
			saldo = contaEspecial.obterSaldo();
			
			double bonus = contaEspecial.obterBonus();
			assertEquals(saldo*0.01, bonus);
			contaEspecial.rendeBonus();
		} catch (TNRException e) {
			System.out.println(e.getMessage());
		}
		
		assertEquals(saldo*1.01, contaEspecial.obterSaldo());
	}

}
