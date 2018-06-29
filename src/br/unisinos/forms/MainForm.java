/*
 * Estruturas avan�adas de dados - 2018/1 - Unisinos
 * Prof. Marcio Garcia Martins
 * Projeto: Tradutor ingl�s-Portugu�s utilizando uma �rvore AVL
 * Classe: Formul�rio principal da aplica��o
 */
package br.unisinos.forms;

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
import br.unisinos.classes.Dialogos;
import br.unisinos.classes.Tradutor;

public class MainForm extends BaseForm {
	private Label labelCurso;
	private Label labelTextPalavra;
	private Button btnImportarArquivo;
	private Button btnSalvarArquivo;
	private Button btnPesquisar;
	private Text textPalavra;
	private Text textDefinicoes;
	private Tradutor tradutor;
	private String[] FILTRO_DAT = new String[] {"*.dat"};
	
	//Contrutor da classe
	public MainForm() {
		//Inst�ncia a classe do tradutor
		this.tradutor = new Tradutor();
		//Inst�ncia o formul�rio
		this.InstanciarFormulario();		
	}
	
	//Inst�ncia e configura o formul�rio
	private void InstanciarFormulario() {
		//Inst�ncia o formul�rio principal e define seus atributos
		this.formulario = new Shell(SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		this.formulario.setSize(645, 440);
		this.formulario.setText("Tradutor ingl�s - Portugu�s utilizando �rvore AVL");	

		this.instanciarComponentesVisuais();
		this.definirEventosComponentesVisuais();
		
		//Posiciona o formul�rio centralizado no monitor do computador
		this.centralizarFormularioMonitor();
		//Define o bot�o "Pesquisar" como default, para fazer a pesquisa quando o usu�rio pressionar <ENTER>
		this.formulario.setDefaultButton(btnPesquisar);
	}
	
	//Cria e configura todos os componentes visuais, como labels, campos de texto e bot�es
	private void instanciarComponentesVisuais() {
		//Inst�ncia e configura o label com a descri��o do projeto
		this.labelCurso = new Label(this.formulario, SWT.NONE);
		this.labelCurso.setText("Estruturas avan�adas de dados 2018/1 - Unisinos \n" +
								"Prof. Marcio Garcia Martins \n" +
								"Projeto: Tradutor Ingl�s - Portugu�s utilizando uma �rvore AVL \n" +
								"Aluno - Lucas Rutkoski");
		this.labelCurso.setBounds(10, 10, 606, 85);
		
		//Inst�ncia e configura o bot�o de importa��o do arquivo
		this.btnImportarArquivo = new Button(this.formulario, SWT.NONE);
		this.btnImportarArquivo.setBounds(10, 101, 300, 30);
		this.btnImportarArquivo.setText("Importar arquivo com defini��es");
		
		//Inst�ncia e configura o bot�o de exporta��o do arquivo
		this.btnSalvarArquivo = new Button(this.formulario, SWT.NONE);
		this.btnSalvarArquivo.setText("Salvar arquivo com defini��es");
		this.btnSalvarArquivo.setBounds(316, 101, 300, 30);
		
		//Inst�ncia e configura o label que fica acima do text
		this.labelTextPalavra = new Label(this.formulario, SWT.NONE);
		this.labelTextPalavra.setBounds(10, 137, 606, 20);
		this.labelTextPalavra.setText("Informe uma palavra no campo abaixo e clique em \"Pesquisar\" para verificar sua tradu��o:");
		
		//Inst�ncia e configura o text onde o usu�rio vai informar a palavra
		this.textPalavra = new Text(this.formulario, SWT.BORDER);
		this.textPalavra.setBounds(10, 163, 477, 26);
		
		//Inst�ncia e configura o bot�o de pesquisa das tradu��es
		this.btnPesquisar = new Button(this.formulario, SWT.NONE);
		this.btnPesquisar.setText("Pesquisar");
		this.btnPesquisar.setBounds(493, 161, 123, 30);
		
		//Inst�ncia e configura o text onde ser�o exibidas as tradu��es
		this.textDefinicoes = new Text(this.formulario, SWT.BORDER | SWT.READ_ONLY | SWT.MULTI | SWT.V_SCROLL);
		this.textDefinicoes.setBounds(10, 205, 606, 192);	
		
		//Define o TAB Order dos campos do formul�rio
		this.formulario.setTabList(new Control[]{this.btnImportarArquivo, this.btnSalvarArquivo, this.textPalavra, 
													this.btnPesquisar, this.textDefinicoes});
	}
	
	//Define os eventos dos componentes visuais, como os clicks dos bot�es
	private void definirEventosComponentesVisuais() {
		//Cria um evento pra capturar o Click() do bot�o de importa��o do arquivo de defini��es
		this.btnImportarArquivo.addListener(SWT.Selection, new Listener()
		{
		    @Override
		    public void handleEvent(Event event)
		    {
		    	try {
		    		//Mostra o di�logo de sele��o do arquivo
		    		String arquivo = selecionarArquivoParaCarregar();
		    		//Se o arquivo veio nulo, usu�rio cancelou a sele��o do arquivo
		    		if (arquivo == null)
		    			return;
		    		
		    		//Passa o local do arquivo para o tradutor carregar
					tradutor.carregaDicionario(arquivo);
					Dialogos.mostrarMensagem(formulario, "Carregamento realizado com sucesso!");
				} 
		    	//Se aconteceu um erro ao carregar o arquivo
		    	catch (Exception e) {
					Dialogos.mostrarMensagemErro(formulario, "Houve um erro ao importar o arquivo selecionado! \n Erro encontrado: " + e.getMessage());
				}
		    }
		});
		
		//Cria um evento pra capturar o Click() do bot�o de exporta��o do arquivo de defini��es
		this.btnSalvarArquivo.addListener(SWT.Selection, new Listener()
		{
		    @Override
		    public void handleEvent(Event event)
		    {
		    	try {
		    		//Mostra o di�logo de sele��o do local de grava��o
		    		String arquivo = selecionarArquivoParaCarregar();
		    		//Se o arquivo veio nulo, usu�rio cancelou a sele��o do arquivo
		    		if (arquivo == null)
		    			return;		    		
		    		
		    		//Passa o local do arquivo para o tradutor descarregar o dicion�rio
		    		tradutor.salvaDicionario(arquivo);
		    		Dialogos.mostrarMensagem(formulario, "Arquivo salvo com sucesso!");
				} 
		    	//Se aconteceu um erro ao salvar o arquivo
		    	catch (Exception e) {
					Dialogos.mostrarMensagemErro(formulario, "Houve um erro ao salvar o arquivo no local selecionado! \n Erro encontrado: " + e.getMessage());
				}
		    }
		});
		
		//Cria um evento pra capturar o Click() do bot�o de pesquisa
		this.btnPesquisar.addListener(SWT.Selection, new Listener()
		{
		    @Override
		    public void handleEvent(Event event)
		    {
		    	//Limpa o text de defin��es sempre
		    	textDefinicoes.setText("");
		    	
		    	//Se o usu�rio n�o informou uma palavra, n�o faz nada
		    	if (textPalavra.getText().trim().isEmpty())
		    		return;
		    	
		    	//Busca as defini��es da palavra da classe do tradutor
		        List<String> definicoes = tradutor.traduzPalavra(textPalavra.getText());
		        
		        //Se encontrou defini��es na �rvore
				if (definicoes != null) {
					//Cria um StringBuilder e percorre a lista de defini��es para montar o resultado
					StringBuilder resultado = new StringBuilder("Tradu��es encontradas para a palavra \"").append(textPalavra.getText()).append("\": \n\n");
					for (String traducao : definicoes)
						resultado.append(traducao).append("\n");
					
					//Joga o resultado na tela
					textDefinicoes.setText(resultado.toString());
				}		
				else {
					//Mostra uma pergunta para o usu�rio se quer incluir a palavra que pesquisou no dicion�rio					
					StringBuilder pergunta = new StringBuilder("A palavra \"").append(textPalavra.getText()).append("\" n�o foi encontrada no dicion�rio.\n")
													   .append("Deseja incluir esta palavra e suas tradu��es?");					
					if (Dialogos.mostrarPerguntaSimNao(formulario, pergunta.toString()) == SWT.YES) {
						//Chama o formul�rio onde ele poder� digitar as tradu��es da palavra
						definicoes = NovaPalavraForm.incluirDefinicoesNovaPalavra(textPalavra.getText());
						
						//Se o usu�rio informou tradu��es, grava a nova palavra no tradutor
						if ((definicoes != null) && (definicoes.size() > 0))
							tradutor.insereTraducao(textPalavra.getText(), definicoes);
					}
				}
		    }
		});
	}
	
	//Mostra um di�logo para o usu�rio selecionar um arquivo .dat para importar no tradutor
	private String selecionarArquivoParaCarregar() {
		//Chama o m�todo que cria o di�logo padr�o e chama a rotina para abrir
		return Dialogos.criarFileDialog(this.formulario, SWT.OPEN, FILTRO_DAT).open();
	}
	
	//Mostra um di�logo para o usu�rio selecionar um local para salvar um arquivo .dat com os dados do tradutor
	private String selecionarArquivoParaSalvar() {
		//Chama o m�todo que cria o di�logo padr�o e chama a rotina para abrir
		return Dialogos.criarFileDialog(this.formulario, SWT.SAVE, FILTRO_DAT).open();		
	}
	
	//Mostrar o formul�rio na tela
	public void showModal() {
		Display display = Display.getDefault();
		
		//Abre o formul�rio na tela
		formulario.open();
		formulario.layout();
		while (!formulario.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}		
	}
	
	//M�todo principal da aplica��o
	public static void main(String[] args) {
		MainForm formulario = new MainForm();
		formulario.showModal();
	}
}