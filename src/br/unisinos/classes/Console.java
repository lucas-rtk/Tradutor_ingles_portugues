/*
 * Estruturas avan�adas de dados - 2018/1 - Unisinos
 * Prof. Marcio Garcia Martins
 * Projeto: Tradutor ingl�s-Portugu�s utilizando uma �rvore AVL
 * Classe: Classe com m�todos est�ticos para ler e imprimir informa��es no console
 */
package br.unisinos.classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {

	private static String stringLeitura;
	private static InputStreamReader inputStream = new InputStreamReader(System.in);
	private static BufferedReader reader = new BufferedReader(inputStream);

	// Rotina para ler um valor inteiro
	public static int lerInteiro() {
		int retorno = 0;
		try {
			retorno = Integer.parseInt(reader.readLine());
		} catch (IOException e) {
			System.out.println("Erro de I/O: " + e);
		} catch (NumberFormatException e) {
			retorno = 0;
		}
		return retorno;
	}

	// Rotina para ler um valor inteiro mostrando uma mensagem antes
	public static int lerInteiro(String msg) {
		System.out.print(msg);
		return lerInteiro();
	}

	// Rotina para ler um valor double
	public static double lerDouble() {
		double retorno = 0;
		try {
			stringLeitura = reader.readLine();
			retorno = Double.parseDouble(stringLeitura);
		} catch (IOException e) {
			System.out.println("Erro de I/O: " + e);
		} catch (NumberFormatException e) {
			retorno = 0;
		}
		return (retorno);
	}

	// Rotina para ler um valor double mostrando uma mensagem antes
	public static double leDouble(String msg) {
		System.out.print(msg);
		return lerDouble();
	}

	// Rotina para ler um valor String
	public static String lerString() {
		stringLeitura = "";

		try {
			stringLeitura = reader.readLine();
		} catch (IOException e) {
			System.out.println("Erro de I/O: " + e);
		}
		return (stringLeitura);
	}

	// Rotina para ler um valor String mostrando uma mensagem antes
	public static String lerString(String msg) {
		System.out.print(msg);
		return lerString();
	}

	// Rotina para ler um valor char
	public static Character lerChar() {
		stringLeitura = "";
		try {
			stringLeitura = reader.readLine();
		} catch (IOException e) {
			System.out.println("Erro de I/O: " + e);
		}
		return (stringLeitura.charAt(0));
	}

	// Rotina para ler um valor char mostrando uma mensagem antes
	public static Character lerChar(String msg) {
		System.out.print(msg);
		return lerChar();
	}
	
	public static void limparConsole(){
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException | IOException ex) {
			ex.printStackTrace();
		}
	}	

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

	public static void ImprimirMensagemOpcaoErrada() {
		System.out.println("Op��o inv�lida!");
		System.out.println("");
	}
	
	public static void imprimirMensagemEnterParaContinuar() {
		Console.lerString("Pressione <ENTER> para continuar;");
	}
}
