/*
 * Estruturas avançadas de dados - 2018/1 - Unisinos
 * Prof. Marcio Garcia Martins
 * Projeto: Tradutor inglês-Português utilizando uma árvore AVL
 * Classe: Formulário de inclusão de novas traduções
 */
package br.unisinos.forms;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class NovaPalavraForm extends BaseForm {
	private Text textNovaPalavra;
	private Text textTraducoes;
	private Label labelExplicacao;
	private Label labelNovaPalavra;
	private Button btnSalvar;
	private Button btnCancelar;
	private boolean confirmou = false;
	
	//Construtor da classe
	public NovaPalavraForm(String novaPalavra) {
		//Instância o formulário
		this.InstanciarFormulario();
		
		//Coloca a nova palavra no text de cima da tela
		this.textNovaPalavra.setText(novaPalavra);
	}
	
	//Instância e configura o formulário
	private void InstanciarFormulario() {
		//Instância o formulário principal e define seus atributos
		this.formulario = new Shell(SWT.BORDER | SWT.TITLE | SWT.APPLICATION_MODAL);
		this.formulario.setSize(580, 465);
		this.formulario.setText("Incluir nova palavra ao dicionário");	

		this.instanciarComponentesVisuais();
		this.definirEventosComponentesVisuais();
		
		//Posiciona o formulário centralizado no monitor do computador
		this.centralizarFormularioMonitor();
	}	

	//Cria e configura todos os componentes visuais, como labels, campos de texto e botões
	private void instanciarComponentesVisuais() {
		//Instância e configura o label com a descrição do text da nova palavra
		this.labelNovaPalavra = new Label(this.formulario, SWT.NONE);
		this.labelNovaPalavra.setBounds(10, 13, 554, 20);
		this.labelNovaPalavra.setText("Nova palavra a ser incluída:");
		
		//Instância e configura o text que vai mostrar a nova palavra
		this.textNovaPalavra = new Text(this.formulario, SWT.BORDER | SWT.READ_ONLY);
		this.textNovaPalavra.setBounds(10, 39, 554, 26);
		
		//Instância e configura o label com a explicação do formulário
		this.labelExplicacao = new Label(this.formulario, SWT.WRAP);
		this.labelExplicacao.setBounds(10, 71, 554, 65);
		this.labelExplicacao.setText("Informe na lista abaixo as traduções para a palavra que será incluída no dicionário. " +
									 "Ao término, clique no botão \"Salvar\" abaixo para incluir a palavra ou em \"Cancelar\" " +
				                     "para voltar à tela anterior.");
		
		//Instância e configura o text que receber as traduções digitadas
		this.textTraducoes = new Text(this.formulario, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		this.textTraducoes.setBounds(10, 143, 554, 244);
		
		//Instância e configura o botão de confirmação da inclusão
		this.btnSalvar = new Button(this.formulario, SWT.NONE);
		this.btnSalvar.setBounds(10, 393, 277, 30);
		this.btnSalvar.setText("Salvar");
		
		//Instância e configura o botão de cancelamento da inclusão
		this.btnCancelar = new Button(this.formulario, SWT.NONE);
		this.btnCancelar.setText("Cancelar");
		this.btnCancelar.setBounds(287, 393, 277, 30);		
		
		//Define o TAB Order dos campos do formulário
		this.formulario.setTabList(new Control[]{this.textTraducoes});
	}
	
	//Define os eventos dos componentes visuais, como os clicks dos botões
	private void definirEventosComponentesVisuais() {
		//Cria um evento pra capturar o Click() do botão de salvar
		this.btnSalvar.addListener(SWT.Selection, new Listener()
		{
		    @Override
		    public void handleEvent(Event event)
		    {
		    	confirmou = true;
		    	formulario.setVisible(false);
		    }
		});
		
		//Cria um evento pra capturar o Click() do botão de cancelar
		this.btnCancelar.addListener(SWT.Selection, new Listener()
		{
		    @Override
		    public void handleEvent(Event event)
		    {
		    	confirmou = false;
		    	formulario.setVisible(false);
		    }
		});	
	}

	//Método para mostrar o formulário na tela
	public void showModal() {
		Display display = Display.getDefault();
		
		//Abre o formulário na tela
		formulario.open();
		formulario.layout();
		while (formulario.isVisible()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}		
	}	

	//Método estático para iniciar a inclusão de uma nova palavra
	public static List<String> incluirDefinicoesNovaPalavra(String novaPalavra) {
		//Declara a variável de retorno e instância o formulário
		List<String> retorno = null;
		NovaPalavraForm form = new NovaPalavraForm(novaPalavra);
		
		//Mosta de forma modal na tela
		form.showModal();
		
		//Se o usuário confirmou a inclusão e informou traduções, monta a lista de traduções
		if ((form.confirmou) && (!form.textTraducoes.getText().trim().isEmpty())) {
			//Instância o retorno
			retorno = new ArrayList<String>();
			
			//Percore o text de traduções quebrando as linhas do texto
			for (String traducao : form.textTraducoes.getText().split("\\n"))
				if (!traducao.trim().isEmpty())
					//Remove os caracteres de quebra de linha para adicionar a tradução à lista
					retorno.add(traducao.replaceAll("[\\r\\n]+", ""));
		}
		//Fecha o formulário
		form.formulario.close();

		return retorno;
	}
}