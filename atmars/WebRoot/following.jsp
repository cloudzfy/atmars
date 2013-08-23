<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@page import="org.atmars.dao.User"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	java.util.List<User> list_following=(java.util.List<User>) session.getAttribute("followings");
%>
<%
    org.atmars.dao.User user=(org.atmars.dao.User) session.getAttribute("user");
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>Following</title>
<link rel="stylesheet" type="text/css" href="css/following.css">
</head>

<body onLoad="Sync()">

	<div class="container">
		<header class="header">
			<div class="nav">
				<div class="logo">
					<img src="homepage-img/homelogo.png" height="34" width="100" />
				</div>
				<ul class="list">
					<li><a href="homepage"
						class="gbgt current" style="color:#fff; padding-left:5px">
							Homepage </a></li>
					<li><a href="search"
						class="gbgt" style="color:#fff; padding-left:15px"> Search </a>
					</li>
				</ul>
				<ul class="right">
					<li><a href="homepage"
						class="gbgt"
						style="color:#fff; padding-left:6px; padding-right:6px">
							<%=user.getNickname() %> </a>
					</li>
					<li style=" width:90px;"><a
						href="logout" class="gbgt"
						style="color:#fff; padding-left:14px"> Logout </a>
					</li class="current">
				</ul>
			</div>
		</header>
		<div class="content">
			<div id="conl" class="conl">
				<div class="left_nav">
					<div class="left_nav_title">FOLLOWING/FOLLOWERS</div>
					<div class="left_nav_item" style="background-color:#79c5e9">
						<img src="template-img/follow.png" class="left_nav_icon">
						<p class="left_nav_content">Following</p>
					</div>
					<a href="myFollowers" class="left_nav_item"> <img
						src="template-img/follower.png" class="left_nav_icon">
						<p class="left_nav_content">Followers</p> </a> <a href="search"
						class="left_nav_item"> <img src="template-img/search.png"
						class="left_nav_icon">
						<p class="left_nav_content">Search</p> </a>
				</div>
			</div>
			<div id="conr" class="conr">
			    <div class="search_box">
			       
					<p class="search_more">
						<span class="search_result">Find <%=list_following.size()%> results</span>
					</p>
				</div>
				<div style="clear:both"></div>
				<div id="person_list">
					
					<%
						int i = 0;
						while (i < list_following.size()) {
					%>
					
					<div class="item">
						<div class="item_l">
							<dl>
								<dt>
									<img src="<%=((User) list_following.get(i)).getImage()%>" class="item_img" />
								</dt>
								<dd>
									<div class="person_name"><a href="userpage?hisId=<%=((User) list_following.get(i)).getUserId()%>"><%=((User) list_following.get(i)).getNickname()%></a></div>
									<div class="item_content">
										<ul>
											<li><strong>Following(<%=((User) list_following.get(i)).getFollowingCount()%>)</strong></li>
											<li><strong>Followers(<%=((User) list_following.get(i)).getFollowerCount()%>)</strong></li>
											<li style="border:none"><strong>Posts(<%=((User) list_following.get(i)).getPostCount()%>)</strong></li>
										</ul>
									</div>
								</dd>
							</dl>
						</div>
					</div>
					<%
						i++;
						}
					%>
					
					
				</div>
				<div style="clear:both"></div>
			</div>
		</div>
        <script type="text/javascript" src="js/following.js"></script>
</body>
</html>
