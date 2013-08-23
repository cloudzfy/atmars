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

import java.util.Date;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import java.io.UnsupportedEncodingException;


import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class Jmail {

	private String serverURL = "http://localhost:8080/atmars/";
	private String logo_img = "weibo-img/logo.png";
	private String poster_img = "weibo-img/poster.png";
	
	public String sendEmail(String email, String nickname, Date date) throws UnsupportedEncodingException {
		
		String plain = email + nickname + date.getTime();
		BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
		String encrypted = passwordEncryptor.encryptPassword(plain);
		String urlEncodedString = java.net.URLEncoder.encode(encrypted, "UTF-8");
		final String to = email;
		final String subject = "AtMars Microblog New User Register Comfirmation Email";
		final String content = "<div style='width:713px; background-color:#A1E0E9; padding-top:30px; padding-bottom:30px; margin: 0 auto;'><img src='" + serverURL + logo_img + "' width='160' /><img src='" + serverURL + poster_img + "' width='100%' /><div style='background-color:#FFFFFF; border-radius:3px; box-shadow:0 1px 3px rgba(0,0,0,0.25); border:1px solid #CCC; padding:20px 20px 20px 20px; margin-left:30px; margin-right:30px; margin-top:30px;'><p><b>Welcome to AtMars MicroBlog</b></p><p>Thank you for registering AtMars MicroBlog.</p><p>To comfirm the email address you provided during registration. please click the following link in 24 hours:</p><p><a href='" + serverURL + "checkEmail?email=" + email + "&ticket=" + urlEncodedString + "'>" + serverURL + "checkEmail?email=" + email + "&ticket=" + urlEncodedString + "</a></p><p>If this link does not work, copy and paste the link into your browser.</p></div></div>";
		
		jmsTemplate.send(destination, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				MapMessage message = session.createMapMessage();
				
				message.setString("To", to);
				message.setString("Subject", subject);
				message.setString("Content", content);
				
				return message;
			}
			
		});
		return encrypted;
	}
	
	public boolean checkLink(String email, String nickname, Date date, String encrypted) throws UnsupportedEncodingException {
		
		String plain = email + nickname + date.getTime();
		
		BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
		if(!passwordEncryptor.checkPassword(plain, encrypted))
		{
			return false;
		}
		Date now = new Date();
		if(now.getTime() - now.getTime() > 86400000)
		{
			return false;
		}
		final String subject = "Your New Account in AtMars Microblog has been Activated";
		final String content = "<div style='width:713px; background-color:#A1E0E9; padding-top:30px; padding-bottom:30px; margin: 0 auto;'><img src='" + serverURL + logo_img + "' width='160' /><img src='" + serverURL + poster_img + "' width='100%' /><div style='background-color:#FFFFFF; border-radius:3px; box-shadow:0 1px 3px rgba(0,0,0,0.25); border:1px solid #CCC; padding:20px 20px 20px 20px; margin-left:30px; margin-right:30px; margin-top:30px;'><p><b>Welcome to AtMars MicroBlog</b></p><p>Congratulations to you! Your email account " + email + " is activated. Thank you for registering AtMars MicroBlog.</p><p>Try to use AtMars to follow your friends:</p><p>&nbsp;&nbsp;&nbsp;&nbsp;<a href='" + serverURL + "'>" + serverURL + "</a></p><p>We are glad to meet you in AtMars MicroBlog.</p></div></div>";
		final String to = email;
		
		jmsTemplate.send(destination, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				MapMessage message = session.createMapMessage();
				
				message.setString("To", to);
				message.setString("Subject", subject);
				message.setString("Content", content);
				
				return message;
			}
			
		});
		return true;
	}
	
	private JmsTemplate jmsTemplate;
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
	private Destination destination;
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
}
