package br.ufc.banco.conta;

import br.ufc.banco.bb.excecoes.TNRException;

public class ContaEspecial extends Conta {

	private double bonus;

	public ContaEspecial(String numero) {
		super(numero);
		bonus = 0;
	}

	public void rendeBonus() throws TNRException {
		super.creditar(bonus);
		bonus = 0;
	}

	public double obterBonus() {
		return bonus;		
	}

	public void creditar(double valor) throws TNRException {
		if(valor<0){
			throw new TNRException(new Exception("Valor inválido!"));
		} else{
		bonus = bonus + (valor * 0.01);
		super.creditar(valor);
		}
	}
	public void setBonus(double valor){
		bonus = valor;
	}
	public void setSaldo(double valor){
		saldo = valor;
	}
}
