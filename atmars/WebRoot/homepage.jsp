<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@page import="org.atmars.dao.User"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
    org.atmars.dao.User user=(org.atmars.dao.User) session.getAttribute("user");
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>Atmars Homepage</title>

<link rel="stylesheet" type="text/css" href="css/homepage.css">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.insertContent.js"></script>
<script type="text/javascript" src="js/maxlength.js"></script>

</head>
<body onLoad="Sync()">

<div class="container">
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
              <a href="homepage" class="gbgt" style="color:#fff; padding-left:6px; padding-right:6px" > 
              <%=user.getNickname() %>
            </a>
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
       <div class="input">
     <form>
       <div>
       	 <div style="float:left; margin-left:30px; margin-top:25px;"><img src="homepage-img/whatsup.png"></div>
         <div style="float:right; margin-right:50px; margin-top:30px;">&nbsp;letters left.</div>
         <div id="letter_num" style="float:right; margin-top:20px; font-family:'Arial Black', Gadget, sans-serif; font-size:24px;">140</div>
         <textarea id="publish_text"></textarea>
         <input type="hidden" name="maxlength" value="140" />
       </div>
       <div>
         <div style="float:left; margin-left:20px; margin-top:10px;">
           <a href="javascript:void(0);" id="emotion_button">
             <img id="emotion_img" src="homepage-img/emotion_0.png">
           </a>
         </div>
         <div style="float:left; margin-left:10px; margin-top:10px;">
           <a href="javascript:void(0);" id="image_button">
             <img id="image_img" src="homepage-img/image_0.png">
           </a>
         </div>
         <div style="float:left; margin-left:10px; margin-top:10px;">
           <a href="javascript:void(0);" id="location_button">
             <img id="location_img" src="homepage-img/location_0.png">
           </a>
         </div>
         <div style="float:left;">
           <input id="image_file" type="file" accept="image/*" style="visibility:collapse" onchange="handleFiles(this.files)" />
         </div>
         <div style="float:right">
           <input type="button" id="submit" value="Publish" onClick="publish()" />
         </div>
       </div>
     </form>
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
                <img src="<%=user.getImage() %>" height="50" width="50"/>
             </dt>
             <dd ><a href="homepage" class="name" ><%=user.getNickname() %></a></dd>
          </dl>
       </div>
       <div class="attention">
          <ul>
             <li>
               <a href="myFollowings" style="text-decoration: none !important; ">
               <strong>
               <%=user.getFollowingCount() %>
               </strong>
               <span>
               FOLLOWING
               </span>
               </a>
             </li>
             <li>
                <a href="myFollowers" style="text-decoration: none !important">
                <strong>
                <%=user.getFollowerCount() %>
                </strong>
                <span>
                FOLLOWERS
                </span>
                </a>
             </li>
             <li>
                <a href="userpage?hisId=<%= user.getUserId()%>" style="text-decoration: none !important">
                <strong>
                <div id="posts_amount"><%=user.getPostCount() %></div>
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
<div id="publish_emotion">
              <table width="200" border="0" align="center">
                <tr>
                  <td width="40" height="40"><a href="javascript:void(0);" title="smile"><img src="Face/smile.gif" width="24" height="24" alt="smile"></a></td>
                  <td width="40"><a href="javascript:void(0);" title="naughty"><img src="Face/naughty.gif" width="24" height="24" alt="naughty"></td>
                  <td width="40"><a href="javascript:void(0);" title="cry"><img src="Face/cry.gif" width="24" height="24" alt="cry"></td>
                  <td width="40"><a href="javascript:void(0);" title="angry"><img src="Face/angry.gif" width="24" height="24" alt="angry"></td>
                  <td width="40"><a href="javascript:void(0);" title="embarrass"><img src="Face/embarrass.gif" width="24" height="24" alt="embarrass"></td>
                </tr>
                <tr>
                  <td height="40"><a href="javascript:void(0);" title="crazy"><img src="Face/crazy.gif" width="24" height="24" alt="crazy"></td>
                  <td><a href="javascript:void(0);" title="effort"><img src="Face/effort.gif" width="24" height="24" alt="effort"></td>
                  <td><a href="javascript:void(0);" title="despise"><img src="Face/despise.gif" width="24" height="24" alt="despise"></td>
                  <td><a href="javascript:void(0);" title="lovely"><img src="Face/lovely.gif" width="24" height="24" alt="lovely"></td>
                  <td><a href="javascript:void(0);" title="laugh"><img src="Face/laugh.gif" width="24" height="24" alt="laugh"></td>
                </tr>
                <tr>
                  <td height="40"><a href="javascript:void(0);" title="titter"><img src="Face/titter.gif" width="24" height="24" alt="titter"></td>
                  <td><a href="javascript:void(0);" title="surprise"><img src="Face/surprise.gif" width="24" height="24" alt="surprise"></td>
                  <td><a href="javascript:void(0);" title="orz"><img src="Face/orz.gif" width="24" height="24" alt="orz"></td>
                  <td><a href="javascript:void(0);" title="unhappy"><img src="Face/unhappy.gif" width="24" height="24" alt="unhappy"></td>
                  <td><a href="javascript:void(0);" title="wronged"><img src="Face/wronged.gif" width="24" height="24" alt="wronged"></td>
                </tr>
              </table>
       </div>
       <div id="publish_image">
       <div style="width:100%; z-index:300; position:absolute">
	       <div id="publish_image_close">
           	<a href="javascript:void(0);"><img src="homepage-img/close.png" /></a>
           </div>
       </div>
       <div style="z-index:250; position:absolute">
	       <img id="preview_image" />
       </div>
       </div>
       <div id="publish_location">
       <div style="float:left; margin-left:10px; margin-top:10px; width:200px; height:200px; text-align: center; line-height:200px">
       	<img id="google_map" src="homepage-img/loader.gif" />
       </div>
       <div id="position_mark_logo" style="float:left; margin-left:10px; display:none"><img src="homepage-img/mark.png" /></div>
       <div style="float:left; margin-left:5px"><p style="font-size:16px;" id="positionInfo"></p></div>
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
       <script type="text/javascript" src="js/homepage.js"></script>
</body>
</html>
