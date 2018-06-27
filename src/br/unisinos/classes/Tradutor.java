/*
 * Estruturas avançadas de dados - 2018/1 - Unisinos
 * Prof. Marcio Garcia Martins
 * Projeto: Tradutor inglês-Português utilizando uma árvore AVL
 * Classe: Classe principial do aplicativo tradutor
 */
package br.unisinos.classes;

import java.util.List;

public class Tradutor {
	
	private ArvoreAVL<Dicionario> arvore;
	
	public Tradutor() {
		//Instância uma árvore de dicionários
		this.arvore = new ArvoreAVL<Dicionario>();
	}
	
	public void salvaDicionario(String arq) {
		// TODO Implementar o salvamento do dicionário em arquivo
	}
	
	public void insereTraducao(String palavra, List<String> definicoes) {
		// TODO se a palavra já existir na árvore, adicionar a definição à lista.
		
		//Insere a tradução na árvore
		this.arvore.inserir(new Dicionario(palavra, definicoes));		
	}
	
	private boolean confirmarInclusaoTraducoes(){
		String opcao = "";
		do {
			opcao = Console.lerString("Digite \"S\" para informar traduções ou \"N\" para encerrar a consulta:");			
			switch(opcao) {
				case "S":
				case "N":
					//Usuário selecionou uma opção válida
					break;
				default:
					//Usuário informou um valor inválido
					Console.ImprimirMensagemOpcaoErrada();
			}
		}while((opcao != "S") && (opcao != "N"));
		
		return (opcao == "S");
	}
	
	private List<String> solicitarListaTraducoes(){
		//TODO continuar
		return null;
	}
	
	public List<String> traduzPalavra(String palavra){
		//Pesquisa na árvore o nodo que possui a palavra pesquisada
		Nodo<Dicionario> nodo = this.arvore.pesquisar(new Dicionario(palavra, null));
		
		//Se encontrou a palavra na árvore
		if (nodo != null)
			//Retorna as definições da palavra passada por parâmetro
			return nodo.getValor().definicoes;
		else
			//Retorna nulo
			return null;
	}
	
	protected void carregaDicionario(String arq) {
		// TODO Implementar a leitura do dicionário em arquivo
	}
	
	private void pesquisarTraducao(String palavra){
		List<String> definicoes = this.traduzPalavra(palavra);
		
		if (definicoes == null) {
			System.out.format("Não foram encontradas traduções para a palavra \"%s\". Deseja informar traduções para ela?", palavra);
			
			if (confirmarInclusaoTraducoes()) {
				definicoes = solicitarListaTraducoes();				
				insereTraducao(palavra, definicoes);
			}
		}
	}	
	
	public static void main(String args[]) {
		Console.imprimirCabecalho();
		Console.imprimirMenu();
		
		int opcao = 0;
		do {
			opcao = Console.lerInteiro("Informe a opção desejada: ");			
			switch(opcao) {
				case 1:
					//TODO Tratar importação do arquivo
					break;
				case 2:
					//TODO Fazer salvamento do arquivo
					break;
				case 3:
					pesquisarTraducao(Console.lerString("Informe a palavra que deseja verificar a tradução: "));					
					break;
				case 4:
					//Usuário quer fechar a aplicação
					break;
				default:
					//Usuário informou um valor inválido
					Console.ImprimirMensagemOpcaoErrada();
			}
		}while(opcao != 4);
	}
}
