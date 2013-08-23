package com.weibo.mars.util;

public class ConvertPostUtils {
	static public String replaceFace(String text)
	{
		 String[] faces={"smile","naughty","cry","angry","embarrass","crazy","effort","despise","lovely","laugh","titter","surprise","orz","unhappy","wronged"};

	    for(String face:faces)
	     {
	    	String fullface="["+face+"]";
	    	String imgsrc="<img src=\"Face/"+face+".gif\"/>";
	    	text=text.replace(fullface, imgsrc);
	     }//for
	    return text;
	}
	static public String replaceAtMarkToHTML(String input)
	{
		return input;
	}
}
