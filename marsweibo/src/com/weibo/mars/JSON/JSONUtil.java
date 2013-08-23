package com.weibo.mars.JSON;


import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.UnsupportedEncodingException;  
import java.util.Map;
  
import org.apache.http.HttpEntity;  
import org.apache.http.HttpResponse;  
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;  
import org.apache.http.params.BasicHttpParams;  
import org.apache.http.protocol.HTTP;  
import org.json.JSONArray;
import org.json.JSONException;  
import org.json.JSONObject;  import android.util.Log;  



public class JSONUtil {
	
	private static final String TAG = "JSONUtil";
	
	
	public static String URLhead="http://192.168.2.155:8080/atmars/";
	public static String email="";
	public static String password="";
	public static String userid="";
	public static String ticket="";
	public static String text="";
	public static String fw="";
	public static String oldestmessageid="";
	
	
	public static JSONObject getJSON(String url) throws JSONException, Exception { 
		return new JSONObject(getRequest(url)); 
	}
	
	
	protected static String getRequest(String url) throws Exception {
		// TODO Auto-generated method stub
		return getRequest(url, new DefaultHttpClient(new BasicHttpParams()));
	}

	protected static String getRequest(String url, DefaultHttpClient client) throws Exception {  
		String result = null;
		int statusCode = 0;
		HttpPost getMethod = new HttpPost(url);
		Log.d(TAG, "do the getRequest,url="+url+"");
		try {
			HttpResponse httpResponse = client.execute(getMethod); 
			statusCode = httpResponse.getStatusLine().getStatusCode();
			Log.d(TAG, "statuscode = "+statusCode); 
			result = retrieveInputStream(httpResponse.getEntity()); 
		}catch (Exception e) {
			Log.e(TAG, e.getMessage());
			throw new Exception(e); 
		}finally {
			getMethod.abort();
		}
		return result;
	}
	
	 protected static String retrieveInputStream(HttpEntity httpEntity) { 
		 int length = (int) httpEntity.getContentLength();         
	        //the number of bytes of the content, or a negative number if unknown. If the content length is known but exceeds Long.MAX_VALUE, a negative number is returned.  
	        //length==-1，下面这句报错，println needs a message  
	        if (length < 0) length = 10000;  
	        StringBuffer stringBuffer = new StringBuffer(length);  
	        try {  
	            InputStreamReader inputStreamReader = new InputStreamReader(httpEntity.getContent(), HTTP.UTF_8);  
	            char buffer[] = new char[length];  
	            int count;  
	            while ((count = inputStreamReader.read(buffer, 0, length - 1)) > 0) {  
	                stringBuffer.append(buffer, 0, count);  
	            }  
	        } catch (UnsupportedEncodingException e) {  
	            Log.e(TAG, e.getMessage());  
	        } catch (IllegalStateException e) {  
	            Log.e(TAG, e.getMessage());  
	        } catch (IOException e) {  
	            Log.e(TAG, e.getMessage());  
	        }  
	        return stringBuffer.toString();  
	    
	 }
	
	public static String JSONLogin(){
		try {
			JSONObject mObject=getJSON(URLhead+"MobileLogin?email="+email+"&password="+password);
			ticket=mObject.getString("ticket");
			JSONObject mUser=mObject.getJSONObject("user");
			userid=mUser.getString("userId");
			return ticket;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static JSONArray JSONMsglist() throws JSONException, Exception{
		JSONObject msgObject=getJSON(URLhead+"MobileGetMyMessages?userId="+userid+"&ticket="+ticket);
		JSONArray msgArray=msgObject.getJSONArray("myMessages");
		
		return msgArray;
	}
	
	        
	public static JSONObject JSONSend() throws JSONException, Exception{
		
			String newtext=text.replaceAll(" ", "+")+fw.replaceAll(" ", "+");
			JSONObject msgObject=getJSON(URLhead+"MobileSendMessage?userId="+userid+"&ticket="+ticket+"&text="+newtext);
			return msgObject;
		
	}
	
	
	

}
