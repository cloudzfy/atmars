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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import org.atmars.dao.User;
import com.opensymphony.xwork2.ActionContext;

public class UserAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	private String imageURI;

	private String email;
	private String password;
	private String nickname;
	private boolean gender;

	private int hisId;

	public String execute_showLoginPage() {
		super.InitAction();
		ActionContext ctx = ActionContext.getContext();
		Map session = ctx.getSession();
		session.put("userList", uService.GetNewRegisterUsers());
		return "success";
	}

	public String performRegister() throws IOException {

		super.InitAction();

		User u = new User();
		boolean avalible = true;
		User alreadyExistUser = uService.getUserInfoByEmail(email);
		if (alreadyExistUser != null) {
			avalible = false;
		}
		alreadyExistUser = uService.getUserInfoByNickname(nickname);
		if (alreadyExistUser != null) {
			avalible = false;
		}
		if (avalible) {
			uService.register(email, password, nickname, gender);
			u =  uService.getUserInfoByEmail(this.email);
			u.setImage("image\\default.jpg");
			System.out.println("the user get");
			System.out.println(u.getUserId());
			if (upload != null) {
				System.out.println(this.uploadContentType);
				String user_dir = "image\\" + String.valueOf(u.getUserId())
						+ "\\";
				String dir = webRootPath + user_dir;
				if (new File(dir).mkdir()) {
					System.out.println("user direction created\n");
				}
				int index = uploadFileName.lastIndexOf(".");
				String fileType = uploadFileName.substring(index + 1);
				System.out.println(fileType);
				String filename = String.valueOf(System.currentTimeMillis())
						+ "." + fileType;
				System.out.println("file name is " + filename);
				String fn = dir + filename;
				FileOutputStream fos = new FileOutputStream(fn);
				InputStream is = new FileInputStream(upload);
				byte[] buffer = new byte[8192];
				int count = 0;
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count);
				}
				fos.close();
				imageURI = "image/" + String.valueOf(u.getUserId()) + "/"
						+ filename;
				u.setImage(imageURI);

			} else {
				u.setImage("image/default.png");
			}
			u.setConfirm(false);
			uService.updateUserInfo(u);

			jMail.sendEmail(u.getEmail(), u.getNickname(), u.getTime());

			return "wait_confirm";
		}
		return "error";
	}

	public String performLogin() {

		super.InitAction();

		ActionContext ctx = ActionContext.getContext();
		Map session = ctx.getSession();

		if (uService.checkLogin(this.email, this.password)) {
			User u =  uService.getUserInfoByEmail(this.email);
			if (u.getConfirm()) {
				session.put("user", u);
				return "login_success";
			} else {
				session.put("error",
						"this account is waiting for email confirm");
			}
		} else {
			session.put("error",
					"Sorry, account does not exist\nor password is wrong");
		}
		return "login_fail";
	}

	public String AddFollow() {

		super.InitAction();

		uService
				.addFollowing(currentUserFromSession.getUserId(), this.hisId);

		return "add_follow_success";

	}

	public String getEmail() {
		return email;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getImageURI() {
		return imageURI;
	}

	public void setImageURI(String imageURI) {
		this.imageURI = imageURI;
	}

	public int getHisId() {
		return hisId;
	}

	public void setHisId(int hisId) {
		this.hisId = hisId;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}
}
