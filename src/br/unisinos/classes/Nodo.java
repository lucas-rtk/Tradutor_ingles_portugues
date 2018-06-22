/*
 * Estruturas avançadas de dados - 2018/1 - Unisinos
 * Prof. Marcio Garcia Martins
 * Projeto: Tradutor inglês-Português utilizando uma árvore AVL
 * Classe: Nodo da árvore AVL
 */
package br.unisinos.classes;

public class Nodo<T> {

	private T valor;
	private Nodo<T> pai;
	private Nodo<T> esquerdo;
	private Nodo<T> direito;
	private int fator;

	public Nodo() {
		this.esquerdo = this.direito = this.pai = null;
	}

	public Nodo(T valor) {
		this(valor, null, null);
	}

	public Nodo(T valor, Nodo<T> esquerdo, Nodo<T> direito) {
		this.esquerdo = esquerdo;
		this.direito = direito;
		this.valor = valor;
		this.fator = 0;
	}

	public T getValor() {
		return this.valor;
	}

	public void setValor(T valor) {
		this.valor = valor;
	}

	public Nodo<T> getEsquerdo() { return this.esquerdo; }

	public void setEsquerdo(Nodo<T> nodo) {
		this.esquerdo = nodo;

		if (nodo != null)
			nodo.setPai(this);
	}

	public Nodo<T> getDireito() { return this.direito; }

	public void setDireito(Nodo<T> nodo) {
		this.direito = nodo;

		if (nodo != null)
			nodo.setPai(this);
	}
	
	public int getFator() { return this.fator; }
	
	public Nodo<T> getPai() { return this.pai; }	
	
	public void setPai(Nodo<T> nodo) {
		this.pai = nodo;
	}
		
	public void calcularBalanceamento() {
		this.fator = calcularAltura(this.esquerdo) - calcularAltura(this.direito);

		//Calcula o fator de balanceamento dos filhos
		if (this.esquerdo != null)
			this.esquerdo.calcularBalanceamento();

		if (this.direito != null)
			this.direito.calcularBalanceamento();
	}

	private int calcularAltura(Nodo<T> nodo) {
		if (nodo == null)
			return 0;

		//Verifica a altura do maior filho
		return 1 + Math.max(calcularAltura(nodo.esquerdo), calcularAltura(nodo.direito));
	}
}