<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<!-- TemplateBeginEditable name="doctitle" -->
<title>Mars Register</title>
<!-- TemplateEndEditable -->
<!-- TemplateBeginEditable name="head" -->
<!-- TemplateEndEditable -->
<link rel="stylesheet" type="text/css" href="css/register.css">

<script type="text/javascript" src="js/register.js"></script>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
</head>

<body>
<div class="cloud"></div>
<div class="container">
  <div class="filter"></div>
  <div class="header"> <img src="register-img/register.png" width="917" class="register"/>
    <!-- end .header --></div>
    
  <div style="top:0; height:117px; background-color:#A1E0E9"></div>
  <div class="content">
    
    <table width="580" height="90" border="0" background="register-img/email.png" style="margin-top:50px; margin-left:30px">
      <tr>
        <td>&nbsp;</td>
      </tr>
    </table>

    <s:form id="myform" name="form1" method="post" action="performRegister" enctype="multipart/form-data">
      
      <table width="650" height="190" border="0" class="table" >
        <tr>
          <td width="97" height="60" align="left" valign="middle" style="color:#666666">Email</td>
          <td width="321">
            <input name="email" type="text" class="textinp" id="email" size="80" maxlength="20" onBlur="email_text_onblur()" onFocus="email_text_onfocus()" style="color:#333333">
          </td>
          <td width="218"><div id="proemail" class="prompt" ></div> </td>
        </tr>
        <tr>
          <td height="60" align="left" valign="middle" style="color:#666666">Password</td>
          <td><input name="password" type="password" class="textinp" id="password" value="" onBlur="password_text_onblur()" onFocus="password_text_onfocus()" size="30"maxlength="20" style="color:#333333"></td>
          <td><div id="propassword" class="prompt" ></div></td>
        </tr>
        <tr>
          <td height="60" align="left" valign="middle" style="color:#666666">Password Confirm</td>
          <td><input name="passwordconfirm" type="password" class="textinp" id="passwordc" value="" onBlur="passwordc_text_onblur()" onFocus="passwordc_text_onfocus()" size="30" maxlength="20" style="color:#333333"></td>
          <td><div id="propasswordc" class="prompt" ></div></td>
        </tr>
        <tr>
          <td height="60" align="left" valign="middle" style="color:#666666">Nickname</td>
          <td><input name="nickname" type="text" class="textinp" id="nickname" onBlur="nickname_text_onblur()" onFocus="nickname_text_onfocus()" size="30"maxlength="20" style="color:#333333"></td>
          <td><div id="pronickname" class="prompt" ></div></td>
        </tr>
        <tr>
          <td height="60" align="left" valign="middle" style="color:#666666">Gender</td>
          <td>
          <div class="fragm">
               <label><input type="radio" value="true" name="gender" id="rdoboy" class="ra" /> Male</label>
          </div>
          <div class="fragm">
               <label><input type="radio" value="false" name="gender" id="rdogirl" class="ra" />Female</label>
          </div>
          </td>
        </tr>
         <tr>
          <td height="90" align="left" valign="middle" style="color:#666666">Photo</td>
          <td>
          <img id="image" src="register-img/uploadphoto.png" onClick="image_file.click()" class="upimg"/>
          </td>
          <td>
           <s:file id="image_file" name="upload" type="file" style="visibility:collapse" onchange="handleFiles(this.files)" />
          </td>
        </tr>
      </table>
      <p>
        <input type="button" name="submitbt" id="submitbt" value="Submit" class="submitbt" onclick="checkform()">
      </p>

    </s:form>
    <table width="780" height="200" border="0" background="register-img/image.png">
      <tr>
        <td>&nbsp;</td>
      </tr>
    </table>
  </div>
  <div class="footer">
    <p>&nbsp;</p>
  <!-- end .footer --></div>
<!-- end .container --></div>
</body>
</html>

