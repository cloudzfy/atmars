/*
 * atmars, An Implementation of a Micro Blog
 * Copyright (C) 2013, Cloudzfy
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.atmars.service.impls;

import java.util.Properties;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.mail.Authenticator;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailServiceMdp implements MessageListener {

	private String name = "atmars.com";
	private String pwd = "rjxyei2012";
	private String from = "atmars.com@gmail.com";
	@Override
	public void onMessage(Message message) {
		
		try {
			MapMessage mapMessage = (MapMessage)message;
			String to = mapMessage.getString("To");
			String subject = mapMessage.getString("Subject");
			String content = mapMessage.getString("Content");
			
			Properties props = System.getProperties();
			props.setProperty("mail.transport.protocol", "smtp");
			props.setProperty("mail.smtp.host", "smtp.gmail.com");
			props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.setProperty("mail.smtp.socketFactory.fallback", "false");
			props.setProperty("mail.smtp.port", "465");
			props.setProperty("mail.smtp.socketFactory.port", "465");
			props.setProperty("mail.smtp.auth", "true");
			
			Authenticator auth = new MailAuthenticator(name, pwd);
			Session session = Session.getDefaultInstance(props, auth);
			
			javax.mail.Message jMessage = new MimeMessage(session);
			jMessage.setFrom(new InternetAddress(from));
			jMessage.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
			jMessage.setSubject(subject);
			jMessage.setContent(content, "text/html;CHARSET=utf8");
			Transport.send(jMessage);
		} catch(Exception e){}
		
	}

}
