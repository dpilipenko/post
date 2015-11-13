package com.dpilipenko.post;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Message.RecipientType;

import com.dpilipenko.post.util.Conf;
import com.dpilipenko.post.util.MessageNoMessageID;

public class Post {

	public static void main(String[] args) throws MessagingException {
		log("Start POST");

		final String to = "TOADDRESS@EMAIL.COM";
		final String subject = "HELLO";
		final String body = "IS THERE ANYBODY OUT THERE?";
		
		email(to, subject, body);
		
		log("End POST");
	}
	
	public static void email(String to, String subject, String body) throws MessagingException {
		Envelope envelope = createEnvelope(new String[] {to}, subject, body);
		email(envelope);
	}

	public static void email(Envelope envelope) throws MessagingException {
		Conf conf = Conf.fetchInstance();
		sendMessage(envelope, conf);
	}
	
	protected static void sendMessage(Envelope envelope, Conf conf) throws MessagingException {
		final String[] to = envelope.getToAddresses();
		final Message m = envelope.getMessage();
		final String subject = m.getSubject();
		final String body = m.getBody();
		
		try {
			Session session = getSession(conf);
			MimeMessage message = new MessageNoMessageID(session);
			message.setFrom(new InternetAddress(conf.getFrom()));
			for (String toAddress : to) { message.addRecipient(RecipientType.TO, new InternetAddress(toAddress)); }
			message.setSubject(subject);
			message.setText(body);
			Transport.send(message);
			log("Message Sent");
		} catch (MessagingException mex) {
			log(mex.getStackTrace().toString());
			throw mex;
		}
	}

	protected static Session getSession(final Conf c) {
		Properties properties = System.getProperties();
		properties.put("mail.transport.protocol", c.getProtocol());
		properties.setProperty("mail.smtp.host", c.getHost());
		properties.setProperty("mail.smtp.port", c.getPort());
		properties.setProperty("mail.smtp.starttls.enable", "true");
		Session session;
		if ("true".equalsIgnoreCase(c.getAuth())) {
			properties.setProperty("mail.smtp.auth", c.getAuth());
			Authenticator authenticator = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
			        return new PasswordAuthentication(c.getUsername(), c.getPassword());
			    }
			};
			session = Session.getDefaultInstance(properties, authenticator);
		} else {
			session = Session.getDefaultInstance(properties);
		}
		return session;
	}
	
	private static Envelope createEnvelope(String[] to, String subject, String body) {
		Message m = new Message();
		m.setSubject(subject);
		m.setBody(body);
		Envelope e = new Envelope();
		e.setMessage(m);
		e.setToAddresses(to);
		return e;
	}
	
	private static void log(String log) {
		System.out.println(log);
	}

}
