/*
 * Estruturas avan�adas de dados - 2018/1 - Unisinos
 * Prof. Marcio Garcia Martins
 * Projeto: Tradutor ingl�s-Portugu�s utilizando uma �rvore AVL
 * Classe: �rvore AVL que vai armazenar as palavras do tradutor
 */
package br.unisinos.classes;

import java.security.InvalidParameterException;

import br.unisinos.enums.TipoOrdenacao;

public class ArvoreAVL<T extends Comparable<T>> {

	private Nodo<T> raiz;
	
	//Construtor da classe
	public ArvoreAVL() {
		limpar();
	}
	
	//M�todo para limpar a estrutura da �rvore
	public void limpar() { 
		this.raiz = null; 
	}	
	
	//Verifica se a �rvore est� vazia
	public boolean estaVazia() { 
		return raiz == null; 
	}	
	
	//Devolve o nodo raiz da �rvore
	public Nodo<T> getRaiz() { 
		return this.raiz;
	}
	
	//Pesquisa se o valor j� existe na �rvore, devolvendo o nodo que cont�m ele
	private Nodo<T> pesquisar(Nodo<T> nodo, T valor) {
		//Se o nodo passado � nulo ou valor � igual ao valor do nodo, retorna ele mesmo
		if ((nodo == null) || valor.compareTo(nodo.getValor()) == 0)
			return nodo;
		//Se o valor � maior do que o valor do nodo, pesquisa para a direita
		else if (valor.compareTo(nodo.getValor()) > 0)
			return pesquisar(nodo.getDireito(), valor);
		else //Sen�o, pesquisa nos filhos � esquerda
			return pesquisar(nodo.getEsquerdo(), valor);			
	}	
	
	//Vers�o externa do m�todo de pesquisa, que chama o m�todo interno iniciando pela raiz
	public Nodo<T> pesquisar(T valor) { 
		return pesquisar(this.raiz, valor); 
	}
	
	//Insere um valor T na �rvore, desde de que ele n�o exista ainda. Recalcula os fatores de balanceamento e faz as rota��es necess�rias
	public boolean inserir(T valor) {
		Nodo<T> tmp = this.raiz;
		Nodo<T> pai = null;
		
		//Se o valor j� existe na �rvore, cancela a inser��o
		if (pesquisar(valor) != null)
			return false;
		
		//Procura o nodo pai do valor que ser� inserido
		while (tmp != null) {
			pai = tmp;			
			tmp = valor.compareTo(tmp.getValor()) > 0 ? tmp.getDireito() : tmp.getEsquerdo();
		}
		
		if (estaVazia())
			//Se a �rvore est� vazia, insere o nodo na raiz
			this.raiz = new Nodo<T>(valor);
		else {
			//Copara o valor com o nodo pai para saber se adiciona na esquerda ou direita
			if (valor.compareTo(pai.getValor()) > 0)
				pai.setDireito(new Nodo<T>(valor));
			else
				pai.setEsquerdo(new Nodo<T>(valor));
			
			//Calcula o balanceamento da �rvore ap�s a inser��o
			this.raiz.calcularBalanceamento();
			
			//Verifica o balanceamento do av� at� a raiz
			verificarBalanceamento(pai.getPai());
		}
		
		return true;			
	}
	
	//Verifica se um nodo da �rvore est� desbalanceado, conforme o seu fator de balanceamento
	private void verificarBalanceamento(Nodo<T> nodo) {
		//Se o nodo passado � nulo (�ltima verifica��o foi feita na raiz), encerra a verifica��o
		if (nodo == null)
			return;

		//Vari�vel de controle de modifica��o da �rvores
		boolean rotacionou = false;

		//Se o nodo est� desbalanceado para a direita
		if (nodo.getFator() > 1) {
			//Se o filho � esquerda tamb�m est� pendendo para a esquerda, faz uma rota��o simples � direita
			if (nodo.getEsquerdo().getFator() > 0)
				rotacaoSimplesDireita(nodo);
			else //Se o filho � esquerda est� pendendo para a direita, faz uma rota��o dupla � direita
				rotacaoDuplaDireita(nodo);

			//Marca que houve altera��o na �rvore
			rotacionou = true;
		} 
		//Se o nodo est� desbalanceado para a esquerda
		else if (nodo.getFator() < -1) {
			//Se o filho � direita tamb�m est� pendendo para a dirieta, faz uma rota��o simples � esquerda
			if (nodo.getDireito().getFator() < 0)
				rotacaoSimplesEsquerda(nodo);
			else //Se o filho � direita est� pendendo para a esquerda, faz uma rota��o dupla � esquerda
				rotacaoDuplaEsquerda(nodo);

			//Marca que houve altera��o na �rvore
			rotacionou = true;
		}

		//Se rotacionou a �rvore, recalcula o balanceamento
		if (rotacionou)
			raiz.calcularBalanceamento();
		
		//Verifica o balanceamento um n�vel acima
		verificarBalanceamento(nodo.getPai());
	}

	//Faz uma rota��o simples � direita no nodo
	private void rotacaoSimplesDireita(Nodo<T> nodoPai) {
		//Guarda o nodo que ser� rotacionado numa vari�vel tempor�ria
		Nodo<T> temp = nodoPai.getEsquerdo();
		//substitui o valor do nodo que est� sendo rotacionado, pelo valor do seu filho � direita
		nodoPai.setEsquerdo(temp.getDireito());

		//Se o nodo pai do que est� sendo rotacionado � a raiz
		if (nodoPai == this.raiz) {
			//Define a raiz atual da arvore como nodo � direita do que est� sendo rotacionado;
			temp.setDireito(this.raiz);
			//Define o nodo que est� sendo rotacionado como raiz; 
			this.raiz = temp;
			//Limpa o pai da nova raiz
			this.raiz.setPai(null);
		}
		//Se o nodo pai do que est� sendo rotacionado � o nodo � direita do av�
		else if (nodoPai == nodoPai.getPai().getDireito()) {
			//Define o nodo que est� sendo rotacionado como nodo � direita do av�
			nodoPai.getPai().setDireito(temp);
			//Define o nodo pai do que est� sendo rotacionado como nodo � direita do que sofreu rota��o
			temp.setDireito(nodoPai);
		} 
		//Se o nodo que est� sendo rotacionado � o nodo � esquerda do pai
		else {
			//Define o nodo que est� sendo rotacionado como nodo � esquerda do av�
			nodoPai.getPai().setEsquerdo(temp);
			//Define o nodo pai do que est� sendo rotacionado como nodo � direita do que sofreu rota��o
			temp.setDireito(nodoPai);
		}
	}

	//Faz uma rota��o simples � esquerda no nodo
	private void rotacaoSimplesEsquerda(Nodo<T> nodoPai) {
		//Guarda o nodo que ser� rotacionado numa vari�vel tempor�ria
		Nodo<T> temp = nodoPai.getDireito();
		//Define como nodo � direita do pai do nodo que ser� rotacionado, o nodo � esquerda do que ser� rotacionado 
		nodoPai.setDireito(temp.getEsquerdo());

		//Se o nodo pai do que est� sendo rotacionado � a raiz
		if (nodoPai == this.raiz) {
			//Define a raiz atual da arvore como nodo � esquerda do que est� sendo rotacionado;
			temp.setEsquerdo(this.raiz);
			//Define o nodo que est� sendo rotacionado como raiz; 
			this.raiz = temp;
			//Limpa o pai da nova raiz
			this.raiz.setPai(null);
		}
		//Se o nodo pai do que est� sendo rotacionado � o nodo � direita do av�
		else if (nodoPai == nodoPai.getPai().getDireito()) {
			//Define o nodo que est� sendo rotacionado como nodo � direita do av�
			nodoPai.getPai().setDireito(temp);
			//Define o nodo pai do que est� sendo rotacionado como nodo � esquerda do que sofreu rota��o
			temp.setEsquerdo(nodoPai);
		} else {
			//Define o nodo que est� sendo rotacionado como nodo � esquerda do av�
			nodoPai.getPai().setEsquerdo(temp);
			//Define o nodo pai do que est� sendo rotacionado como nodo � esquerda do que sofreu rota��o
			temp.setEsquerdo(nodoPai);
		}
	}

	//Faz uma rota��o dupla � direita
	private void rotacaoDuplaDireita(Nodo<T> nodoPai) {
		//Faz primeiro uma rota��o simples � esquerda no nodo � esquerda do que ser� rotacionado
		rotacaoSimplesEsquerda(nodoPai.getEsquerdo());
		//Faz uma rota��o simples � direita no nodo
		rotacaoSimplesDireita(nodoPai);
	}

	//Faz uma rota��o dupla � esquerda
	private void rotacaoDuplaEsquerda(Nodo<T> nodoPai) {
		//Faz primeiro uma rota��o simples � direita no nodo � direita do que ser� rotacionado
		rotacaoSimplesDireita(nodoPai.getDireito());
		//Faz uma rota��o simples � esquerda no nodo
		rotacaoSimplesEsquerda(nodoPai);
	}

	//remove um valor da �rvore
	public void excluir(T valor) {
		//Pesquisa o nodo que possui o valor a ser exclu�do da �rvore
		Nodo<T> temp = pesquisar(valor);
		
		//Se o valor n�o consta na �rvore, encerra a exclus�o
		if (temp == null)
			return;
		
		//Se o nodo a ser exclu�do n�o possui filhos
		if (temp.getEsquerdo() == temp.getDireito()) { //-- ambos s�o nulos
			//Se o nodo que est� sendo exclu�do est� a esquerda do pai
			if (temp.getPai().getEsquerdo() == temp)
				//Seta a esquerda do pai como nulo
				temp.getPai().setEsquerdo(null);
			//Se o nodo que est� sendo exclu�do est� a direita do pai
			else
				//Seta a direita do pai como nulo
				temp.getPai().setDireito(null);
		}
		//Se o nodo a ser exclu�do tem um filho � esquerda somente
		else if ((temp.getEsquerdo() != null) && (temp.getDireito() == null)) {
			//Guarda o nodo � esquerda do que ser� exclu�do numa vari�vel tempor�ria
			Nodo<T> tempEsquerdo = temp.getEsquerdo();
			//Limpa o nodo � esquerda do que ser� exclu�do
			temp.setEsquerdo(null);
			//Troca o valor do nodo que est� sendo exclu�do pelo valor do seu filho � esquerda
			temp.setValor(tempEsquerdo.getValor());			
		}
		//Se o nodo a ser exclu�do tem um filho � direita somente
		else if ((temp.getDireito() != null) && (temp.getDireito() == null)) {
			//Guarda o nodo � direita do que ser� exclu�do numa vari�vel tempor�ria
			Nodo<T> tempDireito = temp.getDireito();
			//Limpa o nodo � direita do que ser� exclu�do
			temp.setDireito(null);
			//Troca o valor do nodo que est� sendo exclu�do pelo valor do seu filho � direita
			temp.setValor(tempDireito.getValor());
		}
		//Se o nodo a ser exclu�do possui filhos dos dois lados
		else {
			//Obt�m o menor valor do lado direito do nodo a ser exclu�do
			T novoValor = getMenorValor(temp.getDireito());
			//Exclui o nodo com o menor valor encontrado da �rvore
			excluir(novoValor);
			//Troca o valor do nodo que est� sendo exclu�do pelo menor valor encontrado � sua direita
			temp.setValor(novoValor);
		}			
	}
	
	//Obt�m o menor valor � esquerda de um nodo. Se ele n�o tiver filhos � esquerda, ele � devolvido como sendo o menor
	private T getMenorValor(Nodo<T> nodo) {
		//Se o nodo atual n�o tem mais filhos � esquerda
		if (nodo.getEsquerdo() == null)
			//Devolve o seu valor como o menor do nodo original
			return nodo.getValor();
		else
			//Desce mais um n�vel � esquerda para obter o menor valor
			return getMenorValor(nodo.getEsquerdo());
	}
	
	//Devolve uma string com os valores da �rvore na seguinte ordem: Pai - Esquerda - Direita
	private String preOrdem(Nodo<T> nodo) {
		//Se o nodo n�o possui valor
		if (nodo == null)
			//Retorna em branco
			return "";
		else
			//Retorno o valor do seu nodo, ap�s os valores dos seus filhos � esquerda e por �ltimo os seus filhos � direita
			return nodo.getValor() +  " " + preOrdem(nodo.getEsquerdo()) + preOrdem(nodo.getDireito());
	}
	
	//Devolve uma string com os valores da �rvore na seguinte ordem: Esquerda - Pai - Direita
	private String emOrdem(Nodo<T> nodo) {
		//Se o nodo n�o possui valor
		if (nodo == null)
			//Retorna em branco
			return "";
		else
			//Retorno o valor dos seus filhos � esquerda, ap�s o seu valor e por �ltimo os seus filhos � direita
			return emOrdem(nodo.getEsquerdo()) + nodo.getValor() + " " + emOrdem(nodo.getDireito());
	}
	
	//Devolve uma string com os valores da �rvore na seguinte ordem: Esquerda - Direita - Pai
	private String posOrdem(Nodo<T> nodo) {
		//Se o nodo n�o possui valor
		if (nodo == null)
			//Retorna em branco
			return "";
		else
			//Retorno o valor dos seus filhos � esquerda, ap�s o valor dos seus filhos � dereita e por �ltimo o seu valor
			return posOrdem(nodo.getEsquerdo()) + posOrdem(nodo.getDireito()) + nodo.getValor() + " ";
	}	
	
	//Implementa��o do m�todo ToString para a classe, devolvendo uma String conforme o tipo de ordena��o escolhido
	public String toString(TipoOrdenacao ordenacao) {
		//Conforme o par�metro recebido, chama o m�todo privado adequado para imprimir os valores da �rvore
		switch (ordenacao) {
			case PreOrdem:
				return preOrdem(this.raiz);
			case EmOrdem:
				return emOrdem(this.raiz);
			case PosOrdem:
				return posOrdem(this.raiz);
			default:
				throw new InvalidParameterException("Par�metro de ordena��o n�o esperado!");
		}		
	}	
}