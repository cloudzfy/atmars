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
import java.util.HashSet;
import java.util.Set;

import org.atmars.utils.ConvertPostUtils;
import org.atmars.utils.TimeUtils;

/**
 * Message entity. @author MyEclipse Persistence Tools
 */

public class Message implements java.io.Serializable {

	// Fields
	private Message original=null;
	private boolean newestState=true;
	private String timeDescription;
	
	private Integer messageId;
	private User user;
	private String text;
	private String image;
	private Date time;
	private String position;
	private Integer sourceId;
	private Integer commentCount;
	private Integer forwardCount;
	private Set favorites = new HashSet(0);
	private Set comments = new HashSet(0);

	// Constructors

	/** default constructor */
	public Message() {
	}

	/** minimal constructor */
	public Message(String text, Integer sourceId) {
		this.text = text;
		this.sourceId = sourceId;
	}

	/** full constructor */
	public Message(User user, String text, String image, Date time,
			String position, Integer sourceId, Integer commentCount,
			Integer forwardCount, Set favorites, Set comments) {
		this.user = user;
		this.text = text;
		this.image = image;
		this.time = time;
		this.position = position;
		this.sourceId = sourceId;
		this.commentCount = commentCount;
		this.forwardCount = forwardCount;
		this.favorites = favorites;
		this.comments = comments;
	}

	// Property accessors

	public Integer getMessageId() {
		return this.messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Integer getSourceId() {
		return this.sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public Integer getCommentCount() {
		return this.commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getForwardCount() {
		return this.forwardCount;
	}

	public void setForwardCount(Integer forwardCount) {
		this.forwardCount = forwardCount;
	}

	public Set getFavorites() {
		return this.favorites;
	}

	public void setFavorites(Set favorites) {
		this.favorites = favorites;
	}

	public Set getComments() {
		return this.comments;
	}

	public void setComments(Set comments) {
		this.comments = comments;
	}

	public Message getOriginal() {
		return original;
	}

	public void setOriginal(Message original) {
		this.original = original;
	}

	public boolean getNewestState() {
		return newestState;
	}

	public void setNewestState(boolean newestState) {
		this.newestState = newestState;
	}

	public String getTimeDescription() {
		return timeDescription;
	}

	public void setTimeDescription(String timeDescription) {
		this.timeDescription = timeDescription;
	}
	public void MakeAllSetNull()
	{
		this.comments=null;
		this.favorites=null;
		if(this.original!=null)
			this.original.MakeAllSetNull();
		this.user.MakeAllSetNull();
	}
}