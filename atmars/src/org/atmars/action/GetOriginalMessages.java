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

public class GetOriginalMessages extends BaseAction {

	private int userId = -1;
	private int cursor = -1;
	private List<Message> originalList;

	@Override
	public String execute() {
		InitAction();
		if (userId == -1) {
			userId = currentUserFromSession.getUserId();
		}
		originalList = mService.GetOriginalMessages(userId, cursor);
		for (Message m : originalList) {
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCursor() {
		return cursor;
	}

	public void setCursor(int cursor) {
		this.cursor = cursor;
	}

	public List<Message> getOriginalList() {
		return originalList;
	}

	public void setOriginalList(List<Message> originalList) {
		this.originalList = originalList;
	}

}
