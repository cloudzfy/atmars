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

import org.atmars.dao.Message;
import org.atmars.utils.ConvertPostUtils;
import org.atmars.utils.TimeUtils;

public class GetMyMessageAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private int oldest_message_id;
	private List<Message> myMessages;

	@Override
	public String execute() {
		InitAction();

		myMessages = mService.GetMyMessages(
				currentUserFromSession.getUserId(), oldest_message_id);
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

	public int getOldest_message_id() {
		return oldest_message_id;
	}

	public void setOldest_message_id(int oldest_message_id) {
		this.oldest_message_id = oldest_message_id;
	}

	public List<Message> getMyMessages() {
		return myMessages;
	}

	public void setMyMessages(List<Message> myMessages) {
		this.myMessages = myMessages;
	}

}
