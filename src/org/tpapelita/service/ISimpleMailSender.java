package org.tpapelita.service;

import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;

public interface ISimpleMailSender {

	void send(String smtpHost, int smtpPort, String smtpUsername,
			String smtpPassword, String fromEmail, String toEmail,
			String subject, String body) throws UnsupportedEncodingException,
			MessagingException;
}