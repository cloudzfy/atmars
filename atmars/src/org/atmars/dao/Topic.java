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

/**
 * Topic entity. @author MyEclipse Persistence Tools
 */

public class Topic implements java.io.Serializable {

	// Fields

	private Integer topicId;
	private String title;
	private String content;
	private Integer count;

	// Constructors

	/** default constructor */
	public Topic() {
	}

	/** minimal constructor */
	public Topic(String title, Integer count) {
		this.title = title;
		this.count = count;
	}

	/** full constructor */
	public Topic(String title, String content, Integer count) {
		this.title = title;
		this.content = content;
		this.count = count;
	}

	// Property accessors

	public Integer getTopicId() {
		return this.topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}