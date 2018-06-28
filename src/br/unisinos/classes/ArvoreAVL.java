/*
 * Estruturas avançadas de dados - 2018/1 - Unisinos
 * Prof. Marcio Garcia Martins
 * Projeto: Tradutor inglês-Português utilizando uma árvore AVL
 * Classe: Árvore AVL que vai armazenar as palavras do tradutor
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
	
	//Método para limpar a estrutura da árvore
	public void limpar() { 
		this.raiz = null; 
	}	
	
	//Verifica se a árvore está vazia
	public boolean estaVazia() { 
		return raiz == null; 
	}	
	
	//Devolve o nodo raiz da árvore
	public Nodo<T> getRaiz() { 
		return this.raiz;
	}
	
	//Pesquisa se o valor já existe na árvore, devolvendo o nodo que contém ele
	private Nodo<T> pesquisar(Nodo<T> nodo, T valor) {
		//Se o nodo passado é nulo ou valor é igual ao valor do nodo, retorna ele mesmo
		if ((nodo == null) || valor.compareTo(nodo.getValor()) == 0)
			return nodo;
		//Se o valor é maior do que o valor do nodo, pesquisa para a direita
		else if (valor.compareTo(nodo.getValor()) > 0)
			return pesquisar(nodo.getDireito(), valor);
		else //Senão, pesquisa nos filhos à esquerda
			return pesquisar(nodo.getEsquerdo(), valor);			
	}	
	
	//Versão externa do método de pesquisa, que chama o método interno iniciando pela raiz
	public Nodo<T> pesquisar(T valor) { 
		return pesquisar(this.raiz, valor); 
	}
	
	//Insere um valor T na árvore, desde de que ele não exista ainda. Recalcula os fatores de balanceamento e faz as rotações necessárias
	public boolean inserir(T valor) {
		Nodo<T> tmp = this.raiz;
		Nodo<T> pai = null;
		
		//Se o valor já existe na árvore, cancela a inserção
		if (pesquisar(valor) != null)
			return false;
		
		//Procura o nodo pai do valor que será inserido
		while (tmp != null) {
			pai = tmp;			
			tmp = valor.compareTo(tmp.getValor()) > 0 ? tmp.getDireito() : tmp.getEsquerdo();
		}
		
		if (estaVazia())
			//Se a árvore está vazia, insere o nodo na raiz
			this.raiz = new Nodo<T>(valor);
		else {
			//Copara o valor com o nodo pai para saber se adiciona na esquerda ou direita
			if (valor.compareTo(pai.getValor()) > 0)
				pai.setDireito(new Nodo<T>(valor));
			else
				pai.setEsquerdo(new Nodo<T>(valor));
			
			//Calcula o balanceamento da árvore após a inserção
			this.raiz.calcularBalanceamento();
			
			//Verifica o balanceamento do avô até a raiz
			verificarBalanceamento(pai.getPai());
		}
		
		return true;			
	}
	
	//Verifica se um nodo da árvore está desbalanceado, conforme o seu fator de balanceamento
	private void verificarBalanceamento(Nodo<T> nodo) {
		//Se o nodo passado é nulo (última verificação foi feita na raiz), encerra a verificação
		if (nodo == null)
			return;

		//Variável de controle de modificação da árvores
		boolean rotacionou = false;

		//Se o nodo está desbalanceado para a direita
		if (nodo.getFator() > 1) {
			//Se o filho à esquerda também está pendendo para a esquerda, faz uma rotação simples à direita
			if (nodo.getEsquerdo().getFator() > 0)
				rotacaoSimplesDireita(nodo);
			else //Se o filho à esquerda está pendendo para a direita, faz uma rotação dupla à direita
				rotacaoDuplaDireita(nodo);

			//Marca que houve alteração na árvore
			rotacionou = true;
		} 
		//Se o nodo está desbalanceado para a esquerda
		else if (nodo.getFator() < -1) {
			//Se o filho à direita também está pendendo para a dirieta, faz uma rotação simples à esquerda
			if (nodo.getDireito().getFator() < 0)
				rotacaoSimplesEsquerda(nodo);
			else //Se o filho à direita está pendendo para a esquerda, faz uma rotação dupla à esquerda
				rotacaoDuplaEsquerda(nodo);

			//Marca que houve alteração na árvore
			rotacionou = true;
		}

		//Se rotacionou a árvore, recalcula o balanceamento
		if (rotacionou)
			raiz.calcularBalanceamento();
		
		//Verifica o balanceamento um nível acima
		verificarBalanceamento(nodo.getPai());
	}

	//Faz uma rotação simples à direita no nodo
	private void rotacaoSimplesDireita(Nodo<T> nodoPai) {
		//Guarda o nodo que será rotacionado numa variável temporária
		Nodo<T> temp = nodoPai.getEsquerdo();
		//substitui o valor do nodo que está sendo rotacionado, pelo valor do seu filho à direita
		nodoPai.setEsquerdo(temp.getDireito());

		//Se o nodo pai do que está sendo rotacionado é a raiz
		if (nodoPai == this.raiz) {
			//Define a raiz atual da arvore como nodo à direita do que está sendo rotacionado;
			temp.setDireito(this.raiz);
			//Define o nodo que está sendo rotacionado como raiz; 
			this.raiz = temp;
			//Limpa o pai da nova raiz
			this.raiz.setPai(null);
		}
		//Se o nodo pai do que está sendo rotacionado é o nodo à direita do avô
		else if (nodoPai == nodoPai.getPai().getDireito()) {
			//Define o nodo que está sendo rotacionado como nodo à direita do avô
			nodoPai.getPai().setDireito(temp);
			//Define o nodo pai do que está sendo rotacionado como nodo à direita do que sofreu rotação
			temp.setDireito(nodoPai);
		} 
		//Se o nodo que está sendo rotacionado é o nodo à esquerda do pai
		else {
			//Define o nodo que está sendo rotacionado como nodo à esquerda do avô
			nodoPai.getPai().setEsquerdo(temp);
			//Define o nodo pai do que está sendo rotacionado como nodo à direita do que sofreu rotação
			temp.setDireito(nodoPai);
		}
	}

	//Faz uma rotação simples à esquerda no nodo
	private void rotacaoSimplesEsquerda(Nodo<T> nodoPai) {
		//Guarda o nodo que será rotacionado numa variável temporária
		Nodo<T> temp = nodoPai.getDireito();
		//Define como nodo à direita do pai do nodo que será rotacionado, o nodo à esquerda do que será rotacionado 
		nodoPai.setDireito(temp.getEsquerdo());

		//Se o nodo pai do que está sendo rotacionado é a raiz
		if (nodoPai == this.raiz) {
			//Define a raiz atual da arvore como nodo à esquerda do que está sendo rotacionado;
			temp.setEsquerdo(this.raiz);
			//Define o nodo que está sendo rotacionado como raiz; 
			this.raiz = temp;
			//Limpa o pai da nova raiz
			this.raiz.setPai(null);
		}
		//Se o nodo pai do que está sendo rotacionado é o nodo à direita do avô
		else if (nodoPai == nodoPai.getPai().getDireito()) {
			//Define o nodo que está sendo rotacionado como nodo à direita do avô
			nodoPai.getPai().setDireito(temp);
			//Define o nodo pai do que está sendo rotacionado como nodo à esquerda do que sofreu rotação
			temp.setEsquerdo(nodoPai);
		} else {
			//Define o nodo que está sendo rotacionado como nodo à esquerda do avô
			nodoPai.getPai().setEsquerdo(temp);
			//Define o nodo pai do que está sendo rotacionado como nodo à esquerda do que sofreu rotação
			temp.setEsquerdo(nodoPai);
		}
	}

	//Faz uma rotação dupla à direita
	private void rotacaoDuplaDireita(Nodo<T> nodoPai) {
		//Faz primeiro uma rotação simples à esquerda no nodo à esquerda do que será rotacionado
		rotacaoSimplesEsquerda(nodoPai.getEsquerdo());
		//Faz uma rotação simples à direita no nodo
		rotacaoSimplesDireita(nodoPai);
	}

	//Faz uma rotação dupla à esquerda
	private void rotacaoDuplaEsquerda(Nodo<T> nodoPai) {
		//Faz primeiro uma rotação simples à direita no nodo à direita do que será rotacionado
		rotacaoSimplesDireita(nodoPai.getDireito());
		//Faz uma rotação simples à esquerda no nodo
		rotacaoSimplesEsquerda(nodoPai);
	}

	//remove um valor da árvore
	public void excluir(T valor) {
		//Pesquisa o nodo que possui o valor a ser excluído da árvore
		Nodo<T> temp = pesquisar(valor);
		
		//Se o valor não consta na árvore, encerra a exclusão
		if (temp == null)
			return;
		
		//Se o nodo a ser excluído não possui filhos
		if (temp.getEsquerdo() == temp.getDireito()) { //-- ambos são nulos
			//Se o nodo que está sendo excluído está a esquerda do pai
			if (temp.getPai().getEsquerdo() == temp)
				//Seta a esquerda do pai como nulo
				temp.getPai().setEsquerdo(null);
			//Se o nodo que está sendo excluído está a direita do pai
			else
				//Seta a direita do pai como nulo
				temp.getPai().setDireito(null);
		}
		//Se o nodo a ser excluído tem um filho à esquerda somente
		else if ((temp.getEsquerdo() != null) && (temp.getDireito() == null)) {
			//Guarda o nodo à esquerda do que será excluído numa variável temporária
			Nodo<T> tempEsquerdo = temp.getEsquerdo();
			//Limpa o nodo à esquerda do que será excluído
			temp.setEsquerdo(null);
			//Troca o valor do nodo que está sendo excluído pelo valor do seu filho à esquerda
			temp.setValor(tempEsquerdo.getValor());			
		}
		//Se o nodo a ser excluído tem um filho à direita somente
		else if ((temp.getDireito() != null) && (temp.getDireito() == null)) {
			//Guarda o nodo à direita do que será excluído numa variável temporária
			Nodo<T> tempDireito = temp.getDireito();
			//Limpa o nodo à direita do que será excluído
			temp.setDireito(null);
			//Troca o valor do nodo que está sendo excluído pelo valor do seu filho à direita
			temp.setValor(tempDireito.getValor());
		}
		//Se o nodo a ser excluído possui filhos dos dois lados
		else {
			//Obtém o menor valor do lado direito do nodo a ser excluído
			T novoValor = getMenorValor(temp.getDireito());
			//Exclui o nodo com o menor valor encontrado da árvore
			excluir(novoValor);
			//Troca o valor do nodo que está sendo excluído pelo menor valor encontrado à sua direita
			temp.setValor(novoValor);
		}			
	}
	
	//Obtém o menor valor à esquerda de um nodo. Se ele não tiver filhos à esquerda, ele é devolvido como sendo o menor
	private T getMenorValor(Nodo<T> nodo) {
		//Se o nodo atual não tem mais filhos à esquerda
		if (nodo.getEsquerdo() == null)
			//Devolve o seu valor como o menor do nodo original
			return nodo.getValor();
		else
			//Desce mais um nível à esquerda para obter o menor valor
			return getMenorValor(nodo.getEsquerdo());
	}
	
	//Devolve uma string com os valores da árvore na seguinte ordem: Pai - Esquerda - Direita
	private String preOrdem(Nodo<T> nodo) {
		//Se o nodo não possui valor
		if (nodo == null)
			//Retorna em branco
			return "";
		else
			//Retorno o valor do seu nodo, após os valores dos seus filhos à esquerda e por último os seus filhos à direita
			return nodo.getValor() +  " " + preOrdem(nodo.getEsquerdo()) + preOrdem(nodo.getDireito());
	}
	
	//Devolve uma string com os valores da árvore na seguinte ordem: Esquerda - Pai - Direita
	private String emOrdem(Nodo<T> nodo) {
		//Se o nodo não possui valor
		if (nodo == null)
			//Retorna em branco
			return "";
		else
			//Retorno o valor dos seus filhos à esquerda, após o seu valor e por último os seus filhos à direita
			return emOrdem(nodo.getEsquerdo()) + nodo.getValor() + " " + emOrdem(nodo.getDireito());
	}
	
	//Devolve uma string com os valores da árvore na seguinte ordem: Esquerda - Direita - Pai
	private String posOrdem(Nodo<T> nodo) {
		//Se o nodo não possui valor
		if (nodo == null)
			//Retorna em branco
			return "";
		else
			//Retorno o valor dos seus filhos à esquerda, após o valor dos seus filhos à dereita e por último o seu valor
			return posOrdem(nodo.getEsquerdo()) + posOrdem(nodo.getDireito()) + nodo.getValor() + " ";
	}	
	
	//Implementação do método ToString para a classe, devolvendo uma String conforme o tipo de ordenação escolhido
	public String toString(TipoOrdenacao ordenacao) {
		//Conforme o parâmetro recebido, chama o método privado adequado para imprimir os valores da árvore
		switch (ordenacao) {
			case PreOrdem:
				return preOrdem(this.raiz);
			case EmOrdem:
				return emOrdem(this.raiz);
			case PosOrdem:
				return posOrdem(this.raiz);
			default:
				throw new InvalidParameterException("Parâmetro de ordenação não esperado!");
		}		
	}	
}