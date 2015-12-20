package br.ufc.banco.conta;

import br.ufc.banco.bb.excecoes.TNRException;
import br.ufc.banco.conta.excecoes.SIException;

public class ContaImposto extends ContaAbstrata {

	public ContaImposto(String numero) {
		super(numero);
	}

	public void debitar(double valor) throws TNRException, SIException{
		if(valor<0){
			throw new TNRException(new Exception("Valor inválido!"));
		} else{
			double novoValor = this.saldo - (valor + (valor * 0.001));
			if(novoValor >= 0){
				this.saldo = novoValor;
			}
			else{
				throw new SIException(numero, this.obterSaldo());
			}
		}
		
	}
}
