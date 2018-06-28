/*
 * Estruturas avançadas de dados - 2018/1 - Unisinos
 * Prof. Marcio Garcia Martins
 * Projeto: Tradutor inglês-Português utilizando uma árvore AVL
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
		//Instância uma árvore de dicionários
		this.arvore = new ArvoreAVL<Dicionario>();
	}
	
	//Devolve uma string no formato do arquivo .dat com o conteúdo completo da árvore, em ordem
	private String montarStringConteudoArvore(Nodo<Dicionario> nodo) {
		//Se o nodo não possui valor
		if (nodo == null)
			//Retorna em branco
			return "";
		else {
			//Cria um StringBuilder para concatenar os valores do nodo no formato do arquivo
			StringBuilder retorno = new StringBuilder(nodo.getValor().palavra);
			
			//Para cada definição, adiciona ao StringBuilder com o caractere de separação
			for (String traducao : nodo.getValor().definicoes)
				retorno.append("#").append(traducao);

			//Chama o método de forma recursiva para os filhos do nodo, fazendo uma quebra de linha a cada palavra da árvore
			return montarStringConteudoArvore(nodo.getEsquerdo()) + retorno.toString() + "\n" + montarStringConteudoArvore(nodo.getDireito());
		}
	}	
	
	//Salva o conteúdo do dicionário no arquivo passado por parâmetro
	public void salvaDicionario(String arq) throws IOException {
		Path localArquivo = Paths.get(arq);
		
		//Cria uma OutputStream para começar a gravar o arquivo
	    try (OutputStream arquivo = new BufferedOutputStream(Files.newOutputStream(localArquivo))) {
	    	//Obtém a String com o conteúdo que será gravado no arquivo
	    	byte[] dados = montarStringConteudoArvore(this.arvore.getRaiz()).getBytes(); 
	    	
	    	//Faz a gravação no disco
	    	arquivo.write(dados, 0, dados.length);
	    	arquivo.flush();
	    } 
	}
	
	//Insere uma nova tradução na árvore
	public void insereTraducao(String palavra, List<String> definicoes) {
		//Cria um objeto dicionário com a palavra e as suas definições
		Dicionario novaTraducao = new Dicionario(palavra, definicoes);
		//Verifica se a palavra já existe na árvore		
		Nodo<Dicionario> nodo = this.arvore.pesquisar(novaTraducao);
		
		//Se a palavra já existe
		if (nodo != null)
			//Adiciona as definições 
			nodo.getValor().definicoes.addAll(definicoes);
		//A palavra ainda não existe na árvore
		else
			//Insere a tradução na árvore
			this.arvore.inserir(novaTraducao);
	}
	
	//Pesquisa a palavra na árvore e devolve uma lista com as traduções
	public List<String> traduzPalavra(String palavra){
		//Pesquisa na árvore o nodo que possui a palavra pesquisada
		Nodo<Dicionario> nodo = this.arvore.pesquisar(new Dicionario(palavra, null));
		
		//Se encontrou a palavra na árvore
		if (nodo != null)
			//Retorna as definições da palavra passada por parâmetro
			return nodo.getValor().definicoes;
		//Não encontrou na árvore
		else 
			//Retorna nulo
			return null;
	}
	
	//Carrega o conteúdo de um arquivo .dat para a estrutura da árvore. Consulte o arquivo "Exemplo.dat" para verificar a estrutura
	public void carregaDicionario(String arq) throws IOException, Exception {
		//Verifica se o arquivo existe e é válido para importação
		File teste = new File(arq);
		if(!teste.exists() || teste.isDirectory()) 
		    throw new Exception("O arquivo indicado não existe ou é um diretório! \n"+
		    					"Arquivo: " + arq);
		
		//Abre o arquivo pra percorrer o conteúdo
		Path localArquivo = Paths.get(arq);
	    try (Scanner arquivo =  new Scanner(localArquivo, StandardCharsets.UTF_8.name())){    	
	        //Faz um loop pelas linhas do arquivo
	    	while (arquivo.hasNextLine()){
	    		//Instância uma lista para receber as traduções do arquivo
	        	List<String> traducoes = new ArrayList<String>();	
	        	
	        	//Obtém uma linha do arquivo
	            try(Scanner linha = new Scanner(arquivo.nextLine())){
		        	//Faz um loop nos campos da linha quebrando pelo delimitador do formato do arquivo. Consulte o arquivo "Exemplo.dat" para verificar a estrutura
	            	linha.useDelimiter("#");
	            
	            	while (linha.hasNext())
	                	//Adiciona todos os campos na lista de traduções
	                	traducoes.add(linha.next());
	            }
	            
	            //Se encontrou traduções na linha do arquivo
	            if (traducoes.size() > 0) {
	            	//Remove o primeiro registro da lista, que é a palavra em inglês
	            	String palavra = traducoes.remove(0);
	            	
	            	//Insere a palavra no dicionário
	            	insereTraducao(palavra, traducoes);	            	
	            }
	        }      
	    }
	}
}