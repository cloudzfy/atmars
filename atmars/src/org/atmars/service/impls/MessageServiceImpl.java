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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.atmars.dao.*;
import org.atmars.service.interfaces.MessageService;
import org.hibernate.classic.Session;
import org.hibernate.Query;

public class MessageServiceImpl implements MessageService {

	@Override
	public Message sendMessage(Integer userid, String text, String image,
			String position, int forwardId) {
		Message message = new Message();
		message.setUser(userDAO.findById(userid));
		message.setImage(image);
		message.setPosition(position);
		message.setCommentCount(0);
		message.setForwardCount(0);
		message.setTime(new Date());

		if (forwardId == -1) {
			message.setText(text);
			message.setSourceId(-1);
		} else {
			Message m = messageDAO.findById(forwardId);
			m.setUser(userDAO.findById(m.getUser().getUserId()));
			if (m.getSourceId() == -1) {
				message.setText(text);
				message.setSourceId(forwardId);
			} else {
				String text2 = text + "//" + "@" + m.getUser().getNickname()
						+ ":" + m.getText();
				message.setText(text2);
				message.setSourceId(m.getSourceId());
			}
			m.setForwardCount(m.getForwardCount() + 1);
			messageDAO.attachDirty(m);
		}
		messageDAO.save(message);
		User u = userDAO.findById(userid);
		u.setPostCount(u.getPostCount() + 1);
		userDAO.attachDirty(u);
		// topic
		int a, b = 0;
		while (true) {
			a = text.indexOf('#', b);
			b = text.indexOf('#', a + 1);
			if (a != -1 && b != -1) {
				String title = text.substring(a + 1, b);
				List<Topic> topicList = topicDAO.findByTitle(title);
				if (topicList.size() == 0) {
					Topic topic = new Topic();
					topic.setTitle(title);
					topicDAO.save(topic);
				} else {
					Topic topic = (Topic) topicList.get(0);
					topic.setCount(topic.getCount() + 1);
					topicDAO.getHibernateTemplate().update(topic);
				}
				b++;
			} else {
				break;
			}
		}
		if(message.getSourceId()!=-1)
		{
			message.setOriginal(messageDAO.findById(message.getSourceId()));
			message.getOriginal().setUser(userDAO.findById(message.getOriginal().getUser().getUserId()));
		}
			
		return message;
	}

	@Override
	public Message getSingleMessage(Integer id) {
		return messageDAO.findById(id);
	}

	@Override
	public List<Message> GetOriginalMessages(int userId, int cursor) {
		List<Message> originalMessages = new ArrayList<Message>();

		String queryString = "from Message m where m.user.userId=" + userId;
		if (cursor == -1)
			queryString += " order by m.messageId desc";
		else
			queryString += " and m.messageId<" + cursor
					+ " order by m.messageId desc";
		Query q = messageDAO.getHibernateTemplate().getSessionFactory()
				.openSession().createQuery(queryString);
		q.setMaxResults(10);
		List l = q.list();
		for (Object obj : l) {
			Message m = (Message) obj;
			if (m.getSourceId() != -1) {
				m.setOriginal(getSingleMessage(m.getSourceId()));
				m.getOriginal().setUser(userDAO.findById(m.getOriginal().getUser().getUserId()));
			}
			originalMessages.add(m);
		}
		return originalMessages;
	}

	@Override
	public Comment sendComment(Integer userid, Integer messageid, String text) {

		Comment comment = new Comment();
		comment.setUser(userDAO.findById(userid));
		Message m = messageDAO.findById(messageid);
		m.setUser(userDAO.findById(m.getUser().getUserId()));
		comment.setMessage(m);
		comment.setText(text);
		comment.setTime(new Date());
		commentDAO.save(comment);

		m.setCommentCount(m.getCommentCount() + 1);
		messageDAO.attachDirty(m);

		return comment;
	}

	@Override
	public List<Comment> getComment(int messageId, int cursor) {
		String queryString = null;

		if (cursor == -1) {
			queryString = "from Comment c where c.message.messageId="
					+ String.valueOf(messageId);
		} else {
			queryString = "from Comment c where c.message.messageId="
					+ String.valueOf(messageId) + " and c.commentId<"
					+ String.valueOf(cursor);
		}
		queryString += " order by c.time desc";
		Session s = commentDAO.getHibernateTemplate().getSessionFactory()
				.openSession();
		Query q = s.createQuery(queryString);
		q.setMaxResults(10);
		return q.list();
	}

	@Override
	public List findNewestMessagesFromAllUsers() {
		Session s = messageDAO.getSessionFactory().openSession();
		String hql = "from Message m where m.sourceId=-1 order by m.messageId desc";
		Query q = s.createQuery(hql);
		q.setMaxResults(6);
		return q.list();
	}

	@Override
	public List<Message> GetMyMessages(int userId, int cursor) {
		String queryString = "select distinct m from Message as m left join m.user as u left join u.followsForFollowedId as f"
				+ " where (f.userByFollowingId.userId="
				+ userId
				+ " or u.userId=" + userId + ")";
		if (cursor != -1) {
			queryString += " and (m.messageId<" + cursor + ")";
		}
		queryString += "order by m.messageId desc";
		Query q = messageDAO.getHibernateTemplate().getSessionFactory()
				.openSession().createQuery(queryString);
		q.setMaxResults(10);
		List l = q.list();
		List<Message> messageList = new ArrayList<Message>();
		for (Object obj : l) {
			Message m = (Message) obj;
			if (m.getSourceId() != -1) {
				m.setOriginal(getSingleMessage(m.getSourceId()));
				m.getOriginal().setUser(userDAO.findById(m.getOriginal().getUser().getUserId()));
			}
			messageList.add(m);
		}
		return messageList;
	}

	private MessageDAO messageDAO;
	private UserDAO userDAO;
	private CommentDAO commentDAO;
	private FavoriteDAO favoriteDAO;
	private TopicDAO topicDAO;

	public MessageServiceImpl(MessageDAO messageDAO, CommentDAO commentDAO,
			UserDAO userDAO, FavoriteDAO favoriteDAO, TopicDAO topicDAO) {
		this.messageDAO = messageDAO;
		this.commentDAO = commentDAO;
		this.userDAO = userDAO;
		this.favoriteDAO = favoriteDAO;
		this.topicDAO = topicDAO;
	}

}
