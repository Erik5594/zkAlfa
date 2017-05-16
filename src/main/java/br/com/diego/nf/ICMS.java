package br.com.diego.nf;

public class ICMS implements Imposto {

	public Double getValor(Double fatura) {
		return fatura*0.11;
	}

}
