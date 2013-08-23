 
$(document).ready(function(){
 
//Page Flip on hover
 
	$("#pageflip").hover(function() {
		$("#pageflip #page-flip-image,#msg_block").stop()
			.animate({
				width: '198px', 
				height: '124px'
			}, 500); 
		} , function() {
		$("#pageflip #page-flip-image,#msg_block").stop() 
			.animate({
				width: '100px', 
				height: '63px'
			}, 220);
	});
});
 
 function initialize(){
	  $.get("init_weiboQiang.action",null,function(result){
		  var response = result;
		  var newMsg = response.new_Messages;
		  var listr =document.getElementById("listr");
		  for(var i = 1; i<newMsg.length; i++){
				   var str='<div class="item"><div class="twit_item"><div class="twit_item_pic"><a href="#"><img src="'+newMsg[i].user.image+'" class="twit_item_img"></a></div><div class="twit_item_content"><a href="#" class="twit_item_name">'+newMsg[i].user.nickname+':</a>'+newMsg[i].text+'<div class="twit_item_time">'+newMsg[i].timeDescription+'</div></div></div></div>';
				   listr.innerHTML= listr.innerHTML+str;
			  }
			  setTimeout("setInterval('weiboqiang()',5000)",10);
	   });
  }
  
  var ifrequest=0;
  var newestmessage=-2;
  var time;
  function weiboqiang(){
	 var listr=document.getElementById("listr");
	  var top=listr.offsetTop;
		  $.get("getNewestMessage.action?currentNewestMessageId="+newestmessage,null,function(result){
				  var response=result;
				  if(response.newestMessage_Now.newestState==true){
				  newestmessage=response.newestMessage_Now.messageId;
				  var str='<div class="twit_item"><div class="twit_item_pic"><a href="#"><img src="'+response.newestMessage_Now.user.image+'" class="twit_item_img"></a></div><div class="twit_item_content"><a href="#" class="twit_item_name">'+response.newestMessage_Now.user.nickname+':</a>'+response.newestMessage_Now.text+'<div class="twit_item_time">'+response.newestMessage_Now.timeDescription+'</div></div></div>';

				  
				  var firstitem=document.createElement("div");
			      firstitem.className="item";
				  firstitem.innerHTML=str;
			      
				  listr.insertBefore(firstitem,listr.firstChild);
				  if(listr.childNodes.length>8){
					  listr.removeChild(listr.lastChild);
				  }
				  top=0-listr.firstChild.offsetHeight;
				  listr.style.top=top+"px";
				  $("#listr").animate({top:0},3000);
				  }
				  
		  });
  }
  
 
  function email_text_onfocus(){
		var email=document.getElementById("email");
		email.placeholder="";
  }
  
  function password_text_onfocus(){
		var email=document.getElementById("password");
		email.placeholder="";
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
		if(checkemail(email.value)==false){
			proemail.className="prompt_wrong";
			proemail.style.color="#FF0000";
			proemail.style.paddingTop="10px";
			proemail.style.paddingBottom="10px";
			proemail.innerHTML="Enter your correct email";
			proemail.style.display="block";
		}
		else{
			proemail.style.display="none";
		}
		
	}