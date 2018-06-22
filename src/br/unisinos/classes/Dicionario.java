package br.unisinos.classes;

import java.util.ArrayList;
import java.util.List;

public class Dicionario implements Comparable<Dicionario> {
	
	protected String palavra;
	protected List<String> definicoes;
	
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

	public int compareTo(Dicionario o) {		
		//Implementa a interface Comparable fazendo a comparação das palavras de forma case-insensitive
		return this.palavra.compareToIgnoreCase(((Dicionario)o).palavra);
	}
	
	public String toString() {
		//Devolve como valor padrão ao "ToStrin()" da classe a palavra definidas
		return this.palavra;
	}
}
