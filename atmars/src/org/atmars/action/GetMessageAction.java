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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.atmars.dao.Message;
import org.atmars.dao.User;
import org.atmars.service.interfaces.MessageService;
import org.atmars.service.interfaces.UserService;
import org.atmars.utils.ConvertPostUtils;
import org.atmars.utils.TimeUtils;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GetMessageAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	// get weiboQiang messages;
	// json return input
	private List<Message> new_Messages = null;

	// get one newest message
	private int currentNewestMessageId = -1;
	private Message newestMessage_Now = null;

	public String weiboQiang() {
		InitAction();
		new_Messages = mService.findNewestMessagesFromAllUsers();
		for (Message m:new_Messages) {
			m.MakeAllSetNull();
			m.setTimeDescription(TimeUtils.getTimeDelay(m.getTime()));
			m.setText(ConvertPostUtils.replaceFace(m.getText()));
			m.setText(ConvertPostUtils.replaceAtMarkToHTML(m.getText()));
			m.getUser().setPassword(null);
		}
		return "success";
	}

	public String execute_getNewestMessage() {
		InitAction();
		List<Message> l = mService.findNewestMessagesFromAllUsers();

		// there is no message
		if (l == null || l.size() == 0) {
			newestMessage_Now = new Message();
			newestMessage_Now.setUser(new User());
			newestMessage_Now.MakeAllSetNull();
			newestMessage_Now.setNewestState(false);

			return "success";
		}

		Message m = (Message) l.get(0);

		// if there is a new message
		if (m.getMessageId() > currentNewestMessageId) {
			newestMessage_Now = m;
			newestMessage_Now.MakeAllSetNull();
			newestMessage_Now.getUser().setPassword(null);
			newestMessage_Now.setTimeDescription(TimeUtils
					.getTimeDelay(newestMessage_Now.getTime()));
			newestMessage_Now.setText(ConvertPostUtils
					.replaceFace(newestMessage_Now.getText()));
			newestMessage_Now.setText(ConvertPostUtils
					.replaceAtMarkToHTML(newestMessage_Now.getText()));
			newestMessage_Now.setNewestState(true);

			return "success";
		}
		// there is no newest message
		newestMessage_Now = new Message();
		newestMessage_Now.setUser(new User());
		newestMessage_Now.MakeAllSetNull();
		newestMessage_Now.setNewestState(false);

		return "success";
	}

	public List<Message> getNew_Messages() {
		return new_Messages;
	}

	public void setNew_Messages(List<Message> new_Messages) {
		this.new_Messages = new_Messages;
	}

	public int getCurrentNewestMessageId() {
		return currentNewestMessageId;
	}

	public void setCurrentNewestMessageId(int currentNewestMessageId) {
		this.currentNewestMessageId = currentNewestMessageId;
	}

	public Message getNewestMessage_Now() {
		return newestMessage_Now;
	}

	public void setNewestMessage_Now(Message newestMessage_Now) {
		this.newestMessage_Now = newestMessage_Now;
	}

}
