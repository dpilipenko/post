package com.dpilipenko.post.util;

import java.io.InputStream;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeMessage;

public class MessageNoMessageID extends MimeMessage {

	public MessageNoMessageID(Folder folder, InputStream is, int msgnum) throws MessagingException {
		super(folder, is, msgnum);
	}

	public MessageNoMessageID(Folder folder, int msgnum) {
		super(folder, msgnum);
	}

	public MessageNoMessageID(Folder folder, InternetHeaders headers, byte[] content, int msgnum) throws MessagingException {
		super(folder, headers, content, msgnum);
	}

	public MessageNoMessageID(MimeMessage arg0) throws MessagingException {
		super(arg0);
	}

	public MessageNoMessageID(Session session, InputStream is) throws MessagingException {
		super(session, is);
	}

	public MessageNoMessageID(Session session) {
		super(session);
	}
	
	protected void updateMessageID() throws MessagingException {
		setHeader("Message-ID", "");
    }
}
