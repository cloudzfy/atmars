<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@page import="org.atmars.dao.User"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	java.util.List<User> list_search=(java.util.List<User>) session.getAttribute("search");
%>
<%
    org.atmars.dao.User user=(org.atmars.dao.User) session.getAttribute("user");
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>Search</title>
<link rel="stylesheet" type="text/css" href="css/search.css">
</head>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script>
	function Sync() {
		var bheight= window.screen.availHeight-160;
		var conrheight= document.all.conr.offsetHeight;
		if(bheight>conrheight){
			document.all.conl.style.height = bheight+ "px";
			document.all.conr.style.height = bheight+ "px";
		}
		else{
			document.all.conl.style.height=conrheight+"px";
		}
	}
</script>
<body onLoad="Sync()">

	<div class="container">
		<header class="header">
			<div class="nav">
				<div class="logo">
					<img src="homepage-img/homelogo.png" height="34" width="100" />
				</div>
				<ul class="list">
					<li><a href="homepage" class="gbgt current"
						style="color:#fff; padding-left:5px"> Homepage </a>
					</li>
					<li>
						<div class="gbgt"
							style="color:#fff; padding-left:15px; background-color:#4380a2">
							Search</div></li>
				</ul>
				<ul class="right">
					<li><a href="homepage" class="gbgt"
						style="color:#fff; padding-left:6px; padding-right:6px">
							<%=user.getNickname() %> </a></li>
					<li style=" width:90px;" class="current"><a href="logout" class="gbgt"
						style="color:#fff; padding-left:14px"> Logout </a></li>
				</ul>
			</div>
		</header>
		<div class="content">
			<div id="conl" class="conl">
				<div class="left_nav">
					<div class="left_nav_title">FOLLOWING/FOLLOWERS</div>
					<a href="myFollowings" class="left_nav_item"> <img
						src="template-img/follow.png" class="left_nav_icon">
				 <p class="left_nav_content">Following</p> 
					</a> <a href="myFollowers" class="left_nav_item"> <img
						src="template-img/follower.png" class="left_nav_icon">
					<p class="left_nav_content">Followers</p> 
					</a>
					<div class="left_nav_item" style="background-color:#79c5e9">
						<img src="template-img/search.png" class="left_nav_icon">
						<p class="left_nav_content">Search</p>
					</div>
				</div>
			</div>
			<div id="conr" class="conr">
				<div class="search_box">
					<div class="search_bar">
						<form action="search">
							<input class="input_search" name="searchString" type="text"
								value="" maxlength="40" /> <input class="btn_search" type="submit" />
						</form>
					</div>
					<p class="search_more">
						<span class="search_result">find <%=list_search.size()%> results</span>
					</p>
				</div>
				<div style="clear:both"></div>
				<div id="person_list">
					

					<%
						int i = 0;
						while (i < list_search.size()) {
					%>
					
					
					
					
					<div class="item">
						<div class="item_l">
							<dl>
								<dt>
									<img src="<%=((User) list_search.get(i)).getImage()%>" class="item_img" />
								</dt>
								<dd>
									<div class="person_name"><a href="userpage?hisId=<%=((User) list_search.get(i)).getUserId()%>"><%=((User) list_search.get(i)).getNickname()%></a></div>
									<div class="item_content">
										<ul>
											<li><strong>Following(<%=((User) list_search.get(i)).getFollowingCount()%>)</strong>
											</li>
											<li><strong>Followers(<%=((User) list_search.get(i)).getFollowerCount()%>)</strong>
											</li>
											<li style="border:none"><strong>Posts(<%=((User) list_search.get(i)).getPostCount()%>)</strong>
											</li>
										</ul>
									</div>
								</dd>
							</dl>
						</div>
						<div class="item_r">
							<p class="follow" id="add<%=((User) list_search.get(i)).getUserId()%>">
							    <%
							    if(((User) list_search.get(i)).getAlreadyFollowing()==false && ((User) list_search.get(i)).getIsSelf()==false)
							    {
							     %>
								<a href="javascript:void(0)" class="addbtn" style="text-decoration:none" name="<%=((User) list_search.get(i)).getUserId()%>" onClick="addfollow(this.name)">+Follow</a>
                                <script type="text/javascript">
								    function addfollow(userid){
										$.get("addFollow.action?hisId="+userid,null,function(result){
											if(result.isSuccess==true){
												var addid="add"+userid;
												var follow=document.getElementById(addid);
												follow.innerHTML='<a href="javascript:void(0)" class="addsuccessbtn" style="text-decoration:none"><img src="search-img/success.png" style="width:12px; height:12px"/>Followed</a>';
											}
										});
									}
								</script>
								<%
								}
								if(((User) list_search.get(i)).getAlreadyFollowing()==true  && ((User) list_search.get(i)).getIsSelf()==false)
							    {
								%>
								<a href="javascript:void(0)" class="addsuccessbtn" style="text-decoration:none"><img src="search-img/success.png" style="width:12px; height:12px"/>Followed</a>
								<%
								}
								 %>
							</p>
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
	</div>
</body>
</html>
