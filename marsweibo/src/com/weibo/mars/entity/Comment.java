package com.weibo.mars.entity;

import java.util.Date;

import com.weibo.mars.util.ConvertPostUtils;
import com.weibo.mars.util.TimeUtils;

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