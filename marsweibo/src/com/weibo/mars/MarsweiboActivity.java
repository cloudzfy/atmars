package com.weibo.mars;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.weibo.mars.JSON.JSONTest;
import com.weibo.mars.JSON.JSONUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MarsweiboActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button b1=(Button)findViewById(R.id.button1);
        final EditText memail=(EditText)findViewById(R.id.editText1);
        final EditText mpassword=(EditText)findViewById(R.id.editText2);
        
        b1.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String ticket="";
				Intent intent=new Intent();
				JSONUtil.email=memail.getText().toString();
				JSONUtil.password=mpassword.getText().toString();
				ticket=JSONUtil.JSONLogin();
				if(!ticket.isEmpty())	intent.setClass(MarsweiboActivity.this, Homepage.class);
				else	intent.setClass(MarsweiboActivity.this, MarsweiboActivity.class);
				startActivity(intent);
				finish();
				
			}
		});
    }
}