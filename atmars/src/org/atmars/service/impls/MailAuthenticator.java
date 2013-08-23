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

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailAuthenticator extends Authenticator {

	private String username;
	private String password;
	
	public MailAuthenticator(String username,String password)
	{
		this.username = username;
		this.password = password;
	}
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication()
	{
		return new PasswordAuthentication(username,password);
	}
	
}