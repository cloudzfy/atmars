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

import java.util.Map;

import org.atmars.dao.User;

import com.opensymphony.xwork2.ActionContext;

public class ShowJspPagesAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private int hisId;
	private User hisUser;

	public String ShowHomePage() {
		InitAction();

		ActionContext ctx = ActionContext.getContext();
		Map<String, Object> session = ctx.getSession();
		session.put("user",
				uService.getUserInfoByUserId(currentUserFromSession.getUserId()));
		return "success";
	}

	public String ShowUserPage() {
		InitAction();

		hisUser = uService.getUserInfoByUserId(hisId);

		return "success";
	}

	public int getHisId() {
		return hisId;
	}

	public void setHisId(int hisId) {
		this.hisId = hisId;
	}

	public User getHisUser() {
		return hisUser;
	}

	public void setHisUser(User hisUser) {
		this.hisUser = hisUser;
	}

}
