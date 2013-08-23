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

package org.atmars.service.interfaces;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.atmars.dao.User;

public interface UserService {
	public void register(String email, String password, String nickname, boolean gender);
	public boolean checkLogin(String email, String password);
	public String CalculateTicket(User user) throws UnsupportedEncodingException;
	public boolean AuthenticUserWithTicket(int userId,String ticket);
	public boolean isAdministrator(String username);
	public boolean addFollowing(Integer myid, Integer hisid);
	public boolean alreadyFollowing(int me,int him);
	public void updateUserInfo(User instance);
	public User getUserInfoByUserId(Integer userId);
	public User getUserInfoByEmail(String email);
	public User getUserInfoByNickname(String nickname);
	public void deleteUser(User user);
	public List<User> GetNewRegisterUsers();
	public List<User> SearchUser(String searchString,Integer currentUserId);
	public List<User> GetFollowings(int userId);
	public List<User> GetFollowers(int userId);
}
