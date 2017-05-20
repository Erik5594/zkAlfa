package br.com.erik.notificacao;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.jboss.logging.Logger;
import org.zkoss.util.logging.Log;



import br.com.erik.entity.NotaFiscal;

public class NotificaEmail extends NotificacaoEmail{
	private final String emailDestino;
	private StringBuilder mensagem;
	private String titulo;
	
	private static Logger logger = Logger.getLogger(NotificaEmail.class);
	
	public NotificaEmail(final String emailDestino, final NotaFiscal notaFiscal) {
		this.emailDestino = emailDestino;
		this.titulo = "Dados da Nota Fiscal";
		this.mensagem = new StringBuilder("Nota Fiscal Nº: ");
		this.mensagem.append(notaFiscal.getId());
		this.mensagem.append("\n");
		this.mensagem.append("Valor do Imposto: ");
		this.mensagem.append("R$ ");
		this.mensagem.append(notaFiscal.getValorImposto());
		this.mensagem.append("\n");
		this.mensagem.append("Valor do Bruto: ");
		this.mensagem.append("R$ ");
		this.mensagem.append(notaFiscal.getValorBruto());
	}
	
	public void notificar(){
		try {
			Message message = new MimeMessage(abrirSessao());
			message.setFrom(new InternetAddress(USUARIO));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDestino));
			message.setSubject(titulo);
			message.setText(mensagem.toString());

			Transport.send(message);
		} catch (Exception e) {
			logger.error(e.getMessage());
			//throw new MyException("Erro ao enviar Email: " + e.getMessage());
		} 
	}

}
