package com.ua.mail;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * Class which is responsible for sending letters to email
 * 
 * @author Serhii Vyshnevskyi
 */
public class Mail {

	private static final String SMTP_HOST_NAME = "smtp.gmail.com";
	private static final String SMTP_PORT = "465";
	private static final String EMAIL_FROM_ADDRESS = "sergonv077@gmail.com";
	private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	private static final String EMAIL_SUBJECT = "VanTea.ua_Code for login";

	public String generateValue() {
		Random ran = new Random();
		Integer i = ran.nextInt(99999) + 10000;
		return i.toString();
	}

	public String code = generateValue();

	public String getCode() {
		return code;
	}

	String emailMsgTxt = "It is your code: " + code;

	public void sendMail(String[] email) throws MessagingException {

		new Mail().sendMessage(email, EMAIL_SUBJECT, emailMsgTxt, EMAIL_FROM_ADDRESS);
	}

	public void sendMessage(String recipients[], String subject, String message, String from)
			throws MessagingException {
		boolean debug = true;

		Properties props = new Properties();

		props.put("mail.smtp.host", SMTP_HOST_NAME);

		props.put("mail.smtp.auth", "true");

		props.put("mail.debug", "true");
		props.put("mail.smtp.port", SMTP_PORT);
		props.put("mail.smtp.socketFactory.port", SMTP_PORT);
		props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.put("mail.smtp.socketFactory.fallback", "false");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(EMAIL_FROM_ADDRESS, "s2510ov97");
			}
		});

		session.setDebug(debug);

		Message msg = new MimeMessage(session);
		InternetAddress addressFrom = new InternetAddress(from);
		msg.setFrom(addressFrom);

		InternetAddress[] addressTo = new InternetAddress[recipients.length];
		for (int i = 0; i < recipients.length; i++) {
			addressTo[i] = new InternetAddress(recipients[i]);
		}
		msg.setRecipients(Message.RecipientType.TO, addressTo);

		msg.setSubject(subject);
		msg.setContent(message, "text/plain");
		Transport.send(msg);
	}

}