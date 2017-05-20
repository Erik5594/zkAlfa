package br.com.erik.entity;

import static org.junit.Assert.*;

import org.junit.Test;

public class NotaFiscalTest {

	private NotaFiscal notaFiscal;
	
	@Test
	public void test() {
		notaFiscal = new NotaFiscal();
		
		notaFiscal.setValorImposto(10.0d);
		notaFiscal.setValorBruto(100.0d);
		
		assertEquals(110.0,  notaFiscal.getValorTotal(),0.0001);
	}

}
