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

package org.atmars.action;

import java.io.UnsupportedEncodingException;

import org.atmars.dao.User;

public class MobileLoginAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String password;
	private User user=null;
	private String ticket=null;
	
	@Override
	public String execute()
	{
		InitAction();
		if(uService.checkLogin(email, password))
		{
			user= uService.getUserInfoByEmail(email);
			try {
				ticket=uService.CalculateTicket(user);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		user.MakeAllSetNull();
		user.setPassword(null);
		return "success";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

}
