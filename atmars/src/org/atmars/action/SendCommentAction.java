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

import org.atmars.dao.Comment;
import org.atmars.utils.TimeUtils;

import com.opensymphony.xwork2.ActionSupport;

public class SendCommentAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private int messageId;
	private String commentString;

	private Comment _publishedComment;

	@Override
	public String execute() {

		InitAction();

		_publishedComment = mService.sendComment(
				currentUserFromSession.getUserId(), messageId, commentString);
		_publishedComment.setTimeDescription(TimeUtils
				.getTimeDelay(_publishedComment.getTime()));
		_publishedComment.MakeAllSetNull();

		return "success";
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getCommentString() {
		return commentString;
	}

	public void setCommentString(String commentString) {
		this.commentString = commentString;
	}

	public Comment get_publishedComment() {
		return _publishedComment;
	}

	public void set_publishedComment(Comment _publishedComment) {
		this._publishedComment = _publishedComment;
	}

}
