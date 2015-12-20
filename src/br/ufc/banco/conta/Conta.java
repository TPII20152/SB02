package br.ufc.banco.conta;

import br.ufc.banco.bb.excecoes.TNRException;
import br.ufc.banco.conta.excecoes.SIException;


public class Conta extends ContaAbstrata {

	public Conta(String numero) {
		super(numero);
	}

	public void debitar(double valor) throws SIException, TNRException {
		if(valor<0){
			throw new TNRException(new Exception("Valor inválido!"));
		} else if (this.saldo >= valor) {
			this.saldo = this.saldo - valor;
		} else {
			throw new SIException(numero, valor);
		}
	}
}