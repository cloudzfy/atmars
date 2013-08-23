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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.atmars.dao.User;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.opensymphony.xwork2.ActionContext;

public class UserDetailAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private List<User> search = null;
	private String searchString = "";

	private List<User> followings = null;

	private List<User> followers = null;

	public String search() {
		InitAction();
		search = new ArrayList<User>();
		if (searchString == null || searchString.isEmpty())
		{

		} 
		else 
		{
			if (currentUserFromSession == null)
			{
				search = uService.SearchUser(searchString, null);
			} 
			else 
			{
				search = uService.SearchUser(searchString,
						currentUserFromSession.getUserId());
			}
		}
		ActionContext.getContext().getSession().put("search", this.search);
		return "success";
	}

	public String myFollowings() {
		InitAction();
		followings = new ArrayList<User>();
		followings = uService.GetFollowings(currentUserFromSession.getUserId());
		ActionContext.getContext().getSession()
				.put("followings", this.followings);
		return "success";
	}

	public String myFollowers() {
		InitAction();
		followers = new ArrayList<User>();
		followers = uService.GetFollowers(currentUserFromSession.getUserId());
		ActionContext.getContext().getSession()
				.put("followers", this.followers);
		return "success";
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

}
