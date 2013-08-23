package com.weibo.mars.JSON;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONTest {
	String urlStr="";
	String ticket="";
	String email="";
	public JSONObject json;
	public String quote="";
	
//	public void JSONExecute() throws IOException{
//		urlStr = "http://asimoov-pc:8081/atmars/MobileLogin?email=8088381@qq.com&password=123456";
//		URL url = null;
//		
//			try {
//				url = new URL(urlStr);
//			} catch (MalformedURLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		
//        HttpURLConnection conn =(HttpURLConnection) url.openConnection();
//				
//        BufferedReader reader = null;
//		
//		reader = new BufferedReader(
//	//	new InputStreamReader(conn.getInputStream()));
//				new InputStreamReader((InputStream) conn.getContent()));
//	//	conn.
//		
//        
//		
//
//		quote = reader.readLine();
//	
//		
//			// TODO Auto-generated catch block
//			
//        System.out.print(quote);
//        
//
//		try {
//			json=new JSONObject(quote);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//mjson=json.getJSONObject("user");
//        //if(mjson!=null){
//        try {
//			ticket=json.getString("ticket");
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        
//        try {
//			email=json.getString("email");
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        
//	}
	
	public static String getJSONLastVideos() throws Exception{
		 // List<Video> videos = new ArrayList<Video>();
		  String path ="http://asimoov-pc:8081/atmars/MobileLogin?email=8088381@qq.com&password=123456";
		  URL url = new URL(path);
		  HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		  conn.setReadTimeout(5*1000);
		  conn.setRequestMethod("GET");
		  InputStream inStream = conn.getInputStream();
		  byte[] data = StreamTool.readInputStream(inStream);
		  String json = new String(data);
		//  JSONArray array = new JSONArray(json);
		//  for(int i=0 ; i < array.length() ; i++){
		//   JSONObject item = array.getJSONObject(i);
		//   int id = item.getInt("id");
		//   String title = item.getString("title");
		//   int timelength = item.getInt("timelength");
		//   videos.add(new Video(id, title, timelength));
		  
		  return json;
		 }
	

}




