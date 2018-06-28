/*
 * Estruturas avan�adas de dados - 2018/1 - Unisinos
 * Prof. Marcio Garcia Martins
 * Projeto: Tradutor ingl�s-Portugu�s utilizando uma �rvore AVL
 * Classe: Dicion�rio que vai armazenar a palavra em ingl�s e as suas tradu��es em portugu�s
 */
package br.unisinos.classes;

import java.util.ArrayList;
import java.util.List;

public class Dicionario implements Comparable<Dicionario> {
	
	protected String palavra;
	protected List<String> definicoes;
	
	//Construtor da classe
	public Dicionario(String palavra, List<String> definicoes) {
		this.palavra = palavra;
		
		//Se a palavra recebeu uma lista de defini��es
		if (definicoes != null)
			//Atribui a lista � vari�vel
			this.definicoes = definicoes;
		else
			//Cria uma lista de defini��es vazia
			this.definicoes = new ArrayList<String>();
	}
	
	//Implementa��o da interface Comparable<>, verificando qual palavra dos dois dicion�rio � a maior (sem considerar letras mai�sculas e min�sculas)
	public int compareTo(Dicionario o) {		
		return this.palavra.compareToIgnoreCase(((Dicionario)o).palavra);
	}
	
	//Implementa��o do m�todo ToString() para a classe
	public String toString() {
		//Devolve como valor padr�o ao "ToString()" da classe a palavra definida
		return this.palavra;
	}
}