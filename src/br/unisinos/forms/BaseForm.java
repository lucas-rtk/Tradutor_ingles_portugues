/*
 * Estruturas avançadas de dados - 2018/1 - Unisinos
 * Prof. Marcio Garcia Martins
 * Projeto: Tradutor inglês-Português utilizando uma árvore AVL
 * Classe: Formulário base da aplicação
 */
package br.unisinos.forms;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.eclipse.swt.widgets.Shell;

public abstract class BaseForm {
	//Variável que vai armazenar os formulários dos filhos
	protected Shell formulario;
	
	//Método padrão para centralizar o formulário no monitor do computador
	protected void centralizarFormularioMonitor() {
		//Obtém as dimensões da tela
		Dimension tamanhoTela = Toolkit.getDefaultToolkit().getScreenSize();
		
		//Posiciona o formulário centralizado no monitor do computador
		this.formulario.setLocation((tamanhoTela.width - formulario.getSize().x) / 2, (tamanhoTela.height - formulario.getSize().y) / 2);
	}
}
