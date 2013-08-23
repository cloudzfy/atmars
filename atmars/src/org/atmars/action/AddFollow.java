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

public class AddFollow extends BaseAction {

	private static final long serialVersionUID = 1L;
	private int hisId;
	private boolean isSuccess;
	private String description;

	@Override
	public String execute() {
		InitAction();
		if (uService.addFollowing(currentUserFromSession.getUserId(), hisId)) {
			isSuccess = true;
			description = "success";
			return "success";
		} else {
			isSuccess = false;
			description = "already following";
		}
		ActionContext ctx = ActionContext.getContext();
		Map<String, Object> session = ctx.getSession();
		User u = uService.getUserInfoByUserId(currentUserFromSession.getUserId());
		session.put("user", u);
		return "success";
	}

	public int getHisId() {
		return hisId;
	}

	public void setHisId(int hisId) {
		this.hisId = hisId;
	}

	public boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
