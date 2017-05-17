package br.com.diego.nf;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GeradorNotaFiscal {
	public void geraNota(Fatura fatura, Imposto imposto) {
		NotaFiscal notaFiscal = geraNotaFiscal(fatura, imposto);
		armazenarNoBanco(notaFiscal);
		enviarEmail(fatura);
	}

	private void enviarEmail(Fatura fatura) {
		final String usuario = "refatoracaoalfa2017@gmail.com";
		final String senha = "refatoracao123";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(usuario, senha);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("refatoracaoalfa2017@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("erik.derick74@gmail.com"));
			message.setSubject("Titulo");
			message.setText("Mensagem");

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	private void armazenarNoBanco(NotaFiscal notaFiscal) {
		// Armazenar no BD
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		em.persist(notaFiscal);

		em.getTransaction().commit();
		em.close();
	}

	private NotaFiscal geraNotaFiscal(Fatura fatura, Imposto imposto) {
		double valorImposto = 0;

		valorImposto = imposto.getValor(fatura.getValor());

		NotaFiscal notaFiscal = new NotaFiscal(valorImposto, fatura.getValor());
		return notaFiscal;
	}
}
