package br.unisinos.tests;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

import br.unisinos.classes.*;
import br.unisinos.enums.TipoOrdenacao;

class ArvoreAVLTestes {

	@Test
	final void testarEstaVazia() {
		ArvoreAVL<Dicionario> arvore = new ArvoreAVL<Dicionario>();
		
		assertNull(arvore.getRaiz());
	}
	
	@Test
	final void testarInclusaoDuplicada() {
		ArvoreAVL<Dicionario> arvore = new ArvoreAVL<Dicionario>();
		
		assertTrue(arvore.inserir(new Dicionario("casa", null)));		
		assertFalse(arvore.inserir(new Dicionario("casa", null)));				
	}	
	
	@Test
	final void testarImpressaoPreOrdem() {
		ArvoreAVL<Dicionario> arvore = new ArvoreAVL<Dicionario>();
		
		arvore.inserir(new Dicionario("casar", null));
		arvore.inserir(new Dicionario("casarei", null));
		arvore.inserir(new Dicionario("casa", null));
		
		assertEquals("casar casa casarei ", arvore.toString(TipoOrdenacao.PreOrdem));
	}
	
	@Test
	final void testarImpressaoEmOrdem() {
		ArvoreAVL<Dicionario> arvore = new ArvoreAVL<Dicionario>();
		
		arvore.inserir(new Dicionario("casar", null));
		arvore.inserir(new Dicionario("casarei", null));
		arvore.inserir(new Dicionario("casa", null));
		arvore.inserir(new Dicionario("caso", null));
		arvore.inserir(new Dicionario("casei", null));
		
		assertEquals("casa casar casarei casei caso ", arvore.toString(TipoOrdenacao.EmOrdem));
	}		
	
	@Test
	final void testarImpressaoPosOrdem() {
		ArvoreAVL<Dicionario> arvore = new ArvoreAVL<Dicionario>();
		
		arvore.inserir(new Dicionario("casar", null));
		arvore.inserir(new Dicionario("casarei", null));
		arvore.inserir(new Dicionario("casa", null));
		
		assertEquals("casa casarei casar ", arvore.toString(TipoOrdenacao.PosOrdem));
	}	
	
	@Test
	final void testarRotacaoSimplesDireita() {
		ArvoreAVL<Integer> arvore = new ArvoreAVL<Integer>();
		
		arvore.inserir(5);
		arvore.inserir(6);
		arvore.inserir(2);
		arvore.inserir(1);
		arvore.inserir(0);
		
		assertEquals("0 2 1 6 5 ", arvore.toString(TipoOrdenacao.PosOrdem));		
	}
	
	@Test
	final void testarRotacaoSimplesEsquerda() {
		ArvoreAVL<Dicionario> arvore = new ArvoreAVL<Dicionario>();
		
		arvore.inserir(new Dicionario("casar", null));
		arvore.inserir(new Dicionario("casarei", null));
		arvore.inserir(new Dicionario("casa", null));
		arvore.inserir(new Dicionario("caso", null));
		arvore.inserir(new Dicionario("casei", null));
		
		assertEquals("casa casar casarei casei caso ", arvore.toString(TipoOrdenacao.EmOrdem));		
	}
	
	@Test
	final void testarRotacaoDuplaDireita() {
		ArvoreAVL<Integer> arvore = new ArvoreAVL<Integer>();
		
		arvore.inserir(120);
		arvore.inserir(150);
		arvore.inserir(110);
		arvore.inserir(80);
		arvore.inserir(100);
		
		assertEquals("80 110 100 150 120 ", arvore.toString(TipoOrdenacao.PosOrdem));		
	}	
	
	@Test
	final void testarRotacaoDuplaEsquerda() {
		ArvoreAVL<Integer> arvore = new ArvoreAVL<Integer>();
		
		arvore.inserir(120);
		arvore.inserir(100);
		arvore.inserir(130);
		arvore.inserir(200);
		arvore.inserir(150);
		
		assertEquals("100 130 200 150 120 ", arvore.toString(TipoOrdenacao.PosOrdem));		
	}		
	
	@Test
	final void testarRotacaoSimplesEsquerdaComTrocaRaiz() {
		ArvoreAVL<Integer> arvore = new ArvoreAVL<Integer>();
		
		arvore.inserir(10);
		arvore.inserir(12);
		arvore.inserir(13);
		
		assertEquals("12 10 13 ", arvore.toString(TipoOrdenacao.PreOrdem));		
	}		
	
	@Test
	final void testarRotacaoSimplesDireitaComTrocaRaiz() {
		ArvoreAVL<Integer> arvore = new ArvoreAVL<Integer>();
		
		arvore.inserir(10);
		arvore.inserir(9);
		arvore.inserir(8);
		
		assertEquals("9 8 10 ", arvore.toString(TipoOrdenacao.PreOrdem));		
	}
	
	@Test
	final void testarExclusaoNodoPonta() {
		ArvoreAVL<Integer> arvore = new ArvoreAVL<Integer>();
		
		arvore.inserir(14);
		arvore.inserir(11);
		arvore.inserir(15);
		arvore.inserir(5);
		arvore.inserir(13);
		arvore.inserir(16);
		arvore.inserir(12);
		
		arvore.excluir(12);
		
		assertEquals("5 11 13 14 15 16 ", arvore.toString(TipoOrdenacao.EmOrdem));		
	}
	
	@Test
	final void testarExclusaoNodoComFilhoEsquerda() {
		ArvoreAVL<Integer> arvore = new ArvoreAVL<Integer>();
		
		arvore.inserir(14);
		arvore.inserir(11);
		arvore.inserir(15);
		arvore.inserir(5);
		arvore.inserir(13);
		arvore.inserir(16);
		arvore.inserir(12);
		
		arvore.excluir(13);
		
		assertEquals("5 11 12 14 15 16 ", arvore.toString(TipoOrdenacao.EmOrdem));		
	}			
	
	@Test
	final void testarExclusaoNodoComFilhoDireita() {
		ArvoreAVL<Integer> arvore = new ArvoreAVL<Integer>();
		
		arvore.inserir(14);
		arvore.inserir(11);
		arvore.inserir(15);
		arvore.inserir(5);
		arvore.inserir(13);
		arvore.inserir(16);
		arvore.inserir(12);
		
		arvore.excluir(15);
		
		assertEquals("5 11 12 13 14 16 ", arvore.toString(TipoOrdenacao.EmOrdem));		
	}			
	
	@Test
	final void testarExclusaoNodoComDoisFilhos() {
		ArvoreAVL<Integer> arvore = new ArvoreAVL<Integer>();
		
		arvore.inserir(14);
		arvore.inserir(11);
		arvore.inserir(15);
		arvore.inserir(5);
		arvore.inserir(13);
		arvore.inserir(16);
		arvore.inserir(12);
		
		arvore.excluir(11);
		
		assertEquals("5 12 13 14 15 16 ", arvore.toString(TipoOrdenacao.EmOrdem));		
	}			
}
