/*
 * Estruturas avan�adas de dados - 2018/1 - Unisinos
 * Prof. Marcio Garcia Martins
 * Projeto: Tradutor ingl�s-Portugu�s utilizando uma �rvore AVL
 * Classe: Formul�rio de inclus�o de novas tradu��es
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
		//Inst�ncia o formul�rio
		this.InstanciarFormulario();
		
		//Coloca a nova palavra no text de cima da tela
		this.textNovaPalavra.setText(novaPalavra);
	}
	
	//Inst�ncia e configura o formul�rio
	private void InstanciarFormulario() {
		//Inst�ncia o formul�rio principal e define seus atributos
		this.formulario = new Shell(SWT.BORDER | SWT.TITLE | SWT.APPLICATION_MODAL);
		this.formulario.setSize(580, 465);
		this.formulario.setText("Incluir nova palavra ao dicion�rio");	

		this.instanciarComponentesVisuais();
		this.definirEventosComponentesVisuais();
		
		//Posiciona o formul�rio centralizado no monitor do computador
		this.centralizarFormularioMonitor();
	}	

	//Cria e configura todos os componentes visuais, como labels, campos de texto e bot�es
	private void instanciarComponentesVisuais() {
		//Inst�ncia e configura o label com a descri��o do text da nova palavra
		this.labelNovaPalavra = new Label(this.formulario, SWT.NONE);
		this.labelNovaPalavra.setBounds(10, 13, 554, 20);
		this.labelNovaPalavra.setText("Nova palavra a ser inclu�da:");
		
		//Inst�ncia e configura o text que vai mostrar a nova palavra
		this.textNovaPalavra = new Text(this.formulario, SWT.BORDER | SWT.READ_ONLY);
		this.textNovaPalavra.setBounds(10, 39, 554, 26);
		
		//Inst�ncia e configura o label com a explica��o do formul�rio
		this.labelExplicacao = new Label(this.formulario, SWT.WRAP);
		this.labelExplicacao.setBounds(10, 71, 554, 65);
		this.labelExplicacao.setText("Informe na lista abaixo as tradu��es para a palavra que ser� inclu�da no dicion�rio. " +
									 "Ao t�rmino, clique no bot�o \"Salvar\" abaixo para incluir a palavra ou em \"Cancelar\" " +
				                     "para voltar � tela anterior.");
		
		//Inst�ncia e configura o text que receber as tradu��es digitadas
		this.textTraducoes = new Text(this.formulario, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		this.textTraducoes.setBounds(10, 143, 554, 244);
		
		//Inst�ncia e configura o bot�o de confirma��o da inclus�o
		this.btnSalvar = new Button(this.formulario, SWT.NONE);
		this.btnSalvar.setBounds(10, 393, 277, 30);
		this.btnSalvar.setText("Salvar");
		
		//Inst�ncia e configura o bot�o de cancelamento da inclus�o
		this.btnCancelar = new Button(this.formulario, SWT.NONE);
		this.btnCancelar.setText("Cancelar");
		this.btnCancelar.setBounds(287, 393, 277, 30);		
		
		//Define o TAB Order dos campos do formul�rio
		this.formulario.setTabList(new Control[]{this.textTraducoes});
	}
	
	//Define os eventos dos componentes visuais, como os clicks dos bot�es
	private void definirEventosComponentesVisuais() {
		//Cria um evento pra capturar o Click() do bot�o de salvar
		this.btnSalvar.addListener(SWT.Selection, new Listener()
		{
		    @Override
		    public void handleEvent(Event event)
		    {
		    	confirmou = true;
		    	formulario.setVisible(false);
		    }
		});
		
		//Cria um evento pra capturar o Click() do bot�o de cancelar
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

	//M�todo para mostrar o formul�rio na tela
	public void showModal() {
		Display display = Display.getDefault();
		
		//Abre o formul�rio na tela
		formulario.open();
		formulario.layout();
		while (formulario.isVisible()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}		
	}	

	//M�todo est�tico para iniciar a inclus�o de uma nova palavra
	public static List<String> incluirDefinicoesNovaPalavra(String novaPalavra) {
		//Declara a vari�vel de retorno e inst�ncia o formul�rio
		List<String> retorno = null;
		NovaPalavraForm form = new NovaPalavraForm(novaPalavra);
		
		//Mosta de forma modal na tela
		form.showModal();
		
		//Se o usu�rio confirmou a inclus�o e informou tradu��es, monta a lista de tradu��es
		if ((form.confirmou) && (!form.textTraducoes.getText().trim().isEmpty())) {
			//Inst�ncia o retorno
			retorno = new ArrayList<String>();
			
			//Percore o text de tradu��es quebrando as linhas do texto
			for (String traducao : form.textTraducoes.getText().split("\\n"))
				if (!traducao.trim().isEmpty())
					//Remove os caracteres de quebra de linha para adicionar a tradu��o � lista
					retorno.add(traducao.replaceAll("[\\r\\n]+", ""));
		}
		//Fecha o formul�rio
		form.formulario.close();

		return retorno;
	}
}