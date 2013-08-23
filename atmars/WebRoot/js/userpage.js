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
	var userpage=document.getElementById("mypage");
	var url=document.URL;
	userpage.innerHTML='<a href="'+url+'" >'+url+'</a>';
	pageRefresh();
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
	   $.get("getOriginalMessages?cursor=" + oldest_message_id + "&userId=" + $(".container").attr("id"),null,function(response){
		   $("#loading_div").css("display","block");
				   var myMsg = response.originalList;
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
	
function forward_send(){
	if($("#forward_text").val()==""){
		return;
	}
				var a=$("#forward_message_id").val();
				var b=$("#forward_text").val();
				closing();
				$.post("forward.action",{"messageid":$("#forward_message_id").val(),"text":$("#forward_text").val(),"upload":"null"},function(result){});
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