package org.tpapelita.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class SimpleMailSender implements ISimpleMailSender {
	public void send(String smtpHost, int smtpPort, String smtpUsername,
			String smtpPassword, String fromEmail, String toEmail,
			String subject, String body) throws UnsupportedEncodingException,
			MessagingException {

		InternetAddress from = new InternetAddress(fromEmail);
		InternetAddress[] to = new InternetAddress[] { new InternetAddress(
				toEmail) };

		Session session = createSession(smtpHost, smtpPort, smtpUsername,
				smtpPassword);

		MimeMessage msg = new MimeMessage(session);

		msg.setFrom(from);
		msg.setReplyTo(new InternetAddress[] { from });

		msg.setRecipients(Message.RecipientType.TO, to);

		msg.setSentDate(new Date());
		msg.setSubject(subject);

		msg.setContent(body, "text/plain");

		Transport.send(msg);
	}

	private Session createSession(String smtpHost, int smtpPort,
			final String smtpUsername, final String smtpPassword) {
		Properties props = new Properties();
		props.setProperty("mail.transport.default", "smtp");
		props.setProperty("mail.smtp.host", smtpHost);
		props.setProperty("mail.smtp.port", String.valueOf(smtpPort));
		props.setProperty("mail.store.protocol", "pop3");
		props.setProperty("mail.smtp.auth",
				String.valueOf(smtpUsername != null));

		Authenticator authenticator = null;
		if (smtpUsername != null) {
			props.setProperty("mail.smtp.starttls.enable", String.valueOf(true));

			authenticator = new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(smtpUsername,
							smtpPassword);
				}
			};
		}
		return Session.getInstance(props, authenticator);
	}
}