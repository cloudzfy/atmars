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

import org.atmars.dao.Comment;
import org.atmars.utils.*;

public class GetCommentAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private int messageId;
	private int cursor = -1;

	private List<Comment> comments;

	@Override
	public String execute() {
		InitAction();

		comments = mService.getComment(messageId, cursor);
		for (Comment c : comments) {
			c.MakeAllSetNull();
			c.setTimeDescription(TimeUtils.getTimeDelay(c.getTime()));
		}

		return "success";
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public int getCursor() {
		return cursor;
	}

	public void setCursor(int cursor) {
		this.cursor = cursor;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}
