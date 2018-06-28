/*
 * Estruturas avan�adas de dados - 2018/1 - Unisinos
 * Prof. Marcio Garcia Martins
 * Projeto: Tradutor ingl�s-Portugu�s utilizando uma �rvore AVL
 * Classe: Formul�rio base da aplica��o
 */
package br.unisinos.forms;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.eclipse.swt.widgets.Shell;

public abstract class BaseForm {
	//Vari�vel que vai armazenar os formul�rios dos filhos
	protected Shell formulario;
	
	//M�todo padr�o para centralizar o formul�rio no monitor do computador
	protected void centralizarFormularioMonitor() {
		//Obt�m as dimens�es da tela
		Dimension tamanhoTela = Toolkit.getDefaultToolkit().getScreenSize();
		
		//Posiciona o formul�rio centralizado no monitor do computador
		this.formulario.setLocation((tamanhoTela.width - formulario.getSize().x) / 2, (tamanhoTela.height - formulario.getSize().y) / 2);
	}
}
