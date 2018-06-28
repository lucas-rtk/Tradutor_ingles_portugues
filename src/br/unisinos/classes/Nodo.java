/*
 * Estruturas avan�adas de dados - 2018/1 - Unisinos
 * Prof. Marcio Garcia Martins
 * Projeto: Tradutor ingl�s-Portugu�s utilizando uma �rvore AVL
 * Classe: Nodo da �rvore AVL
 */
package br.unisinos.classes;

public class Nodo<T> {

	private T valor;
	private Nodo<T> pai;
	private Nodo<T> esquerdo;
	private Nodo<T> direito;
	private int fator;

	//Construtor b�sico da classe
	public Nodo() {
		this.esquerdo = this.direito = this.pai = null;
	}

	//Construtor apenas com o valor do nodo
	public Nodo(T valor) {
		this(valor, null, null);
	}

	//Construtor completo
	public Nodo(T valor, Nodo<T> esquerdo, Nodo<T> direito) {
		this.esquerdo = esquerdo;
		this.direito = direito;
		this.valor = valor;
		this.fator = 0;
	}

	//Devolve o valor do nodo
	public T getValor() {
		return this.valor;
	}

	//Define um valor para o nodo
	public void setValor(T valor) {
		this.valor = valor;
	}

	//Devolve o filho � esquerda do nodo
	public Nodo<T> getEsquerdo() { 
		return this.esquerdo; 
	}

	//Define o filho � esquerda do nodo
	public void setEsquerdo(Nodo<T> nodo) {
		this.esquerdo = nodo;

		//Se o nodo do par�metro n�o � nulo, define este como o nodo pai dele
		if (nodo != null)
			nodo.setPai(this);
	}

	//Devolve o filho � direita do nodo
	public Nodo<T> getDireito() { 
		return this.direito; 
	}

	//Define o filho � direita do nodo
	public void setDireito(Nodo<T> nodo) {
		this.direito = nodo;

		//Se o nodo do par�metro n�o � nulo, define este como o nodo pai dele
		if (nodo != null)
			nodo.setPai(this);
	}
	
	//Devolve o fator de balanceamento do nodo
	public int getFator() { 
		return this.fator; 
	}
	
	//Devolve o pai do nodo
	public Nodo<T> getPai() { 
		return this.pai; 
	}	
	
	//Define um pai para o nodo
	public void setPai(Nodo<T> nodo) {
		this.pai = nodo;
	}
		
	//Calcula o seu fator de balanceamento e o do seus filhos
	public void calcularBalanceamento() {
		//Subtrai a altura dos filhos � esquerda pela altura dos filhos � direita para montar o fator de balanceamento
		this.fator = calcularAltura(this.esquerdo) - calcularAltura(this.direito);

		//Calcula o fator de balanceamento dos filhos
		if (this.esquerdo != null)
			this.esquerdo.calcularBalanceamento();

		if (this.direito != null)
			this.direito.calcularBalanceamento();
	}

	//Verifica a altura do nodo de forma recursiva, somando sempre 1 + altura dos filhos
	private int calcularAltura(Nodo<T> nodo) {
		//Se n�o existem mais filhos, retorna zero
		if (nodo == null)
			return 0;

		//Verifica a altura do maior filho
		return 1 + Math.max(calcularAltura(nodo.esquerdo), calcularAltura(nodo.direito));
	}
}