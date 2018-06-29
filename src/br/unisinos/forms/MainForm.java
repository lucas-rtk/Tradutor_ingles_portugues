/*
 * Estruturas avançadas de dados - 2018/1 - Unisinos
 * Prof. Marcio Garcia Martins
 * Projeto: Tradutor inglês-Português utilizando uma árvore AVL
 * Classe: Formulário principal da aplicação
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
		//Instância a classe do tradutor
		this.tradutor = new Tradutor();
		//Instância o formulário
		this.InstanciarFormulario();		
	}
	
	//Instância e configura o formulário
	private void InstanciarFormulario() {
		//Instância o formulário principal e define seus atributos
		this.formulario = new Shell(SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		this.formulario.setSize(645, 440);
		this.formulario.setText("Tradutor inglês - Português utilizando árvore AVL");	

		this.instanciarComponentesVisuais();
		this.definirEventosComponentesVisuais();
		
		//Posiciona o formulário centralizado no monitor do computador
		this.centralizarFormularioMonitor();
		//Define o botão "Pesquisar" como default, para fazer a pesquisa quando o usuário pressionar <ENTER>
		this.formulario.setDefaultButton(btnPesquisar);
	}
	
	//Cria e configura todos os componentes visuais, como labels, campos de texto e botões
	private void instanciarComponentesVisuais() {
		//Instância e configura o label com a descrição do projeto
		this.labelCurso = new Label(this.formulario, SWT.NONE);
		this.labelCurso.setText("Estruturas avançadas de dados 2018/1 - Unisinos \n" +
								"Prof. Marcio Garcia Martins \n" +
								"Projeto: Tradutor Inglês - Português utilizando uma árvore AVL \n" +
								"Aluno - Lucas Rutkoski");
		this.labelCurso.setBounds(10, 10, 606, 85);
		
		//Instância e configura o botão de importação do arquivo
		this.btnImportarArquivo = new Button(this.formulario, SWT.NONE);
		this.btnImportarArquivo.setBounds(10, 101, 300, 30);
		this.btnImportarArquivo.setText("Importar arquivo com definições");
		
		//Instância e configura o botão de exportação do arquivo
		this.btnSalvarArquivo = new Button(this.formulario, SWT.NONE);
		this.btnSalvarArquivo.setText("Salvar arquivo com definições");
		this.btnSalvarArquivo.setBounds(316, 101, 300, 30);
		
		//Instância e configura o label que fica acima do text
		this.labelTextPalavra = new Label(this.formulario, SWT.NONE);
		this.labelTextPalavra.setBounds(10, 137, 606, 20);
		this.labelTextPalavra.setText("Informe uma palavra no campo abaixo e clique em \"Pesquisar\" para verificar sua tradução:");
		
		//Instância e configura o text onde o usuário vai informar a palavra
		this.textPalavra = new Text(this.formulario, SWT.BORDER);
		this.textPalavra.setBounds(10, 163, 477, 26);
		
		//Instância e configura o botão de pesquisa das traduções
		this.btnPesquisar = new Button(this.formulario, SWT.NONE);
		this.btnPesquisar.setText("Pesquisar");
		this.btnPesquisar.setBounds(493, 161, 123, 30);
		
		//Instância e configura o text onde serão exibidas as traduções
		this.textDefinicoes = new Text(this.formulario, SWT.BORDER | SWT.READ_ONLY | SWT.MULTI | SWT.V_SCROLL);
		this.textDefinicoes.setBounds(10, 205, 606, 192);	
		
		//Define o TAB Order dos campos do formulário
		this.formulario.setTabList(new Control[]{this.btnImportarArquivo, this.btnSalvarArquivo, this.textPalavra, 
													this.btnPesquisar, this.textDefinicoes});
	}
	
	//Define os eventos dos componentes visuais, como os clicks dos botões
	private void definirEventosComponentesVisuais() {
		//Cria um evento pra capturar o Click() do botão de importação do arquivo de definições
		this.btnImportarArquivo.addListener(SWT.Selection, new Listener()
		{
		    @Override
		    public void handleEvent(Event event)
		    {
		    	try {
		    		//Mostra o diálogo de seleção do arquivo
		    		String arquivo = selecionarArquivoParaCarregar();
		    		//Se o arquivo veio nulo, usuário cancelou a seleção do arquivo
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
		
		//Cria um evento pra capturar o Click() do botão de exportação do arquivo de definições
		this.btnSalvarArquivo.addListener(SWT.Selection, new Listener()
		{
		    @Override
		    public void handleEvent(Event event)
		    {
		    	try {
		    		//Mostra o diálogo de seleção do local de gravação
		    		String arquivo = selecionarArquivoParaCarregar();
		    		//Se o arquivo veio nulo, usuário cancelou a seleção do arquivo
		    		if (arquivo == null)
		    			return;		    		
		    		
		    		//Passa o local do arquivo para o tradutor descarregar o dicionário
		    		tradutor.salvaDicionario(arquivo);
		    		Dialogos.mostrarMensagem(formulario, "Arquivo salvo com sucesso!");
				} 
		    	//Se aconteceu um erro ao salvar o arquivo
		    	catch (Exception e) {
					Dialogos.mostrarMensagemErro(formulario, "Houve um erro ao salvar o arquivo no local selecionado! \n Erro encontrado: " + e.getMessage());
				}
		    }
		});
		
		//Cria um evento pra capturar o Click() do botão de pesquisa
		this.btnPesquisar.addListener(SWT.Selection, new Listener()
		{
		    @Override
		    public void handleEvent(Event event)
		    {
		    	//Limpa o text de definções sempre
		    	textDefinicoes.setText("");
		    	
		    	//Se o usuário não informou uma palavra, não faz nada
		    	if (textPalavra.getText().trim().isEmpty())
		    		return;
		    	
		    	//Busca as definições da palavra da classe do tradutor
		        List<String> definicoes = tradutor.traduzPalavra(textPalavra.getText());
		        
		        //Se encontrou definições na árvore
				if (definicoes != null) {
					//Cria um StringBuilder e percorre a lista de definições para montar o resultado
					StringBuilder resultado = new StringBuilder("Traduções encontradas para a palavra \"").append(textPalavra.getText()).append("\": \n\n");
					for (String traducao : definicoes)
						resultado.append(traducao).append("\n");
					
					//Joga o resultado na tela
					textDefinicoes.setText(resultado.toString());
				}		
				else {
					//Mostra uma pergunta para o usuário se quer incluir a palavra que pesquisou no dicionário					
					StringBuilder pergunta = new StringBuilder("A palavra \"").append(textPalavra.getText()).append("\" não foi encontrada no dicionário.\n")
													   .append("Deseja incluir esta palavra e suas traduções?");					
					if (Dialogos.mostrarPerguntaSimNao(formulario, pergunta.toString()) == SWT.YES) {
						//Chama o formulário onde ele poderá digitar as traduções da palavra
						definicoes = NovaPalavraForm.incluirDefinicoesNovaPalavra(textPalavra.getText());
						
						//Se o usuário informou traduções, grava a nova palavra no tradutor
						if ((definicoes != null) && (definicoes.size() > 0))
							tradutor.insereTraducao(textPalavra.getText(), definicoes);
					}
				}
		    }
		});
	}
	
	//Mostra um diálogo para o usuário selecionar um arquivo .dat para importar no tradutor
	private String selecionarArquivoParaCarregar() {
		//Chama o método que cria o diálogo padrão e chama a rotina para abrir
		return Dialogos.criarFileDialog(this.formulario, SWT.OPEN, FILTRO_DAT).open();
	}
	
	//Mostra um diálogo para o usuário selecionar um local para salvar um arquivo .dat com os dados do tradutor
	private String selecionarArquivoParaSalvar() {
		//Chama o método que cria o diálogo padrão e chama a rotina para abrir
		return Dialogos.criarFileDialog(this.formulario, SWT.SAVE, FILTRO_DAT).open();		
	}
	
	//Mostrar o formulário na tela
	public void showModal() {
		Display display = Display.getDefault();
		
		//Abre o formulário na tela
		formulario.open();
		formulario.layout();
		while (!formulario.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}		
	}
	
	//Método principal da aplicação
	public static void main(String[] args) {
		MainForm formulario = new MainForm();
		formulario.showModal();
	}
}