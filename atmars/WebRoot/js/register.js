
    var emailIsRight=0;
    var nicknameIsRight=0;
    var passwordIsRight=0;
    var passwordcIsRight=0;

	function handleFiles(files){
		var file = files[0];  
		var reader = new FileReader();  
		reader.onload = function(e){
		var img=document.getElementById("image");
			img.src=e.target.result;
		}
		reader.readAsDataURL(file);
	}
    
	function email_text_onfocus(){
		var proemail=document.getElementById("proemail");
		var email=document.getElementById("email");
		proemail.className="prompt";
		proemail.style.color="#333333";
		proemail.style.paddingTop="0px";
		proemail.style.paddingBottom="0px";
		proemail.innerHTML="Use your Email as login account E.g:example@example.com";
		proemail.style.display="block";
		email.placeholder="";
		emailIsRight=0;
	}
	
	function checkemail(str){
		var Expression=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/; 
		var objExp = new RegExp(Expression);
		if(objExp.test(str)==true){
		 return true;
		}else{
		return false;
		}
	}
	
	function email_text_onblur(){
		var email=document.getElementById("email");
		var proemail=document.getElementById("proemail");
		if(checkemail(email.value)){
			 var xhr = new XMLHttpRequest();
			 xhr.open("GET","email_occupied.action?email="+email.value,true);
	    	 xhr.onreadystatechange = function(){
				 if(xhr.readyState==4 && xhr.status==200){
					 var response=JSON.parse(xhr.responseText);
					 if(response.occupied==false){
						 proemail.className="prompt_right";
						 proemail.innerHTML="";
						 var success=document.createElement("img");
						 success.className="successimg";
						 success.src="register-img/success.png";
						 proemail.appendChild(success);
						 emailIsRight=1;
					 }
					 else{
						 proemail.className="prompt_wrong";
						 proemail.style.color="#FF0000";
						 proemail.style.paddingTop="10px";
						 proemail.style.paddingBottom="10px";
						 proemail.innerHTML="User already exist";
					 }
				 }
				 else{
					  proemail.className="prompt_wrong";
					  proemail.style.color="#FF0000";
					  proemail.style.paddingTop="10px";
				      proemail.style.paddingBottom="10px";
				      proemail.innerHTML="Authentication failed";
				 }
			 }
			 xhr.send(null);
			 
		}
		else{
			proemail.className="prompt_wrong";
			proemail.style.color="#FF0000";
			proemail.style.paddingTop="10px";
			proemail.style.paddingBottom="10px";
			proemail.innerHTML="Enter your correct email";
		}
		proemail.style.display="block";
	}
	
	function password_text_onfocus(){
		var propassword=document.getElementById("propassword");
		propassword.className="prompt";
		propassword.style.color="#333333";
		propassword.style.paddingTop="0px";
		propassword.style.paddingBottom="0px";
		propassword.innerHTML="This should be 6-16 characters (letters, numbers or symbols)";
		propassword.style.display="block";
		passwordIsRight=0;
	}
	
	function password_text_onblur(){
		var password=document.getElementById("password");
		var propassword=document.getElementById("propassword");
		var passwordc=document.getElementById("passwordc");
		if(password.value.length < 6 ){
			propassword.className="prompt_wrong";
			propassword.style.color="#FF0000";
			propassword.style.paddingTop="10px";
			propassword.style.paddingBottom="10px";
			propassword.innerHTML="Minimum 6 characters!";
		}
		else if(password.value.length > 16 ){
			propassword.className="prompt_wrong";
			propassword.style.color="#FF0000";
			propassword.style.paddingTop="10px";
			propassword.style.paddingBottom="10px";
			propassword.innerHTML="Max 16 characters!";
		}
		else if(passwordc.value!=password.value){
			if(passwordc.value!="")
			{
				var propasswordc=document.getElementById("propasswordc");
				propasswordc.className="prompt_wrong";
				propasswordc.style.color="#FF0000";
			    propasswordc.style.paddingTop="0px";
		        propasswordc.style.paddingBottom="0px";
		    	propasswordc.innerHTML="Two passwords do not match.";
		    	passwordcIsRight=0;
			}
			propassword.className="prompt_right";
		    propassword.innerHTML="";
			var success=document.createElement("img");
			success.className="successimg";
			success.src="register-img/success.png";
			propassword.appendChild(success);
			passwordIsRight=1;
		}
		else if(passwordc.value==password.value)
		{
			var propasswordc=document.getElementById("propasswordc");
			
			propassword.className="prompt_right";
			propassword.innerHTML="";
			var success=document.createElement("img");
			success.className="successimg";
			success.src="register-img/success.png";
			propassword.appendChild(success);
			
			propasswordc.className="prompt_right";
			propasswordc.innerHTML="";
			var csuccess=document.createElement("img");
			csuccess.className="successimg";
			csuccess.src="register-img/success.png";
			propasswordc.appendChild(csuccess);
			passwordIsRight=1;
			passwordcIsRight=1;
		}
		propassword.style.display="block";
	}
	
	function passwordc_text_onfocus(){
		var propasswordc=document.getElementById("propasswordc");
		propasswordc.className="prompt";
		propasswordc.style.color="#333333";
		propasswordc.style.paddingTop="0px";
		propasswordc.style.paddingBottom="0px";
		propasswordc.innerHTML="This should be 6-16 characters (letters, numbers or symbols)";
		propasswordc.style.display="block";
		passwordcIsRight=0;
	}
	
	function passwordc_text_onblur(){
		var password=document.getElementById("password");
		var propasswordc=document.getElementById("propasswordc");
		var passwordc=document.getElementById("passwordc");
		if(passwordc.value.length < 6 ){
			propasswordc.className="prompt_wrong";
			propasswordc.style.color="#FF0000";
			propasswordc.style.paddingTop="10px";
			propasswordc.style.paddingBottom="10px";
			propasswordc.innerHTML="Minimum 6 characters!";
		}
		else if(passwordc.value.length > 16 ){
			propasswordc.className="prompt_wrong";
			propasswordc.style.color="#FF0000";
			propasswordc.style.paddingTop="10px";
			propasswordc.style.paddingBottom="10px";
			propasswordc.innerHTML="Max 16 characters!";
		}
		else if(passwordc.value!=password.value && password.value!=""){
			propasswordc.className="prompt_wrong";
			propasswordc.style.color="#FF0000";
			propasswordc.style.paddingTop="0px";
		    propasswordc.style.paddingBottom="0px";
			propasswordc.innerHTML="Two passwords do not match.";
		}
		else{
			propasswordc.className="prompt_right";
			propasswordc.innerHTML="";
			var success=document.createElement("img");
			success.className="successimg";
			success.src="register-img/success.png";
			propasswordc.appendChild(success);
			passwordcIsRight=1;
		}
		propasswordc.style.display="block";
	}
	
	function nickname_text_onfocus(){
		var pronickname=document.getElementById("pronickname");
		pronickname.className="prompt";
		pronickname.style.color="#333333";
		pronickname.style.paddingTop="0px";
		pronickname.style.paddingBottom="0px";
		pronickname.innerHTML="You may enter 4-20 characters";
		pronickname.style.display="block";
		nacknameIsRight=0;
	}
	
	function nickname_text_onblur(){
		var nickname=document.getElementById("nickname");
		var pronickname=document.getElementById("pronickname");
		if(nickname.value.length < 4 ){
			pronickname.className="prompt_wrong";
			pronickname.style.color="#FF0000";
			pronickname.style.paddingTop="10px";
			pronickname.style.paddingBottom="10px";
			pronickname.innerHTML="Minimum 4 characters!";
		}
		else if(nickname.value.length > 20 ){
			pronickname.className="prompt_wrong";
			pronickname.style.color="#FF0000";
			pronickname.style.paddingTop="10px";
			pronickname.style.paddingBottom="10px";
			pronickname.innerHTML="Max 20 characters!";
		}
		else{
			var xhr = new XMLHttpRequest();
			xhr.open("GET","nickname_occupied.action?nickname="+nickname.value,true);
	    	xhr.onreadystatechange = function(){
				if(xhr.readyState==4 && xhr.status==200){
					var response=JSON.parse(xhr.responseText);
					if(response.occupied==false){
						pronickname.className="prompt_right";
						pronickname.innerHTML="";
						var success=document.createElement("img");
						success.className="successimg";
						success.src="register-img/success.png";
						pronickname.appendChild(success);
						nicknameIsRight=1;
						}
						else{
						pronickname.className="prompt_wrong";
						pronickname.style.color="#FF0000";
						pronickname.style.paddingTop="10px";
						pronickname.style.paddingBottom="10px";
						pronickname.innerHTML="Nickname already exist";
						}
				}
				else{
					 pronickname.className="prompt_wrong";
					 pronickname.style.color="#FF0000";
					 pronickname.style.paddingTop="10px";
				     pronickname.style.paddingBottom="10px";
				     pronickname.innerHTML="Authentication failed";
				}
			 }
			 xhr.send(null);
		}
		pronickname.style.display="block";
	}
	
	function checkform(){
		var myform=document.getElementById("myform");
		var email=document.getElementById("email");
		var password=document.getElementById("password");
		var passwordconfirm=document.getElementById("passwordconfirm");
		var nickname=document.getElementById("nickname");
		if(emailIsRight==0){
			email.focus();
			return;
		}
		else if(passwordIsRight==0){
			password.focus();
			return;
		}
		else if(passwordcIsRight==0){
			passwordc.focus();
			return;
		}
		else if(nicknameIsRight==0){
			nickname.focus();
			return;
		}
		else{
			 myform.submit();
		}
	}
