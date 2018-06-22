package br.unisinos.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.unisinos.classes.Dicionario;

class DicionarioTestes {

	@Test
	void TestarComparacaoMaior() {
		Dicionario temp1 = new Dicionario("viajei", null);
		Dicionario temp2 = new Dicionario("viajar", null);
		
		assertTrue(temp1.compareTo(temp2) > 0);
	}
	
	@Test
	void TestarComparacaoMenor() {
		Dicionario temp1 = new Dicionario("Casar", null);
		Dicionario temp2 = new Dicionario("Casei", null);
		
		assertTrue(temp1.compareTo(temp2) < 0);
	}	
	
	@Test
	void TestarComparacaoIgual() {
		Dicionario temp1 = new Dicionario("viajar", null);
		Dicionario temp2 = new Dicionario("viajar", null);
		
		assertTrue(temp1.compareTo(temp2) == 0);
	}	
}
