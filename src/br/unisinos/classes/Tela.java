/*
 * Estruturas avan�adas de dados - 2018/1 - Unisinos
 * Prof. Marcio Garcia Martins
 * Projeto: Tradutor ingl�s-Portugu�s utilizando uma �rvore AVL
 * Classe: Classe com m�todos est�ticos para gerar os dados em tela
 */
package br.unisinos.classes;

public class Tela {
	
	public static void imprimirCabecalho() {
		System.out.println("----------------------------------------------------");
		System.out.println("  Tradutor ingl�s-portugues");
		System.out.println("  Estruturas avan�adas de dados - 2018/1 - Unisinos");
		System.out.println("  Prof. Marcio Garcia Martins");
		System.out.println("  Aluno - Lucas Rutkoski");
		System.out.println("----------------------------------------------------");
	}
	
	public static void imprimirMenu() {
		System.out.println("");
		System.out.println("               Op��es dispon�veis");
		System.out.println("1. Importar arquivo com dados para o dicion�rio;");
		System.out.println("2. Salvar arquivo com dados do dicion�rio");
		System.out.println("3. Pesquisar tradu��o de uma palavra");
		System.out.println("4. Sair");
		System.out.println("");
	}
	
	public static void ImprimirPergunta() {
		System.out.print("Informe a op��o desejada: ");
	}
	
	public static void ImprimirMensagemOpcaoErrada() {
		System.out.println("Op��o inv�lida!");
		System.out.println("Verifique o menu e indique uma op��o v�lida.");
		System.out.println("");
	}
}
