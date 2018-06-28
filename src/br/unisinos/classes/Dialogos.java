/*
 * Estruturas avan�adas de dados - 2018/1 - Unisinos
 * Prof. Marcio Garcia Martins
 * Projeto: Tradutor ingl�s-Portugu�s utilizando uma �rvore AVL
 * Classe: Classe com m�todos est�ticos que trabalham com os di�logos do SWT
 */
package br.unisinos.classes;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class Dialogos {

	//Instancia uma classe FileDialog() conforme a opera��o enviada por exemplo (LOAD ou SAVE) j� com filtro definido
	public static FileDialog criarFileDialog(Shell pai, int operacao, String[] extencoes) {
		//Inst�ncia a classe
		FileDialog dialogo = new FileDialog(pai, operacao);
		//Define o filtro padr�o 
		dialogo.setFilterExtensions(extencoes);
		
		return dialogo;
	}
	
	//Cria uma inst�ncia da classe MessageBox com os par�metros recebidos
	public static MessageBox criarMessageBox(Shell pai, int opcoes, String titulo, String mensagem) {
		//Cria a classe do MessageBox
		MessageBox retorno = new MessageBox(pai, opcoes); 
		
		//Define um t�tulo padr�o e a mensagem do par�metro
		retorno.setText(titulo);
		retorno.setMessage(mensagem);
		
		return retorno;
	}
	
	//Mostra um di�logo com uma mensagem de aviso ao usu�rio e um bot�o "OK"
	public static void mostrarMensagem(Shell pai, String mensagem) {
		//Mostra o box na tela
		Dialogos.criarMessageBox(pai, SWT.ICON_INFORMATION | SWT.OK, "Aviso", mensagem).open();
	}
	
	//Mostra um di�logo com uma mensagem de erro ao usu�rio e um bot�o "OK"
	public static void mostrarMensagemErro(Shell pai, String mensagem) {
		//Mostra o box na tela
		Dialogos.criarMessageBox(pai, SWT.ICON_ERROR | SWT.OK, "Erro", mensagem).open();		
	}

	//Mostra um di�logo com uma pergunta para o usu�rio e os bot�es "Sim" e "N�o"
	public static int mostrarPerguntaSimNao(Shell pai, String pergunta) {
		//Mostra o box na tela
		return Dialogos.criarMessageBox(pai, SWT.ICON_QUESTION | SWT.YES | SWT.NO, "Confirma��o", pergunta).open();		
	}
}
