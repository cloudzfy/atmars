$(function () {
	$("#publish_text").maxlength({
		'feedback': '#letter_num',
		'useInput': true
	});
	$("#forward_text").maxlength({
		'feedback': '#forward_letter_num',
		'useInput':true
	});
	$("#comment_text").maxlength({
		'feedback': '#comment_letter_num',
		'useInput':true
	});
});

function Sync(){
	document.all.conr.style.height=document.all.conl.offsetHeight+"px";
	pageRefresh();
}

 var upload_img = "null";
		   var upload_filename = "null";
		    function handleFiles(files){
				var file = files[0];
				upload_filename = files[0].name;
	    		var reader = new FileReader();  
	    		reader.onload = function(e){
					$("#preview_image").attr("src",null);
					upload_img = e.target.result;
					var tmp = new Image();
					tmp.setAttribute("src",e.target.result);
					$("#preview_image").attr("src",e.target.result).load(function() {
                        if(tmp.width<400){
							$("#publish_image").width(tmp.width+10);
							$("#publish_image").height(tmp.height+10);
							$("#preview_image").width(tmp.width);
							$("#preview_image").height(tmp.height);
						} else {
							$("#publish_image").width(400);
							$("#publish_image").height(400*tmp.height/tmp.width);
							$("#preview_image").width(390);
							$("#preview_image").height($("#publish_image").height()-10);
						}
                    });
    			}
    			reader.readAsDataURL(file);  
    		}
			$("#image_button").click(function(e) {
                if(upload_filename == "null") {
					image_file.click();
				} else {
					$("#image_img").attr("src","homepage-img/image_0.png");
					upload_filename = "null";
					upload_img = "null";
					$("#image_file").attr("value","");
					$("#preview_image").width(null);
					$("#preview_image").height(null);
				}
            });
			
function publish(){
	if($("#publish_text").val()==""){
		return;
	}
		  $.post("publish.action",{"messageid":"-1","text":$("#publish_text").val(),"position":$("#positionInfo").text(),"upload":upload_img,"uploadFileName":upload_filename},function(result){
			  var str = '<dt class="face"><a href="javascript:void(0);"><img src="';
			  if(result.lastPost.user.image!=null){
					str = str + result.lastPost.user.image;
				}else{
					 str = str + 'homepage-img/upimg.png';
				 }
					   str = str + '" width="50" height="50"></a></dt><dd class="flcontent"><p class="fltext"><a href="userpage.action?hisId=' + result.lastPost.user.userId + '" class="author_name">' + result.lastPost.user.nickname + '</a>:&nbsp;' + result.lastPost.text + '</p>';
					   if(result.lastPost.image!=null){
						   str = str + '<p><div style="margin-left:10px; margin-top:5px;"><img src="' + result.lastPost.image + '" style="width:300px"></div></p>';
					   }
					   str = str + '<p class="fltime"><span><a href="javascript:void(0);" name="' + result.lastPost.messageId + '" onClick="forward(this.name)">Forward</a><i class="W_vline">&nbsp;|&nbsp;</i><a href="javascript:void(0);" name="' + result.lastPost.messageId + '" onClick="comment(this.name,true)">Comment</a></span>' + result.lastPost.timeDescription + '&nbsp;&nbsp;&nbsp;';
					   if(result.lastPost.position!=null && result.lastPost.position!=""){
						   str = str + 'From ' + result.lastPost.position;
					   }
					   str = str + '</p><div id="commentlist_' + result.lastPost.messageId + '" class="commentlist"></div></dd>';
					   var i = document.createElement("dl");
					   i.className="feed_list";
					   i.innerHTML=str;
					   i.id="message_" + result.lastPost.messageId;
					   var mainlist = document.getElementById("mainlist");
					   mainlist.insertBefore(i,mainlist.firstChild);
					   var height=i.offsetHeight+100;
					   i.style.display = "none";
					   $("#mainlist").animate({marginTop:height+"px"},600,"swing",function(){
						   mainlist.style.marginTop="70px";
						   $("#" + "message_" + result.lastPost.messageId).fadeIn(1000);
					   });
					   $("#publish_text").val("");
					   $("#image_img").attr("src","homepage-img/image_0.png");
					   upload_filename = "null";
					   upload_img = "null";
					   $("#image_file").attr("value","");
					   $("#preview_image").width(null);
					   $("#preview_image").height(null);
					   $("#location_img").attr("src","homepage-img/location_0.png");
					   $("#positionInfo").text("");
					   $("#letter_num").text("140");
					   isLocation = false;
					   $("#posts_amount").text($("#posts_amount").text()-0+1);
					   document.all.conr.style.height=document.all.conl.offsetHeight+"px";
		  });
	  }


  function forward(id){
	   $("#back_div").height($(document.body).height());
	   $("#back_div").width($(document.body).width());
	   $("#back_div").fadeIn(700);
	   $("#forward_div").fadeIn(700);
	   $("#forward_div").css("left",document.body.scrollLeft+(window.screen.availWidth-442)/2);
	   $("#forward_div").css("top",document.body.scrollTop+(window.screen.availHeight-290)/2);
	   $("#forward_message_id").val(id);
	   $("#forward_text").val("Fw: ");
	   $("#forward_letter_num").text("140");
   }
   
   function closing(){
	   $("#back_div").fadeOut(700);
	   $("#forward_div").fadeOut(700);
   }
   $(window).scroll(function(e) {
	   if(document.body.scrollTop+window.screen.availHeight>=document.body.scrollHeight){
		   pageRefresh();
	   }
   });
   var oldest_message_id = 9999999;
   function pageRefresh(){
	   $.get("getMyMessages.action?oldest_message_id=" + oldest_message_id,null,function(response){
		   $("#loading_div").css("display","block");
				   var myMsg = response.myMessages;
				   if(myMsg == null || myMsg.length == 0)
				   {
					   $("#loading_div").css("display","none");
					   return;
				   }
				   else
				   {
					   $("#loading_div").css("display","block");
				   }
				   var not_original = response.not_original;
				   for(var i = 0; i<myMsg.length; i++){
					   if(oldest_message_id <= myMsg[i].messageId)
					   {
						   $("#loading_div").css("display","none");
						   continue;
					   }
					   oldest_message_id = myMsg[i].messageId;
					   var str = '<dl class="feed_list"><dt class="face"><a href="javascript:void(0);"><img src="';
					   if(myMsg[i].user.image!=null){
						   str = str + myMsg[i].user.image;
					   }else{
						   str = str + 'homepage-img/upimg.png';
					   }
					   str = str + '" width="50" height="50"></a></dt><dd class="flcontent"><p class="fltext"><a href="userpage.action?hisId=' + myMsg[i].user.userId + '" class="author_name">' + myMsg[i].user.nickname + '</a>:&nbsp;' + myMsg[i].text + '</p>';
					   if(myMsg[i].image!=null){
						   str = str + '<p><div style="margin-left:10px; margin-top:5px;"><img src="' + myMsg[i].image + '" style="width:300px"></div></p>';
					   }
					   if(myMsg[i].sourceId!=-1){
						   str = str + '<p><div class="previous_div"><p class="fltext"><a href="userpage.action?hisId=' + myMsg[i].original.user.userId + '" class="author_name">@' + myMsg[i].original.user.nickname + '</a>:&nbsp;' + myMsg[i].original.text + '</p>';
						   if(myMsg[i].original.image!=null){
							   str = str + '<p><div style="margin-left:20px; margin-top:5px"><img src="' + myMsg[i].original.image + '" width="300"></div></p>';
						   }
						   str = str + '<p class="fltime"><span><a href="javascript:void(0);" name="' + myMsg[i].original.messageId + '"  onClick="forward(this.name)">Forward</a><i class="W_vline">&nbsp;|&nbsp;</i><a href="javascript:void(0);" name="' + myMsg[i].messageId + '" onClick="comment(this.name,false,' + myMsg[i].original.messageId + ')">Comment</a></span>' + myMsg[i].original.timeDescription + '&nbsp;&nbsp;&nbsp;';
						   if(myMsg[i].original.position!=null && myMsg[i].original.position!=""){
							   str = str + 'From ' + myMsg[i].original.position;
						   }
						   str = str + '</p><div id="commentlist_pre_' + myMsg[i].messageId + '" class="commentlist"></div></div></p>';
					   }
					   str = str + '<p class="fltime"><span><a href="javascript:void(0);" name="' + myMsg[i].messageId + '" onClick="forward(this.name)">Forward</a><i class="W_vline">&nbsp;|&nbsp;</i><a href="javascript:void(0);" name="' + myMsg[i].messageId + '" onClick="comment(this.name,true)">Comment</a></span>' + myMsg[i].timeDescription + '&nbsp;&nbsp;&nbsp;';
					   if(myMsg[i].position!=null && myMsg[i].position!=""){
						   str = str + 'From ' + myMsg[i].position;
					   }
					   str = str + '</p><div id="commentlist_' + myMsg[i].messageId + '" class="commentlist"></div></dd></dl>';
					   var mainlist = document.getElementById("mainlist");
					   mainlist.innerHTML = mainlist.innerHTML + str;
					   str = null;
					   document.all.conr.style.height=document.all.conl.offsetHeight+"px";
				   }
		   });
   }
   
   
   
var isClick=false;
				$("body").click(function(event){
					if($("#publish_emotion").css("display")=="block"
					&&(event.clientX<$("#publish_emotion").offset().left||event.clientX>$("#publish_emotion").offset().left+$("#emotion_button").width()
					||event.clientY<$("#publish_emotion").offset().top||event.clientY>$("#emotion_button").offset().top+$("#emotion_button").height())){
						if(!isClick){
							$("#emotion_img").attr("src","homepage-img/emotion_0.png");
							$("#publish_emotion").fadeOut(700);
						} else {
							isClick = false;
						}
					}
				});
				$("#emotion_button").click(function(event){
					$("#publish_emotion").css("left",$("#emotion_button").offset().left);
					$("#publish_emotion").css("top",$("#emotion_button").offset().top+$("#emotion_button").height());
					$("#emotion_img").attr("src","homepage-img/emotion_1.png");
					$("#publish_emotion").fadeIn(700);
					isClick=true;
				});
				$("#publish_emotion").find("a").click(function( event){
				    var insertCon=$(this).attr("title");
				    $("#publish_text").insertContent("["+insertCon+"]");
					$("#emotion_img").attr("src","homepage-img/emotion_0.png");
					$("#publish_emotion").fadeOut(700);
				});


$("#image_file").change(function(event){
			$("#publish_image_close").click(function(e) {
                $("#publish_image").fadeOut(700);
            });
			$("#publish_image").css("left",$("#image_button").offset().left);
			$("#publish_image").css("top",$("#image_button").offset().top+$("#image_button").height());
			$("#image_img").attr("src","homepage-img/image_1.png");
			$("#publish_image").fadeIn(700);
		});
		$("body").click(function(event){
					if($("#publish_image").css("display")=="block"
					&&(event.clientX<$("#publish_image").offset().left||event.clientX>$("#publish_image").offset().left+$("#publish_image").width()
					||event.clientY<$("#publish_image").offset().top||event.clientY>$("#image_button").offset().top+$("#image_button").height())){
						$("#publish_image").fadeOut(700);
					}
				});
				
				
var isClick = false;
		var isLocation = false;
		$("#location_button").click(function(){
			isClick=true;
			if(isLocation){
				$("#location_img").attr("src","homepage-img/location_0.png");
				$("#positionInfo").text("");
				isLocation = false;
			} else {
				$("#publish_location").css("left",$("#location_button").offset().left);
				$("#publish_location").css("top",$("#location_button").offset().top+$("#location_button").height());
				$("#location_img").attr("src","homepage-img/location_1.png");
				$("#publish_location").fadeIn(700);
				isLocation = true;
			}
		});
		$("body").click(function(event){
					if($("#publish_location").css("display")=="block"
					&&(event.clientX<$("#publish_location").offset().left||event.clientX>$("#publish_location").offset().left+$("#publish_location").width()
					||event.clientY<$("#publish_location").offset().top||event.clientY>$("#location_button").offset().top+$("#location_button").height())){
						if(!isClick){
							$("#publish_location").fadeOut(700);
						} else {
							isClick = false;
						}
					}
				});
			$("#location_button").click(function(e) {
                if(navigator.geolocation){
					navigator.geolocation.getCurrentPosition(show_map,handle_error ,null);
				}
            });
			function handle_error(){
			}
			function show_map(position) {
				var coords = position.coords;
				$.get("googlePosition.action?latitude="+coords.latitude+"&longitude="+coords.longitude,null,function(result){
					$("#google_map").attr("src","http://maps.google.com/maps/api/staticmap?center=" + coords.latitude + "," + coords.longitude + "&zoom=12&size=200x200&maptype=roadmap&markers=color:red%7Clabel:A%7C" + coords.latitude + "," + coords.longitude + "&sensor=false");
					var response = JSON.parse(result.position);
					$("#position_mark_logo").css("display","none");
					document.getElementById("positionInfo").textContent = response.results[0].address_components[1].long_name + ", " + response.results[0].address_components[2].long_name;
				});
			}
			
			
			
function forward_send(){
	if($("#forward_text").val()==""){
		return;
	}
				var a=$("#forward_message_id").val();
				var b=$("#forward_text").val();
				closing();
				$.post("forward.action",{"messageid":$("#forward_message_id").val(),"text":$("#forward_text").val(),"upload":"null"},function(result){
					var str = '<dt class="face"><a href="javascript:void(0);"><img src="';
					   if(result.lastPost.user.image!=null){
						   str = str + result.lastPost.user.image;
					   }else{
						   str = str + 'homepage-img/upimg.png';
					   }
					   str = str + '" width="50" height="50"></a></dt><dd class="flcontent"><p class="fltext"><a href="userpage.action?hisId=' + result.lastPost.user.userId + '" class="author_name">' + result.lastPost.user.nickname + '</a>:&nbsp;' + result.lastPost.text + '</p>';
					   if(result.lastPost.image!=null){
						   str = str + '<p><div style="margin-left:10px; margin-top:5px;"><img src="' + result.lastPost.image + '" style="width:300px"></div></p>';
					   }
					   if(result.lastPost.sourceId!=-1){
						   str = str + '<p><div class="previous_div"><p class="fltext"><a href="userpage.action?hisId=' + result.lastPost.original.user.userId + '" class="author_name">@' + result.lastPost.original.user.nickname + '</a>:&nbsp;' + result.lastPost.original.text + '</p>';
						   if(result.lastPost.original.image!=null){
							   str = str + '<p><div style="margin-left:20px; margin-top:5px"><img src="' + result.lastPost.original.image + '" width="300"></div></p>';
						   }
						   str = str + '<p class="fltime"><span><a href="javascript:void(0);" name="' + result.lastPost.original.messageId + '"  onClick="forward(this.name)">Forward</a><i class="W_vline">&nbsp;|&nbsp;</i><a href="javascript:void(0);" name="' + result.lastPost.messageId + '" onClick="comment(this.name,false,' + result.lastPost.original.messageId + ')">Comment</a></span>' + result.lastPost.original.timeDescription + '&nbsp;&nbsp;&nbsp;';
						   if(result.lastPost.original.position!=null && result.lastPost.original.position!=""){
							   str = str + 'From ' + result.lastPost.original.position;
						   }
						   str = str + '</p><div id="commentlist_pre_' + result.lastPost.messageId + '" class="commentlist"></div></div></p>';
					   }
					   str = str + '<p class="fltime"><span><a href="javascript:void(0);" name="' + result.lastPost.messageId + '" onClick="forward(this.name)">Forward</a><i class="W_vline">&nbsp;|&nbsp;</i><a href="javascript:void(0);" name="' + result.lastPost.messageId + '" onClick="comment(this.name,true)">Comment</a></span>' + result.lastPost.timeDescription + '&nbsp;&nbsp;&nbsp;';
					   if(result.lastPost.position!=null && result.lastPost.position!=""){
						   str = str + 'From ' + result.lastPost.position;
					   }
					   str = str + '</p><div id="commentlist_' + result.lastPost.messageId + '" class="commentlist"></div></dd>';
					   var i = document.createElement("dl");
					   i.className="feed_list";
					   i.innerHTML=str;
					   i.id = "message_" + result.lastPost.messageId;
					   var mainlist = document.getElementById("mainlist");
					   mainlist.insertBefore(i,mainlist.firstChild);
					   var height=i.offsetHeight+100;
					   i.style.display = "none";
					   $("#mainlist").animate({marginTop:height+"px"},600,"swing",function(){
						   mainlist.style.marginTop="70px";
						   $("#" + "message_" + result.lastPost.messageId).fadeIn(1000);
					   });
					   $("#posts_amount").text($("#posts_amount").text()-0+1);
					   document.all.conr.style.height=document.all.conl.offsetHeight+"px";
				});
				$("#forward_text").val("");
			}

var isCommentActive = false;
var activeComment = "";
var activeCommentMsgId;
function comment(id,isnew,oldid){
	var elementname;
	if(isnew){
		elementname = "commentlist_" + id;
	} else {
		elementname = "commentlist_pre_" + id;
		id=oldid;
	}
	if(isCommentActive) {
		$("#" + activeComment).slideUp(1000);
		$("#" + activeComment).html("")
		document.all.conr.style.height=document.all.conl.offsetHeight+"px";
		isCommentActive = false;
		if(elementname == activeComment) {
			return;
		}
	}
	$("#" + elementname).html('<div id="loading_comment_div" class="loading_comment_div"><div style="line-height:40px"><img src="homepage-img/loading.gif">&nbsp;&nbsp;Loading&nbsp;...</div></div>');
	activeCommentMsgId = id;
	$.get("getComment.action",{"messageId":id},function(result){
				var str = '<dl class="commentdl_input"><form><textarea id="comment_submit"></textarea><input type="button" class="comment_button" value="Comment" onClick="handleCommentSubmit()"><div style="clear:right"></div><input type="hidden" name="maxlength" value="140" /></form></dl><div style="clear:left"></div>';
		myComments = result.comments;
		for(var i=0;i<myComments.length;i++){
			str = str + '<dl class="commentdl_list"><dt class="commentdt"><img src="' + myComments[i].user.image + '" style="width:30px; height:30px"/></dt><dd class="commentdd"><p style="margin:0"><a href="userpage.action?hisId=' + myComments[i].user.userId + '" class="author_name_comment">@' + myComments[i].user.nickname + '</a>:&nbsp;' + myComments[i].text + '</p><p style="margin:0; text-align:right">' + myComments[i].timeDescription + '</p></dd></dl>';
		}
		$("#" + elementname).slideUp(1000);
		$("#" + elementname).html(str);
		$("#" + elementname).slideDown(1000);
		isCommentActive = true;
		activeComment = elementname;
		document.all.conr.style.height=document.all.conl.offsetHeight+"px";
		$("#comment_submit").keyup(function(e) {
  		  $("#comment_submit").height($("#comment_submit").scrollTop()+$("#comment_submit").height());
		});
		$("#comment_submit").maxlength({
			'useInput': true
		});
	});
}
function handleCommentSubmit() {
	var commentText = document.getElementById("comment_submit");
	if(commentText.value==""){
		return;
	}
    $.post("sendComment.action",{"messageId":activeCommentMsgId,"commentString":commentText.value},function(result){
		commentText.value="";
		var newComment = result._publishedComment;
		var clist = document.getElementById(activeComment);
		var i = document.createElement("dl");
		i.id = "commentlist_" + newComment.commentId;
		i.className = "commentdl_list";
		i.style.display = "none";
		i.innerHTML = '<dt class="commentdt"><img src="' + newComment.user.image + '" style="width:30px; height:30px"/></dt><dd class="commentdd"><p style="margin:0"><a href="userpage.action?hisId=' + newComment.user.userId + '" class="author_name_comment">@' + newComment.user.nickname + '</a>:&nbsp;' + newComment.text + '</p><p style="margin:0; text-align:right">' + newComment.timeDescription + '</p></dd>';
		clist.insertBefore(i,clist.childNodes[1]);
		$("#" + "commentlist_" + newComment.commentId).fadeIn(1000);
		document.all.conr.style.height=document.all.conl.offsetHeight+"px";
	});
}