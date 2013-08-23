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

import org.atmars.utils.ConvertPostUtils;
import org.atmars.utils.TimeUtils;

/**
 * Comment entity. @author MyEclipse Persistence Tools
 */

public class Comment implements java.io.Serializable {

	// Fields

	private Integer commentId;
	private User user;
	private Message message;
	private String text;
	private Date time;
	private String timeDescription=null;

	// Constructors

	/** default constructor */
	public Comment() {
	}

	/** minimal constructor */
	public Comment(String text, Date time) {
		this.text = text;
		this.time = time;
	}

	/** full constructor */
	public Comment(User user, Message message, String text, Date time) {
		this.user = user;
		this.message = message;
		this.text = text;
		this.time = time;
	}

	// Property accessors

	public Integer getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
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

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getTimeDescription() {
		return timeDescription;
	}

	public void setTimeDescription(String timeDescription) {
		this.timeDescription = timeDescription;
	}
	public void MakeAllSetNull()
	{
		this.message.MakeAllSetNull();
		this.user.MakeAllSetNull();
	}
}