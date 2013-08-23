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

package org.atmars.dao;

import java.util.Date;

/**
 * Favorite entity. @author MyEclipse Persistence Tools
 */

public class Favorite implements java.io.Serializable {

	// Fields

	private Integer favoriteId;
	private User user;
	private Message message;
	private Date time;

	// Constructors

	/** default constructor */
	public Favorite() {
	}

	/** minimal constructor */
	public Favorite(Date time) {
		this.time = time;
	}

	/** full constructor */
	public Favorite(User user, Message message, Date time) {
		this.user = user;
		this.message = message;
		this.time = time;
	}

	// Property accessors

	public Integer getFavoriteId() {
		return this.favoriteId;
	}

	public void setFavoriteId(Integer favoriteId) {
		this.favoriteId = favoriteId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Message getMessage() {
		return this.message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	public void MakeAllSetNull()
	{
		this.user.MakeAllSetNull();
		this.message.MakeAllSetNull();
	}
}