/*
 * Estruturas avan�adas de dados - 2018/1 - Unisinos
 * Prof. Marcio Garcia Martins
 * Projeto: Tradutor ingl�s-Portugu�s utilizando uma �rvore AVL
 * Classe: Classe principial do aplicativo tradutor
 */
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
	
	private boolean confirmarInclusaoTraducoes(){
		String opcao = "";
		do {
			opcao = Console.lerString("Digite \"S\" para informar tradu��es ou \"N\" para encerrar a consulta:");			
			switch(opcao) {
				case "S":
				case "N":
					//Usu�rio selecionou uma op��o v�lida
					break;
				default:
					//Usu�rio informou um valor inv�lido
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
	
	private void pesquisarTraducao(String palavra){
		List<String> definicoes = this.traduzPalavra(palavra);
		
		if (definicoes == null) {
			System.out.format("N�o foram encontradas tradu��es para a palavra \"%s\". Deseja informar tradu��es para ela?", palavra);
			
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
			opcao = Console.lerInteiro("Informe a op��o desejada: ");			
			switch(opcao) {
				case 1:
					//TODO Tratar importa��o do arquivo
					break;
				case 2:
					//TODO Fazer salvamento do arquivo
					break;
				case 3:
					pesquisarTraducao(Console.lerString("Informe a palavra que deseja verificar a tradu��o: "));					
					break;
				case 4:
					//Usu�rio quer fechar a aplica��o
					break;
				default:
					//Usu�rio informou um valor inv�lido
					Console.ImprimirMensagemOpcaoErrada();
			}
		}while(opcao != 4);
	}
}
