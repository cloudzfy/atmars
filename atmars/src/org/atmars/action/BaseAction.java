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

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.atmars.dao.User;
import org.atmars.service.impls.Jmail;
import org.atmars.service.impls.MailServiceMdp;
import org.atmars.service.interfaces.MessageService;
import org.atmars.service.interfaces.UserService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	protected String webRootPath = null;
	protected MessageService mService = null;
	protected UserService uService = null;
	protected Jmail jMail=null;
	protected MailServiceMdp mailServiceMdp=null;
	protected User currentUserFromSession = null;

	protected void InitAction() {
		webRootPath = ServletActionContext.getServletContext().getRealPath("/");
		String xmlPath=webRootPath
				+ "WEB-INF\\applicationContext.xml";
		
		ApplicationContext appContext = new FileSystemXmlApplicationContext(xmlPath);
		//Resource res = new FileSystemResource(webRootPath
		//		+ "WEB-INF\\applicationContext.xml");
		//XmlBeanFactory factory = new XmlBeanFactory(res);
		BeanFactory factory = appContext;
		mService = (MessageService) factory.getBean("messageService");
		uService = (UserService) factory.getBean("userService");
		jMail = (Jmail) factory.getBean("jmail");
		mailServiceMdp = (MailServiceMdp) factory.getBean("mailServiceMdp");
		factory.getBean("listnerContainer");
		ActionContext ctx = ActionContext.getContext();
		Map<String, Object> session = ctx.getSession();
		currentUserFromSession = (User) session.get("user");
	}
}
