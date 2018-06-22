package br.unisinos.classes;

import java.util.List;

public class Tradutor {
	
	private ArvoreAVL<Dicionario> arvore;
	
	public Tradutor() {
		//Inst�ncia uma �rvore de dicion�rios
		this.arvore = new ArvoreAVL<Dicionario>();
	}
	
	public void salvaDicionario(String arq) {
		// TODO Implementar o salvamento do dicion�rio em arquivo
	}
	
	public void insereTraducao(String palavra, List<String> definicoes) {
		// TODO se a palavra j� existir na �rvore, adicionar a defini��o � lista.
		
		//Insere a tradu��o na �rvore
		this.arvore.inserir(new Dicionario(palavra, definicoes));		
	}
	
	public List<String> traduzPalavra(String palavra){
		//Pesquisa na �rvore o nodo que possui a palavra pesquisada
		Nodo<Dicionario> nodo = this.arvore.pesquisar(new Dicionario(palavra, null));
		
		//Se encontrou a palavra na �rvore
		if (nodo != null)
			//Retorna as defini��es da palavra passada por par�metro
			return nodo.getValor().definicoes;
		else
			//Retorna nulo
			return null;
	}
	
	protected void carregaDicionario(String arq) {
		// TODO Implementar a leitura do dicion�rio em arquivo
	}
}
