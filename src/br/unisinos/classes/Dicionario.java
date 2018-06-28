/*
 * Estruturas avançadas de dados - 2018/1 - Unisinos
 * Prof. Marcio Garcia Martins
 * Projeto: Tradutor inglês-Português utilizando uma árvore AVL
 * Classe: Dicionário que vai armazenar a palavra em inglês e as suas traduções em português
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
		
		//Se a palavra recebeu uma lista de definições
		if (definicoes != null)
			//Atribui a lista à variável
			this.definicoes = definicoes;
		else
			//Cria uma lista de definições vazia
			this.definicoes = new ArrayList<String>();
	}
	
	//Implementação da interface Comparable<>, verificando qual palavra dos dois dicionário é a maior (sem considerar letras maiúsculas e minúsculas)
	public int compareTo(Dicionario o) {		
		return this.palavra.compareToIgnoreCase(((Dicionario)o).palavra);
	}
	
	//Implementação do método ToString() para a classe
	public String toString() {
		//Devolve como valor padrão ao "ToString()" da classe a palavra definida
		return this.palavra;
	}
}