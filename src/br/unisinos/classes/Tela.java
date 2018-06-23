/*
 * Estruturas avançadas de dados - 2018/1 - Unisinos
 * Prof. Marcio Garcia Martins
 * Projeto: Tradutor inglês-Português utilizando uma árvore AVL
 * Classe: Classe com métodos estáticos para gerar os dados em tela
 */
package br.unisinos.classes;

public class Tela {
	
	public static void imprimirCabecalho() {
		System.out.println("----------------------------------------------------");
		System.out.println("  Tradutor inglês-portugues");
		System.out.println("  Estruturas avançadas de dados - 2018/1 - Unisinos");
		System.out.println("  Prof. Marcio Garcia Martins");
		System.out.println("  Aluno - Lucas Rutkoski");
		System.out.println("----------------------------------------------------");
	}
	
	public static void imprimirMenu() {
		System.out.println("");
		System.out.println("               Opções disponíveis");
		System.out.println("1. Importar arquivo com dados para o dicionário;");
		System.out.println("2. Salvar arquivo com dados do dicionário");
		System.out.println("3. Pesquisar tradução de uma palavra");
		System.out.println("4. Sair");
		System.out.println("");
	}
	
	public static void ImprimirPergunta() {
		System.out.print("Informe a opção desejada: ");
	}
	
	public static void ImprimirMensagemOpcaoErrada() {
		System.out.println("Opção inválida!");
		System.out.println("Verifique o menu e indique uma opção válida.");
		System.out.println("");
	}
}
