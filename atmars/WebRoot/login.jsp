<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@page import="org.atmars.dao.User"%>
<%
	java.util.List<User> userList=(java.util.List<User>) session.getAttribute("userList");
%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<!-- TemplateBeginEditable name="doctitle" -->
<title>Welcome to Atmars</title>
<!-- TemplateEndEditable -->
<!-- TemplateBeginEditable name="head" -->
<!-- TemplateEndEditable -->
<link rel="stylesheet" type="text/css" href="css/login.css">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
   
  
</script>
<body onLoad="initialize()">
 <div id="pageflip">
<a href="atmarsAPI.html">  <img src="login-img/peersm.png" alt="Subscribe!" id="page-flip-image" style="width: 100px; height: 63px; "/></a>
<div id="msg_block" style="width: 100px; height: 63px; "></div>
</div> 
<div class="container">
  <img src="weibo-img/logo.png" class="logo" />
  <div class="img"> 
    <img src="weibo-img/poster.png" width="100%" height="100%"/>
  </div>

  <div class="content">
     <div class="tList_mdu1">
       <dl class="mainlist">
          <dt style="font-family:'Comic Sans MS', cursive; font-size:18px">&nbsp;&nbsp;New User ...</dt>
       </dl>
       <dd>
         <ul id="userlist">
         
         <%int i=0;
         int count=9;
         if(userList.size()<9)
            { count=userList.size(); }
            while(i<count)
         {
          %>
         
          <li ><a href="javascript:void(0)"><img src="<%=((User) userList.get(i)).getImage()%>" class="person_img" ></a><a href="userpage?hisId=<%=((User) userList.get(i)).getUserId() %>" class="person_name"><%=((User) userList.get(i)).getNickname()%></a> </li>
          <% 
          i++;
          }
           %>
         
         
         
         </ul>
       </dd>
    </div>
    
    <div class="tList_mdu2">
        <dl class="twit_list">
           <dt style="font-family:'Comic Sans MS', cursive; font-size:18px">&nbsp;&nbsp;Talking ...</dt>
           <dd style="position:relative; height:550px; overflow:hidden">
           <div class="bottomcover" style="z-index:2"></div>
           <div id="listr" class="maincontent" style="position:relative">
           
           </div>
        </dl>
     </div>    
     <div style="clear:both"></div>
  </div>
 
   <div class="login">
   <a href="register.jsp"><button class="registerbt">Register Now</button></a>
    <form name="form1" method="post" action="performLogin">
      <table width="267" border="0"  style="margin-top:20px">
        <tr>
          <td><label for="username">
            <input name="email" type="text" class="textinp" id="email" size="30" maxlength="20" placeholder="Email" onBlur="email_text_onblur()" onFocus="email_text_onfocus()" style="color:#333333">
          </label></td>
        </tr>
        <tr>
          <td><label for="password">
            <input name="password" type="password" class="textinp" id="password" size="30" maxlength="20" onFocus="password_text_onfocus()" placeholder="Password" style="color:#333333">
          </label></td>
        </tr>
      </table>
      <%
      	if(session.getAttribute("error")==null)
      	{
       %>
      <div id="proemail" class="prompt_wrong"></div>
      <%
      	}
       %>
       <%
       		if(session.getAttribute("error")!=null)
       		{
        %>
     	 <div id="proemail" class="login_wrong">Sorry, the account with this keycode was not found.</div>
      <%
      	}
       %>
      <input type="submit" name="login" id="login" value="Login" class="loginbt">
      
    </form>
     
  </div>
  <div class="footer">
  <!-- end .footer --></div>
<!-- end .container --></div>
<script type="text/javascript" src="js/login.js"></script>
</body>
</html>
