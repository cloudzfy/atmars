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

import java.util.concurrent.TimeUnit;
import javax.persistence.criteria.CriteriaBuilder.In;
import org.atmars.dao.Message;
import org.atmars.utils.*;

public class MobileSendMessageAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private int userId;
	private int forwordId=-1;
	private String text;
	private String ticket;
	private Message message=null;
	public String execute()
	{
		InitAction();
		if(uService.AuthenticUserWithTicket(userId, ticket))
		{
			message = mService.sendMessage(userId, text, null, null, forwordId);
			message.MakeAllSetNull();
			message.getUser().setPassword(null);
			message.setTimeDescription(TimeUtils.getTimeDelay(message.getTime()));
			message.setText(ConvertPostUtils.replaceFace(message.getText()));
			message.setText(ConvertPostUtils.replaceAtMarkToHTML(message.getText()));
			if(message.getOriginal()!=null)
			{
				message.getOriginal().MakeAllSetNull();
				message.getOriginal().getUser().setPassword(null);
				message.getOriginal().setText(ConvertPostUtils.replaceFace(message.getOriginal().getText()));
				message.getOriginal().setText(ConvertPostUtils.replaceAtMarkToHTML(message.getOriginal().getText()));
				message.getOriginal().setTimeDescription(TimeUtils.getTimeDelay(message.getOriginal().getTime()));
			}
		}
		return "success";
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getForwordId() {
		return forwordId;
	}
	public void setForwordId(int forwordId) {
		this.forwordId = forwordId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	
}
