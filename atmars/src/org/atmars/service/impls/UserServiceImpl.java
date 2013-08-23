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

package org.atmars.service.impls;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.atmars.dao.Follow;
import org.atmars.dao.FollowDAO;
import org.atmars.dao.User;
import org.atmars.dao.UserDAO;
import org.atmars.service.interfaces.UserService;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.jasypt.util.password.BasicPasswordEncryptor;

public class UserServiceImpl implements UserService {

	@Override
	public void updateUserInfo(User instance) {
		userDAO.attachDirty(instance);
	}

	@Override
	public User getUserInfoByEmail(String email) {
		List l = userDAO.findByProperty("email", email);
		if (l == null || l.size() == 0)
			return null;
		return (User) l.get(0);
	}

	@Override
	public void register(String email, String password, String nickname,
			boolean gender) {
		BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
		String encryptedPassword = passwordEncryptor.encryptPassword(password);
		User user = new User();
		user.setEmail(email);
		user.setPassword(encryptedPassword);
		user.setNickname(nickname);
		user.setGender(gender);

		user.setFollowerCount(0);
		user.setFollowingCount(0);
		user.setPostCount(0);
		user.setTime(new Date());

		userDAO.save(user);
	}

	@Override
	public boolean checkLogin(String email, String password) {
		BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
		List l = userDAO.findByEmail(email);
		if (l == null || l.size() == 0) {
			return false;
		}
		User user = (User) userDAO.findByEmail(email).get(0);
		if (passwordEncryptor.checkPassword(password, user.getPassword())) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isAdministrator(String username) {
		User user = (User) userDAO.findByEmail(username).get(0);
		if (user.getPriority() == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean addFollowing(Integer myid, Integer hisid) {
		User me = userDAO.findById(myid);
		User him = userDAO.findById(hisid);
		Follow follow = new Follow();
		follow.setUserByFollowingId(me);
		follow.setUserByFollowedId(him);
		String queryString = "from Follow f where f.userByFollowingId.userId="
				+ myid + " and f.userByFollowedId.userId=" + hisid;
		List l = followDAO.getHibernateTemplate().getSessionFactory()
				.openSession().createQuery(queryString).list();
		if (l == null || l.size() == 0) {
			followDAO.save(follow);
			me.setFollowingCount(me.getFollowingCount() + 1);
			userDAO.attachDirty(me);
			him.setFollowerCount(him.getFollowerCount() + 1);
			userDAO.attachDirty(him);
			return true;
		}
		return false;
	}

	@Override
	public User getUserInfoByUserId(Integer userId) {
		User user = userDAO.findById(userId);
		return user;
	}

	@Override
	public List<User> GetNewRegisterUsers() {
		List<User> l = new ArrayList<User>();
		Session s = userDAO.getHibernateTemplate().getSessionFactory()
				.openSession();
		String queryString = "from User u order by u.time desc";
		Query q = s.createQuery(queryString);
		q.setMaxResults(9);
		for (Object obj : q.list()) {
			l.add((User) obj);
		}
		return l;
	}

	@Override
	public boolean alreadyFollowing(int me, int him) {
		String queryString = "from Follow f where "
				+ "f.userByFollowingId.userId=" + me
				+ " and f.userByFollowedId.userId=" + him;
		Session s = followDAO.getHibernateTemplate().getSessionFactory()
				.openSession();
		List l = s.createQuery(queryString).list();
		if (l == null || l.size() == 0) {
			return false;
		}
		return true;
	}

	@Override
	public User getUserInfoByNickname(String nickname) {
		List l = userDAO.findByProperty("nickname", nickname);
		if (l == null || l.size() == 0)
			return null;
		return (User) l.get(0);
	}

	@Override
	public List<User> SearchUser(String searchString, Integer currentUserId) {
		if (searchString == null || searchString.isEmpty())
			return null;
		List<User> resultUserList = new ArrayList<User>();
		String queryString = "from User u where u.nickname like '%"
				+ searchString + "%'";
		List l = userDAO.getHibernateTemplate().getSessionFactory()
				.openSession().createQuery(queryString).list();
		if (currentUserId != null) 
		{
			for (Object obj : l) 
			{
				User u = (User) obj;
				if (alreadyFollowing(currentUserId, u.getUserId()))
					u.setAlreadyFollowing(true);
				if (currentUserId.intValue() == u.getUserId().intValue())
					u.setIsSelf(true);
				resultUserList.add(u);
			}
		}
		else 
		{
			for (Object obj : l) 
			{
				User u = (User) obj;
				resultUserList.add(u);
			}
		}
		return resultUserList;
	}

	@Override
	public List<User> GetFollowings(int userId) {
		List <User> resultUserList = new ArrayList<User>();
		String queryString="select u from User as u left join u.followsForFollowedId as f where f.userByFollowingId.userId="+userId;
		List l = userDAO.getHibernateTemplate().getSessionFactory().openSession().createQuery(queryString).list();
		for(Object obj:l)
		{
			User u = (User) obj;
			resultUserList.add(u);
		}
		return resultUserList;
	}

	@Override
	public List<User> GetFollowers(int userId) {
		List <User> resultUserList = new ArrayList<User>();
		String queryString="select u from User as u left join u.followsForFollowingId as f where f.userByFollowedId.userId="+userId;
		List l = userDAO.getHibernateTemplate().getSessionFactory().openSession().createQuery(queryString).list();
		for(Object obj:l)
		{
			User u = (User) obj;
			resultUserList.add(u);
		}
		return resultUserList;
	}
	@Override
	public void deleteUser(User user) {
		userDAO.getHibernateTemplate().delete(user);
	}
	

	@Override
	public String CalculateTicket(User user) throws UnsupportedEncodingException {
		String plain = user.getEmail()+user.getNickname()+user.getTime().getTime();
		BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
		String encrypted = passwordEncryptor.encryptPassword(plain);
		String urlEncodedString = java.net.URLEncoder.encode(encrypted, "UTF-8");
		return urlEncodedString;
	}
	
	@Override
	public boolean AuthenticUserWithTicket(int userId, String ticket) {
		User u = userDAO.findById(userId);
		String plainPassword = u.getEmail()+u.getNickname()+u.getTime().getTime();
		BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
		return passwordEncryptor.checkPassword(plainPassword, ticket);
	}


	
	
	private UserDAO userDAO;
	private FollowDAO followDAO;

	public UserServiceImpl(UserDAO userDAO, FollowDAO followDAO) {
		this.userDAO = userDAO;
		this.followDAO = followDAO;
	}

	
	

}
