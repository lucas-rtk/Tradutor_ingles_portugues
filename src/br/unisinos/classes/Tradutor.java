/*
 * Estruturas avan�adas de dados - 2018/1 - Unisinos
 * Prof. Marcio Garcia Martins
 * Projeto: Tradutor ingl�s-Portugu�s utilizando uma �rvore AVL
 * Classe: Classe principial do aplicativo tradutor
 */
package br.unisinos.classes;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tradutor {
	
	private ArvoreAVL<Dicionario> arvore;
	
	//Construtor da classe
	public Tradutor() {
		//Inst�ncia uma �rvore de dicion�rios
		this.arvore = new ArvoreAVL<Dicionario>();
	}
	
	//Devolve uma string no formato do arquivo .dat com o conte�do completo da �rvore, em ordem
	private String montarStringConteudoArvore(Nodo<Dicionario> nodo) {
		//Se o nodo n�o possui valor
		if (nodo == null)
			//Retorna em branco
			return "";
		else {
			//Cria um StringBuilder para concatenar os valores do nodo no formato do arquivo
			StringBuilder retorno = new StringBuilder(nodo.getValor().palavra);
			
			//Para cada defini��o, adiciona ao StringBuilder com o caractere de separa��o
			for (String traducao : nodo.getValor().definicoes)
				retorno.append("#").append(traducao);

			//Chama o m�todo de forma recursiva para os filhos do nodo, fazendo uma quebra de linha a cada palavra da �rvore
			return montarStringConteudoArvore(nodo.getEsquerdo()) + retorno.toString() + "\n" + montarStringConteudoArvore(nodo.getDireito());
		}
	}	
	
	//Salva o conte�do do dicion�rio no arquivo passado por par�metro
	public void salvaDicionario(String arq) throws IOException {
		Path localArquivo = Paths.get(arq);
		
		//Cria uma OutputStream para come�ar a gravar o arquivo
	    try (OutputStream arquivo = new BufferedOutputStream(Files.newOutputStream(localArquivo))) {
	    	//Obt�m a String com o conte�do que ser� gravado no arquivo
	    	byte[] dados = montarStringConteudoArvore(this.arvore.getRaiz()).getBytes(); 
	    	
	    	//Faz a grava��o no disco
	    	arquivo.write(dados, 0, dados.length);
	    	arquivo.flush();
	    } 
	}
	
	//Insere uma nova tradu��o na �rvore
	public void insereTraducao(String palavra, List<String> definicoes) {
		//Cria um objeto dicion�rio com a palavra e as suas defini��es
		Dicionario novaTraducao = new Dicionario(palavra, definicoes);
		//Verifica se a palavra j� existe na �rvore		
		Nodo<Dicionario> nodo = this.arvore.pesquisar(novaTraducao);
		
		//Se a palavra j� existe
		if (nodo != null)
			//Adiciona as defini��es 
			nodo.getValor().definicoes.addAll(definicoes);
		//A palavra ainda n�o existe na �rvore
		else
			//Insere a tradu��o na �rvore
			this.arvore.inserir(novaTraducao);
	}
	
	//Pesquisa a palavra na �rvore e devolve uma lista com as tradu��es
	public List<String> traduzPalavra(String palavra){
		//Pesquisa na �rvore o nodo que possui a palavra pesquisada
		Nodo<Dicionario> nodo = this.arvore.pesquisar(new Dicionario(palavra, null));
		
		//Se encontrou a palavra na �rvore
		if (nodo != null)
			//Retorna as defini��es da palavra passada por par�metro
			return nodo.getValor().definicoes;
		//N�o encontrou na �rvore
		else 
			//Retorna nulo
			return null;
	}
	
	//Carrega o conte�do de um arquivo .dat para a estrutura da �rvore. Consulte o arquivo "Exemplo.dat" para verificar a estrutura
	public void carregaDicionario(String arq) throws IOException, Exception {
		//Verifica se o arquivo existe e � v�lido para importa��o
		File teste = new File(arq);
		if(!teste.exists() || teste.isDirectory()) 
		    throw new Exception("O arquivo indicado n�o existe ou � um diret�rio! \n"+
		    					"Arquivo: " + arq);
		
		//Abre o arquivo pra percorrer o conte�do
		Path localArquivo = Paths.get(arq);
	    try (Scanner arquivo =  new Scanner(localArquivo, StandardCharsets.UTF_8.name())){    	
	        //Faz um loop pelas linhas do arquivo
	    	while (arquivo.hasNextLine()){
	    		//Inst�ncia uma lista para receber as tradu��es do arquivo
	        	List<String> traducoes = new ArrayList<String>();	
	        	
	        	//Obt�m uma linha do arquivo
	            try(Scanner linha = new Scanner(arquivo.nextLine())){
		        	//Faz um loop nos campos da linha quebrando pelo delimitador do formato do arquivo. Consulte o arquivo "Exemplo.dat" para verificar a estrutura
	            	linha.useDelimiter("#");
	            
	            	while (linha.hasNext())
	                	//Adiciona todos os campos na lista de tradu��es
	                	traducoes.add(linha.next());
	            }
	            
	            //Se encontrou tradu��es na linha do arquivo
	            if (traducoes.size() > 0) {
	            	//Remove o primeiro registro da lista, que � a palavra em ingl�s
	            	String palavra = traducoes.remove(0);
	            	
	            	//Insere a palavra no dicion�rio
	            	insereTraducao(palavra, traducoes);	            	
	            }
	        }      
	    }
	}
}