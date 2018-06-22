package br.unisinos.classes;

import java.util.ArrayList;
import java.util.List;

public class Dicionario implements Comparable<Dicionario> {
	
	protected String palavra;
	protected List<String> definicoes;
	
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

	public int compareTo(Dicionario o) {		
		//Implementa a interface Comparable fazendo a compara��o das palavras de forma case-insensitive
		return this.palavra.compareToIgnoreCase(((Dicionario)o).palavra);
	}
	
	public String toString() {
		//Devolve como valor padr�o ao "ToStrin()" da classe a palavra definidas
		return this.palavra;
	}
}
