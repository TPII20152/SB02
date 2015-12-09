package br.ufc.banco.conta;


public class ContaImposto extends ContaAbstrata {

	public ContaImposto(String numero) {
		super(numero);
	}

	public void debitar(double valor) {
		double novoValor = this.saldo - (valor + (valor * 0.001));
		if(novoValor >= 0){
			this.saldo = novoValor;
		}
		else{
			System.out.println("Saldo insuficiente!");
		}
	}
}
