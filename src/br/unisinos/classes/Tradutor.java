/*
 * Estruturas avançadas de dados - 2018/1 - Unisinos
 * Prof. Marcio Garcia Martins
 * Projeto: Tradutor inglês-Português utilizando uma árvore AVL
 * Classe: Classe principial do aplicativo tradutor
 */
package br.unisinos.classes;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tradutor {
	
	private ArvoreAVL<Dicionario> arvore;
	
	public Tradutor() {
		//Instância uma árvore de dicionários
		this.arvore = new ArvoreAVL<Dicionario>();
	}
	
	private String descarregarArvore(Nodo<Dicionario> nodo) {
		//Se o nodo não possui valor
		if (nodo == null)
			//Retorna em branco
			return "";
		else {
			StringBuilder retorno = new StringBuilder(nodo.getValor().palavra);
			
			for (String traducao : nodo.getValor().definicoes) {
				retorno.append("#").append(traducao);
			}
			return descarregarArvore(nodo.getEsquerdo()) + retorno.toString() + "\n" + descarregarArvore(nodo.getDireito());
		}
	}	
	
	public void salvaDicionario(String arq) {
		Path localArquivo = Paths.get(arq);
		
	    try (OutputStream arquivo = new BufferedOutputStream(Files.newOutputStream(localArquivo))) {
	    	byte[] dados = descarregarArvore(this.arvore.getRaiz()).getBytes(); 
	    	arquivo.write(dados, 0, dados.length);
	    } catch (IOException x) {
	    	System.err.println(x);
	    }
	}
	
	public void insereTraducao(String palavra, List<String> definicoes) {
		Dicionario novaTraducao = new Dicionario(palavra, definicoes);
		Nodo<Dicionario> nodo = this.arvore.pesquisar(novaTraducao);
		
		if (nodo != null)
			nodo.getValor().definicoes.addAll(definicoes);
		else
			//Insere a tradução na árvore
			this.arvore.inserir(novaTraducao);
	}
	
	private boolean confirmarInclusaoTraducoes(){
		String opcao = "";
		do {
			System.out.println("");
			opcao = Console.lerString("Digite \"S\" para informar traduções ou \"N\" para encerrar a consulta:").toUpperCase();			
			switch(opcao.toUpperCase()) {
				case "S":
				case "N":
					//Usuário selecionou uma opção válida
					break;
				default:
					//Usuário informou um valor inválido
					Console.ImprimirMensagemOpcaoErrada();
			}
		}while((!opcao.equals("S")) && (!opcao.equals("N")));
		
		return (opcao.equals("S"));
	}
	
	private List<String> solicitarListaTraducoes(String palavra){
		System.out.format("Informe as traduções para a palavra \"%s\".\n", palavra); 
		System.out.println("A cada tradução informada, pressione <ENTER> para gravar. Para encerrar a inclusão \n " +
						   "de traduções, deixe a linha em branco e pressione <ENTER>.");
		System.out.println("");
		
		String traducao = "";
		List<String> retorno = new ArrayList<String>();
		do {
			traducao = Console.lerString();
			
			if (!traducao.trim().isEmpty())
				retorno.add(traducao);
		} while(!traducao.trim().isEmpty());
		
		return retorno;
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
	
	protected void carregaDicionario(String arq) throws IOException {
		Path localArquivo = Paths.get(arq);
		
	    try (Scanner arquivo =  new Scanner(localArquivo, StandardCharsets.UTF_8.name())){    	
	        while (arquivo.hasNextLine()){
	        	List<String> traducoes = new ArrayList<String>();	
	        	
	            try(Scanner linha = new Scanner(arquivo.nextLine())){
	                linha.useDelimiter("#");
	                while (linha.hasNext()){
	                  traducoes.add(linha.next());
	                }
	              }
	            
	            if (traducoes.size() > 0) {
	            	String palavra = traducoes.remove(0);
	            	insereTraducao(palavra, traducoes);	            	
	            }
	        }      
	      }
	}
	
	private void pesquisarTraducao(String palavra){
		List<String> definicoes = this.traduzPalavra(palavra);
		
		if (definicoes != null) {
			System.out.format("Lista de traduções encontradas para a palavra \"%s\": %s \n", palavra, definicoes.toString());
			Console.imprimirMensagemEnterParaContinuar();
		}		
		else {
			System.out.format("Não foram encontradas traduções para a palavra \"%s\". Deseja informar traduções para ela?", palavra);
			
			if (confirmarInclusaoTraducoes()) {
				definicoes = solicitarListaTraducoes(palavra);
				
				if (definicoes.size() == 0)
					System.out.println("Você não informou nenhuma tradução para a palavra. Não será incluída no dicionário.");
				else
					insereTraducao(palavra, definicoes);
			}
		}
	}	
	
	private String selecionarArquivo() {
		FileDialog loadDialog = new FileDialog(new Frame(), "Selecione o arquivo com os dados que deseja importar", FileDialog.LOAD);
        loadDialog.setFile("*.dat");
        loadDialog.setModal(true);
        loadDialog.toFront();
        loadDialog.setVisible(true);
        
        if (loadDialog.getFile() == null)
        	return "";
        else
        	return loadDialog.getDirectory() + loadDialog.getFile();
	}
	
	private void importarArquivo(){
		String localArquivo = this.selecionarArquivo();
		if (localArquivo.isEmpty()) {
			System.out.println("Carregamento cancelado, usuário não selecionou um arquivo!");
			Console.imprimirMensagemEnterParaContinuar();
		} else
			try {
				this.carregaDicionario(localArquivo);
				System.out.println("Arquivo carregado com sucesso!");
				Console.imprimirMensagemEnterParaContinuar();							
			} catch (IOException e) {
				System.out.println("Houve um erro ao carregar o arquivo selecionado!");
				System.out.println(e.getMessage());
				Console.imprimirMensagemEnterParaContinuar();
			}		
	}
	
	public static void main(String args[]) {	
		Tradutor tradutor = new Tradutor();
		
		int opcao = 0;
		do {
			Console.limparConsole();
			Console.imprimirCabecalho();
			Console.imprimirMenu();
			
			opcao = Console.lerInteiro("Informe a opção desejada: ");			
			switch(opcao) {
				case 1:
					//Usuário quer importar um arquivo com traduções
					tradutor.importarArquivo();					
					break;
				case 2:
					tradutor.salvaDicionario("C:\\temp\\arquivo.dat");
					break;
				case 3:
					//Usuário quer pesquisar a tradução de uma palavra
					tradutor.pesquisarTraducao(Console.lerString("Informe a palavra que deseja verificar a tradução: "));					
					break;
				case 4:
					//Usuário quer fechar a aplicação
					break;
				default:
					//Usuário informou um valor inválido
					Console.ImprimirMensagemOpcaoErrada();
					Console.imprimirMensagemEnterParaContinuar();
			}
		}while(opcao != 4);
	}
}
