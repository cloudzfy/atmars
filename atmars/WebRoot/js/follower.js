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