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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

import sun.misc.BASE64Decoder;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SendMessageAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String text;
	private int messageid = -1;
	private String position;
	private String upload;
	private String uploadFileName;
	private String imageURI;

	// return lastPost;
	private Message lastPost = null;

	@Override
	public String execute() {
		InitAction();
		try {
			ImageUpload();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (imageURI != null) {
			lastPost = mService.sendMessage(
					currentUserFromSession.getUserId(), text, imageURI,
					position, messageid);
		} else {
			lastPost = mService.sendMessage(
					currentUserFromSession.getUserId(), text, null, position,
					messageid);
		}
		lastPost.MakeAllSetNull();
		lastPost.getUser().setPassword(null);
		lastPost.setTimeDescription(TimeUtils.getTimeDelay(lastPost.getTime()));
		lastPost.setText(ConvertPostUtils.replaceFace(lastPost.getText()));
		lastPost.setText(ConvertPostUtils.replaceAtMarkToHTML(lastPost.getText()));
		if(lastPost.getOriginal()!=null)
		{
			lastPost.getOriginal().MakeAllSetNull();
			lastPost.getUser().setPassword(null);
			lastPost.getOriginal().setText(ConvertPostUtils.replaceFace(lastPost.getOriginal().getText()));
			lastPost.getOriginal().setText(ConvertPostUtils.replaceAtMarkToHTML(lastPost.getOriginal().getText()));
			lastPost.getOriginal().setTimeDescription(TimeUtils.getTimeDelay(lastPost.getOriginal().getTime()));
		}
		User u = uService.getUserInfoByUserId(currentUserFromSession.getUserId());
		ActionContext.getContext().getSession().put("user", u);
		return "success";
	}

	private boolean ImageUpload() throws IOException {

		this.imageURI = null;

		if (upload.equals("null")) {
			return false;
		}
		String user_dir = "image\\"
				+ String.valueOf(currentUserFromSession.getUserId()) + "\\";
		String dir = webRootPath + user_dir;
		if (new File(dir).exists()) {

		} else {
			new File(dir).mkdir();
			System.out.println("user direction created\n");
		}
		int index = uploadFileName.lastIndexOf(".");
		String fileType = uploadFileName.substring(index + 1);
		System.out.println(fileType);
		String filename = String.valueOf(System.currentTimeMillis()) + "."
				+ fileType;
		System.out.println("file name is " + filename);
		String fn = dir + filename;
		BASE64Decoder decoder = new BASE64Decoder();
		String upload2 = new String();
		int index1 = this.upload.indexOf(',');
		upload2 = this.upload.substring(index1 + 1);
		byte[] b = decoder.decodeBuffer(upload2);

		FileOutputStream fos = new FileOutputStream(fn, true);
		fos.write(b);
		fos.flush();
		imageURI = "image/"
				+ String.valueOf(currentUserFromSession.getUserId()) + "/"
				+ filename;
		return true;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getMessageid() {
		return messageid;
	}

	public void setMessageid(int messageid) {
		this.messageid = messageid;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getUpload() {
		return upload;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public Message getLastPost() {
		return lastPost;
	}

	public void setLastPost(Message lastPost) {
		this.lastPost = lastPost;
	}
}
