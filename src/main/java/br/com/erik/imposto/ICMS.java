package br.com.erik.imposto;

public class ICMS implements Imposto {

	public Double getValor(Double fatura) {
		return fatura*0.11;
	}

}
