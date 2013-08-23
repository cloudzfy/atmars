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
import java.util.Map;
import org.atmars.dao.User;
import org.springframework.dao.DataAccessException;
import com.opensymphony.xwork2.ActionContext;

public class CheckEmailAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String email;
	private String ticket;

	@Override
	public String execute() {
		InitAction();
		User u =  uService.getUserInfoByEmail(email);

		try {
			if (jMail.checkLink(u.getEmail(), u.getNickname(), u.getTime(),
					ticket)) {
				u.setConfirm(true);
				uService.updateUserInfo(u);
				Map session = ActionContext.getContext().getSession();
				session.put("user", u);
				return "confirm_success";
			} else {
				uService.deleteUser(u);
				return "confirm_fail";
			}
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

}
