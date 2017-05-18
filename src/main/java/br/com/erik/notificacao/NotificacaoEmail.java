package br.com.erik.notificacao;

import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public abstract class NotificacaoEmail implements Notificador{
	protected static final String USUARIO = "refatoracaoalfa2017@gmail.com";
	private static final String SENHA = "refatoracao123";
	
	private Properties getPropriedades(){
		Properties propriedades = new Properties();
		propriedades.put("mail.smtp.auth", "true");
		propriedades.put("mail.smtp.starttls.enable", "true");
		propriedades.put("mail.smtp.host", "smtp.gmail.com");
		propriedades.put("mail.smtp.port", "587");
		return propriedades;
	}
	
	protected Session abrirSessao() {
		return Session.getInstance(getPropriedades(), new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USUARIO, SENHA);
			}
		});
	}
}
