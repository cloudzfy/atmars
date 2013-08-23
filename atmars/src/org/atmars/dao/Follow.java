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
 * Follow entity. @author MyEclipse Persistence Tools
 */

public class Follow implements java.io.Serializable {

	// Fields

	private Integer followId;
	private User userByFollowingId;
	private User userByFollowedId;

	// Constructors

	/** default constructor */
	public Follow() {
	}

	/** full constructor */
	public Follow(User userByFollowingId, User userByFollowedId) {
		this.userByFollowingId = userByFollowingId;
		this.userByFollowedId = userByFollowedId;
	}

	// Property accessors

	public Integer getFollowId() {
		return this.followId;
	}

	public void setFollowId(Integer followId) {
		this.followId = followId;
	}

	public User getUserByFollowingId() {
		return this.userByFollowingId;
	}

	public void setUserByFollowingId(User userByFollowingId) {
		this.userByFollowingId = userByFollowingId;
	}

	public User getUserByFollowedId() {
		return this.userByFollowedId;
	}

	public void setUserByFollowedId(User userByFollowedId) {
		this.userByFollowedId = userByFollowedId;
	}
	public void MakeAllSetNull()
	{
		this.userByFollowedId.MakeAllSetNull();
		this.userByFollowingId.MakeAllSetNull();
	}
}