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
import java.util.HashSet;
import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String email;
	private String password;
	private String nickname;
	private boolean gender;
	private String image;
	private Integer priority;
	private Date time;
	private Integer followerCount;
	private Integer followingCount;
	private Integer postCount;
	private boolean confirm;
	
	private boolean alreadyFollowing=false;
	private boolean isSelf=false;
	
	private Set messages = new HashSet(0);
	private Set followsForFollowingId = new HashSet(0);
	private Set comments = new HashSet(0);
	private Set favorites = new HashSet(0);
	private Set followsForFollowedId = new HashSet(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String email, String password, String nickname, boolean gender,
			Integer followerCount, Integer followingCount, Integer postCount,
			boolean confirm) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.gender = gender;
		this.followerCount = followerCount;
		this.followingCount = followingCount;
		this.postCount = postCount;
		this.confirm = confirm;
	}

	/** full constructor */
	public User(String email, String password, String nickname, boolean gender,
			String image, Integer priority, Date time, Integer followerCount,
			Integer followingCount, Integer postCount, boolean confirm,
			Set messages, Set followsForFollowingId, Set comments,
			Set favorites, Set followsForFollowedId) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.gender = gender;
		this.image = image;
		this.priority = priority;
		this.time = time;
		this.followerCount = followerCount;
		this.followingCount = followingCount;
		this.postCount = postCount;
		this.confirm = confirm;
		this.messages = messages;
		this.followsForFollowingId = followsForFollowingId;
		this.comments = comments;
		this.favorites = favorites;
		this.followsForFollowedId = followsForFollowedId;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public boolean getGender() {
		return this.gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getFollowerCount() {
		return this.followerCount;
	}

	public void setFollowerCount(Integer followerCount) {
		this.followerCount = followerCount;
	}

	public Integer getFollowingCount() {
		return this.followingCount;
	}

	public void setFollowingCount(Integer followingCount) {
		this.followingCount = followingCount;
	}

	public Integer getPostCount() {
		return this.postCount;
	}

	public void setPostCount(Integer postCount) {
		this.postCount = postCount;
	}

	public boolean getConfirm() {
		return this.confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}

	public Set getMessages() {
		return this.messages;
	}

	public void setMessages(Set messages) {
		this.messages = messages;
	}

	public Set getFollowsForFollowingId() {
		return this.followsForFollowingId;
	}

	public void setFollowsForFollowingId(Set followsForFollowingId) {
		this.followsForFollowingId = followsForFollowingId;
	}

	public Set getComments() {
		return this.comments;
	}

	public void setComments(Set comments) {
		this.comments = comments;
	}

	public Set getFavorites() {
		return this.favorites;
	}

	public void setFavorites(Set favorites) {
		this.favorites = favorites;
	}

	public Set getFollowsForFollowedId() {
		return this.followsForFollowedId;
	}

	public void setFollowsForFollowedId(Set followsForFollowedId) {
		this.followsForFollowedId = followsForFollowedId;
	}

	public boolean getAlreadyFollowing() {
		return alreadyFollowing;
	}

	public void setAlreadyFollowing(boolean alreadyFollowing) {
		this.alreadyFollowing = alreadyFollowing;
	}
	
	public boolean getIsSelf() {
		return isSelf;
	}

	public void setIsSelf(boolean isSelf) {
		this.isSelf = isSelf;
	}

	public void MakeAllSetNull()
	{
		this.messages = null;
		this.followsForFollowingId = null;
		this.comments = null;
		this.favorites = null;
		this.followsForFollowedId = null;
	}
}