package br.ufc.banco.dados;

import java.util.ArrayList;

import br.ufc.banco.conta.ContaAbstrata;
import br.ufc.banco.dados.excecoes.CEException;
import br.ufc.banco.dados.excecoes.CIException;

public class ArrayContas implements IRepositorioContas {

	private ArrayList<ContaAbstrata> contas = null;
	
	public ArrayContas() {
		this.contas = new ArrayList<ContaAbstrata>();
	}

	public void apagar(String numero) throws CIException {
		ContaAbstrata conta = this.procurar(numero);
		if (conta != null) {
			this.contas.remove(conta);
		} else {
			throw new CIException(numero);
		}
	}

	public void inserir(ContaAbstrata conta) throws CEException {
		if (this.procurar(conta.obterNumero()) == null) {
			this.contas.add(conta);
		} else {
			throw new CEException(conta.obterNumero());
		}
	}

	public ContaAbstrata[] listar() {

		ContaAbstrata[] lista = null;
		if (this.contas.size() > 0) {
			lista = new ContaAbstrata[this.contas.size()];
			for (int i = 0; i < this.contas.size(); i++) {
				lista[i] = (ContaAbstrata) this.contas.get(i);
			}
		}
		return lista;
	}

	public int numeroContas() {
		return this.contas.size();
	}

	public ContaAbstrata procurar(String numero) {
		if (this.contas.size() > 0) {
			for (int i = 0; i < this.contas.size(); i++) {
				ContaAbstrata conta = (ContaAbstrata) this.contas.get(i);
				if (conta.obterNumero().equals(numero)) {
					return conta;
				}
			}
		}
		return null;
	}
}
