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

import java.util.List;
import org.atmars.dao.Message;
import org.atmars.utils.ConvertPostUtils;
import org.atmars.utils.TimeUtils;

public class MobileGetMyMessagesAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private int oldestMessageId=-1;
	private List<Message> myMessages;
	private int userId;
	private String ticket;
	
	@Override
	public String execute() {
		InitAction();

		if(uService.AuthenticUserWithTicket(userId, ticket))
		{
			myMessages = mService.GetMyMessages(
					userId, oldestMessageId);
			for (Message m : myMessages) {
				m.MakeAllSetNull();
				m.getUser().setPassword(null);
				m.setTimeDescription(TimeUtils.getTimeDelay(m.getTime()));
				m.setText(ConvertPostUtils.replaceFace(m.getText()));
				m.setText(ConvertPostUtils.replaceAtMarkToHTML(m.getText()));
				if(m.getOriginal()!=null)
				{
					m.getOriginal().MakeAllSetNull();
					m.getOriginal().getUser().setPassword(null);
					m.getOriginal().setText(ConvertPostUtils.replaceFace(m.getOriginal().getText()));
					m.getOriginal().setText(ConvertPostUtils.replaceAtMarkToHTML(m.getOriginal().getText()));
					m.getOriginal().setTimeDescription(TimeUtils.getTimeDelay(m.getOriginal().getTime()));
				}
			}
			return "success";
		}
		myMessages=null;
		return "success";
	}

	public int getOldestMessageId() {
		return oldestMessageId;
	}

	public void setOldestMessageId(int oldestMessageId) {
		this.oldestMessageId = oldestMessageId;
	}

	public List<Message> getMyMessages() {
		return myMessages;
	}

	public void setMyMessages(List<Message> myMessages) {
		this.myMessages = myMessages;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
}
