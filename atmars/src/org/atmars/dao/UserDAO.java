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

package org.atmars.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for User
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see org.atmars.dao.User
 * @author MyEclipse Persistence Tools
 */

public class UserDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(UserDAO.class);
	// property constants
	public static final String EMAIL = "email";
	public static final String PASSWORD = "password";
	public static final String NICKNAME = "nickname";
	public static final String GENDER = "gender";
	public static final String IMAGE = "image";
	public static final String PRIORITY = "priority";
	public static final String FOLLOWER_COUNT = "followerCount";
	public static final String FOLLOWING_COUNT = "followingCount";
	public static final String POST_COUNT = "postCount";
	public static final String CONFIRM = "confirm";

	protected void initDao() {
		// do nothing
	}

	public void save(User transientInstance) {
		log.debug("saving User instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(User persistentInstance) {
		log.debug("deleting User instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public User findById(java.lang.Integer id) {
		log.debug("getting User instance with id: " + id);
		try {
			User instance = (User) getHibernateTemplate().get(
					"org.atmars.dao.User", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(User instance) {
		log.debug("finding User instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding User instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from User as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List findByNickname(Object nickname) {
		return findByProperty(NICKNAME, nickname);
	}

	public List findByGender(Object gender) {
		return findByProperty(GENDER, gender);
	}

	public List findByImage(Object image) {
		return findByProperty(IMAGE, image);
	}

	public List findByPriority(Object priority) {
		return findByProperty(PRIORITY, priority);
	}

	public List findByFollowerCount(Object followerCount) {
		return findByProperty(FOLLOWER_COUNT, followerCount);
	}

	public List findByFollowingCount(Object followingCount) {
		return findByProperty(FOLLOWING_COUNT, followingCount);
	}

	public List findByPostCount(Object postCount) {
		return findByProperty(POST_COUNT, postCount);
	}

	public List findByConfirm(Object confirm) {
		return findByProperty(CONFIRM, confirm);
	}

	public List findAll() {
		log.debug("finding all User instances");
		try {
			String queryString = "from User";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public User merge(User detachedInstance) {
		log.debug("merging User instance");
		try {
			User result = (User) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(User instance) {
		log.debug("attaching dirty User instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(User instance) {
		log.debug("attaching clean User instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UserDAO getFromApplicationContext(ApplicationContext ctx) {
		return (UserDAO) ctx.getBean("UserDAO");
	}
}