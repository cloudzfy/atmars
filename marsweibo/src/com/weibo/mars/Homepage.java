package com.weibo.mars;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.weibo.mars.JSON.JSONUtil;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.EditText;
import android.widget.SimpleAdapter.ViewBinder;

public class Homepage extends Activity{
	
	private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	private int currentItem;
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//���ý������
		setContentView(R.layout.homepage);
		final EditText mytext=(EditText)findViewById(R.id.editmessage);
		//mytext.setText(null);
		ListView msglist=(ListView)findViewById(R.id.msglist);
		Button send=(Button)findViewById(R.id.sendmessage);
		
		//����listview������������
		SimpleAdapter adapter;
		try {
			adapter = new SimpleAdapter(this,getData(),R.layout.weibolist,
					new String[]{"title","info","img","time"},
					new int[]{R.id.title,R.id.info,R.id.img,R.id.time});
			msglist.setAdapter(adapter);
			adapter.setViewBinder(new ViewBinder() {
				
				public boolean setViewValue(View view, Object data,
						String textRepresentation) {
					//�ж��Ƿ�Ϊ����Ҫ����Ķ���
					if(view instanceof ImageView  && data instanceof Bitmap){
						ImageView iv = (ImageView) view;
					
						iv.setImageBitmap((Bitmap) data);
						return true;
					}else
					return false;
				}
			});
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		
		//setListAdapter(adapter);
		
		//������΢����ť�Ĳ���
		send.setOnClickListener(new OnClickListener(){

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				JSONUtil.text=mytext.getText().toString();

				try {
					JSONObject msendtext=JSONUtil.JSONSend();
					//if(!msendtext.isNull("forwordId")) refresh();
					//else setTitle("����ʧ�ܣ�");
					refresh();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//ˢ��΢���б�
				
			}

			//ˢ��activity�Ĳ���
			
			
		});
		
		//�б����ѡ�в���
		msglist.setOnItemClickListener(new OnItemClickListener() {  
			  
           	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
           		
           		int count=arg2+1;
				setTitle("ѡ���˵�"+count+"��΢��");  
				
			}  
        });  
		
		//�б���ĳ�������
		msglist.setOnItemLongClickListener(new OnItemLongClickListener(){

			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				int count=arg2+1;
				currentItem=count;
				setTitle("ѡ���˵�"+count+"��΢��");
				return false;
			}
			
		});
		
		
		
		//�б���ĵ����˵�
		msglist.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {  
            
            public void onCreateContextMenu(ContextMenu menu, View v,
					ContextMenuInfo menuInfo) {
				// TODO Auto-generated method stub
				menu.setHeaderTitle("����ѡ��"); 
				//���������˵���Ĳ���
                menu.add(0, 0, 0, "ת��").setOnMenuItemClickListener(new OnMenuItemClickListener(){

					public boolean onMenuItemClick(MenuItem arg0) {
						// TODO Auto-generated method stub
						try {
							JSONArray nmsglist=JSONUtil.JSONMsglist();
							JSONObject msgObject=(JSONObject)nmsglist.opt(currentItem-1);
							String username=msgObject.getJSONObject("user").getString("nickname");
							String newtext=msgObject.getString("text");
							JSONUtil.fw="//@"+username+":"+newtext;
							setTitle(arg0+"��"+currentItem+"��΢��");
						
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return false;
					}
					
                }); 
                menu.add(0, 1, 0, "ˢ��").setOnMenuItemClickListener(new OnMenuItemClickListener(){

					public boolean onMenuItemClick(MenuItem item) {
						// TODO Auto-generated method stub
						refresh();
						return false;
					}
                	
                });
                
			}  
        }); 
	}

	private void refresh() {
		// TODO Auto-generated method stub
		finish();
		Intent intent = new Intent(Homepage.this,Homepage.class);
		startActivity(intent);
	}

	private List<Map<String, Object>> getData() throws JSONException, Exception {
		
		JSONArray msglist=JSONUtil.JSONMsglist();
		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("id", 1);
//		map.put("title", "G1");
//		map.put("info", "���������ܺ�");
//		map.put("img", R.drawable.i1);
//		map.put("time", "2����ǰ");
//		list.add(map);
//
//		map = new HashMap<String, Object>();
//		map.put("id", 2);
//		map.put("title", "G2");
//		map.put("info", "��ӭʹ��marsweibo");
//		map.put("img", R.drawable.i2);
//		map.put("time", "10����ǰ");
//		list.add(map);
//
//		map = new HashMap<String, Object>();
//		map.put("id", 3);
//		map.put("title", "G3");
//		map.put("info", "����۲�");
//		map.put("img", R.drawable.i3);
//		map.put("time", "����");
//		list.add(map);
		
		int count=msglist.length();
		
		for(int i=0;i<count;i++){
			JSONObject msgObject=(JSONObject)msglist.opt(i);
			map = new HashMap<String, Object>();
			map.put("id", i);
			map.put("title", msgObject.getJSONObject("user").getString("nickname"));
			map.put("info", msgObject.getString("text"));
			map.put("img", getBitmap(JSONUtil.URLhead+msgObject.getJSONObject("user").getString("image")));
			map.put("time", msgObject.getString("timeDescription"));
			list.add(map);
		}
		return list;
	}
	
	
	 @Override  
	 public boolean onContextItemSelected(MenuItem item) {  
	        //setTitle("ѡ����"+item+"΢��");   
	        return super.onContextItemSelected(item);  
	    }  
	 
	 
	 
	 public Bitmap getBitmap(String imageUrl){  
	        Bitmap mBitmap = null;  
	        try {  
	            URL url = new URL(imageUrl);  
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
	            InputStream is = conn.getInputStream();  
	            mBitmap = BitmapFactory.decodeStream(is);  
	              
	        } catch (MalformedURLException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	        Bitmap newmBitmap=transImage(mBitmap,72,72,2);
	        return newmBitmap;  
	    }
	 
	 public Bitmap transImage(Bitmap bitmap, int width, int height, int quality)
		{
			
			int bitmapWidth = bitmap.getWidth();
			int bitmapHeight = bitmap.getHeight();
			// ����ͼƬ�ĳߴ�
			float scaleWidth = (float) width / bitmapWidth;
			float scaleHeight = (float) height / bitmapHeight;
			Matrix matrix = new Matrix();
			matrix.postScale(scaleWidth, scaleHeight);
			// �������ź��Bitmap����
			Bitmap resizeBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmapWidth, bitmapHeight, matrix, false);
			return resizeBitmap;
		}

}
