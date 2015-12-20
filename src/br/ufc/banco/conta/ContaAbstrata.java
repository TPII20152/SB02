package br.ufc.banco.conta;

import br.ufc.banco.bb.excecoes.TNRException;
import br.ufc.banco.conta.excecoes.SIException;


public abstract class ContaAbstrata {

	protected String numero;
	protected double saldo;

	public ContaAbstrata(String numero) {
		this.numero = numero;
		saldo = 0;
	}

	public void creditar(double valor) throws TNRException{
		if(valor<0){
			throw new TNRException(new Exception("Valor inválido!"));
		} else saldo = saldo + valor;
	}

	public abstract void debitar(double valor) throws SIException, TNRException;

	public String obterNumero() {
		return numero;
	}

	public double obterSaldo() {
		return saldo;
	}
}
