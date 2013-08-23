<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@page import="org.atmars.dao.User"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
    org.atmars.dao.User user=(org.atmars.dao.User) session.getAttribute("user");
    org.atmars.dao.User hisUser = (org.atmars.dao.User)request.getAttribute("hisUser");
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>Atmars Userpage</title>

<link rel="stylesheet" type="text/css" href="css/userpage.css">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.insertContent.js"></script>
<script type="text/javascript" src="js/maxlength.js"></script>

</head>

<body onLoad="Sync()">

<div class="container" id="<s:property value="hisId" />">
  <header class="header">
    <div class="nav">
      <div class="logo">
        <img src="homepage-img/homelogo.png" height="34" width="100" />
      </div>
       <ul class="list">
           <li><a href="homepage" class="gbgt current" style="color:#fff; padding-left:5px" > Homepage </a></li>
           <li>
              <a href="search" class="gbgt" style="color:#fff; padding-left:15px" >
              Search
              </a>
           </li>
       </ul>
       <ul class="right">
          <li>
             
							<%if (user!=null) {%>
							
							 <a href="homepage" class="gbgt" style="color:#fff; padding-left:6px; padding-right:6px" >
							 <%=user.getNickname() %> </a>
							
							<%}else{ %>
							 <a href="start" class="gbgt" style="color:#fff; padding-left:6px; padding-right:6px" >
							 login </a>
							<%} %>
							
							
          </li>
          <li  style=" width:90px;">
              <a href="logout" class="gbgt" style="color:#fff; padding-left:14px" > 
              Logout
              </a>
          </li class="current">
       </ul>
    </div>
  </header>
  <div class="content">
     <div id="conl" class="conl">
       <div class="user_infor">
         <div class="user_image">
           <img src="<s:property value="hisUser.image" />" width="132px" height="132px" />
         </div>
         <div class="user_detail">
           <div class="user_name"><s:property value="hisUser.nickname" /></div>
           <div id="sex">
             <div >
               <img class="sex_img" src="userpage-img/<s:property value="hisUser.gender" />.png"/>
             </div>
             <s:set name="gender" value="gender" />
             <%if(hisUser.getGender()){ %>
            <div class="sex_name">Male</div>
             <%}else{ %>
              <div class="sex_name">Female</div>
             <%} %>
            
             <div style="clear:both"></div>
           </div>
           <div id="mypage" class="mypage"></div>
         </div>
         <div style="clear:left"> </div>
       </div>
       <div style="width:553px; height:3px; background-color:#EAEDEE; float:left; margin-left:15px; margin-top:20px; margin-bottom:20px"></div>
          <div id="mainlist">
          </div>
          <div id="loading_div" class="loading_div">
          	<img src="homepage-img/loading.gif">&nbsp;&nbsp;Loading&nbsp;...
          </div>
   
     </div>
       
     <div id="conr" class="conr">
       <div class="person_info">
          <dl>
             <dt>
                <img src="<s:property value="hisUser.image" />" height="50" width="50"/>
             </dt>
             <dd ><a href="userpage?hisId=<%=hisUser.getUserId() %>" class="name" ><s:property value="hisUser.nickname" /></a></dd>
          </dl>
       </div>
       <div class="attention">
          <ul>
             <li>
               <a href="javascript:void(0);" style="text-decoration: none !important; ">
               <strong>
               <s:property value="hisUser.followingCount" />
               </strong>
               <span>
               FOLLOWING
               </span>
               </a>
             </li>
             <li>
                <a href="javascript:void(0);" style="text-decoration: none !important">
                <strong>
               <s:property value="hisUser.followerCount" />
                </strong>
                <span>
                FOLLOWERS
                </span>
                </a>
             </li>
             <li>
                <a href="userpage?hisId=<%=hisUser.getUserId() %>" style="text-decoration: none !important">
                <strong>
                <s:property value="hisUser.postCount" />
                </strong>
                <span>
                POSTS
                </span>
                </a>
             </li>
          </ul>
       </div>
     </div>
    <div style="clear:both">
    </div>
  </div>
</div>
       <div id="back_div">
       
       </div>
       <div id="forward_div">
       <form>
       <div style="width:100%; height:36px; background-color:#EAFFD7">
       <div style="float:left; font-size:20px; margin-left:8px; margin-top:8px; color:#008000">Forward</div>
       <div style="float:right; width:20px; height:20px; margin-right:8px; margin-top:8px;"><a href="javascript:void(0);" onClick="closing()"><img src="homepage-img/close.png"></a></div>
       </div>
       <div style="float:right; margin-top:15px;">
       <div style="float:right; margin-right:50px; margin-top:30px; color:#999999">&nbsp;letters left.</div>
       <div id="forward_letter_num" style="float:right; margin-top:26px; font-family:'Arial Black', Gadget, sans-serif; font-size:18px; color:#999999">140</div>
       </div>
       <div style="float:left; width:412px;; margin-left:15px; margin-right:15px; margin-top:15px;">
       <textarea class="forward_text" id="forward_text"></textarea>
       <input type="hidden" name="maxlength" value="140" />
       <input type="hidden" id="forward_message_id" />
       <div style="float:right; margin-right:15px"><input value="Forward" type="button" class="forward_button" onClick="forward_send()" /></div>
       </div>
        </form>
       </div>
       <script type="text/javascript" src="js/userpage.js"></script>
</body>
</html>
