package br.ufc.banco.conta;

import br.ufc.banco.bb.excecoes.TNRException;

public class ContaPoupanca extends Conta {

	public ContaPoupanca(String numero) {
		super(numero);
	}

	public void rendeJuros(double taxa) throws TNRException {
		if(taxa<=0){
			throw new TNRException(new Exception("Taxa inválida! Utilize uma taxa maior que 0."));
		} else this.creditar(this.obterSaldo() * taxa);
	}
}
