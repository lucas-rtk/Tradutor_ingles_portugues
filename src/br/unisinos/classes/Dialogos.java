/*
 * Estruturas avançadas de dados - 2018/1 - Unisinos
 * Prof. Marcio Garcia Martins
 * Projeto: Tradutor inglês-Português utilizando uma árvore AVL
 * Classe: Classe com métodos estáticos que trabalham com os diálogos do SWT
 */
package br.unisinos.classes;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class Dialogos {

	//Instancia uma classe FileDialog() conforme a operação enviada por exemplo (LOAD ou SAVE) já com filtro definido
	public static FileDialog criarFileDialog(Shell pai, int operacao, String[] extencoes) {
		//Instância a classe
		FileDialog dialogo = new FileDialog(pai, operacao);
		//Define o filtro padrão 
		dialogo.setFilterExtensions(extencoes);
		
		return dialogo;
	}
	
	//Cria uma instância da classe MessageBox com os parâmetros recebidos
	public static MessageBox criarMessageBox(Shell pai, int opcoes, String titulo, String mensagem) {
		//Cria a classe do MessageBox
		MessageBox retorno = new MessageBox(pai, opcoes); 
		
		//Define um título padrão e a mensagem do parâmetro
		retorno.setText(titulo);
		retorno.setMessage(mensagem);
		
		return retorno;
	}
	
	//Mostra um diálogo com uma mensagem de aviso ao usuário e um botão "OK"
	public static void mostrarMensagem(Shell pai, String mensagem) {
		//Mostra o box na tela
		Dialogos.criarMessageBox(pai, SWT.ICON_INFORMATION | SWT.OK, "Aviso", mensagem).open();
	}
	
	//Mostra um diálogo com uma mensagem de erro ao usuário e um botão "OK"
	public static void mostrarMensagemErro(Shell pai, String mensagem) {
		//Mostra o box na tela
		Dialogos.criarMessageBox(pai, SWT.ICON_ERROR | SWT.OK, "Erro", mensagem).open();		
	}

	//Mostra um diálogo com uma pergunta para o usuário e os botões "Sim" e "Não"
	public static int mostrarPerguntaSimNao(Shell pai, String pergunta) {
		//Mostra o box na tela
		return Dialogos.criarMessageBox(pai, SWT.ICON_QUESTION | SWT.YES | SWT.NO, "Confirmação", pergunta).open();		
	}
}
