/*
 * atmars, An Implementation of a Micro Blog
 * Copyright (C) 2013, Cloudzfy
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.atmars.utils;

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
