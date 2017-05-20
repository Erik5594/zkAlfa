package br.com.erik.nf;

import org.zkoss.zk.ui.event.GenericEventListener;

import br.com.erik.dao.NotaFiscalDao;
import br.com.erik.entity.Fatura;
import br.com.erik.entity.NotaFiscal;
import br.com.erik.imposto.Imposto;
import br.com.erik.notificacao.NotificaEmail;
import br.com.erik.notificacao.Notificador;

public class GeradorNotaFiscal {
	
	public void geraNota(Fatura fatura, Imposto imposto, String emailDestino) {
		NotaFiscal notaFiscal = geraNotaFiscal(fatura, imposto);
		
		NotaFiscalDao notaFiscalDao = new NotaFiscalDao();
		notaFiscalDao.armazenarNoBanco(notaFiscal);
		
		Notificador noticadorEmail = new NotificaEmail(emailDestino, notaFiscal);
		noticadorEmail.notificar();
		
	}

	private NotaFiscal geraNotaFiscal(Fatura fatura, Imposto imposto) {
		
		double valorImposto = imposto.getValor(fatura.getValor());

		NotaFiscal notaFiscal = new NotaFiscal(valorImposto, fatura.getValor());
		
		return notaFiscal;
	}
}
