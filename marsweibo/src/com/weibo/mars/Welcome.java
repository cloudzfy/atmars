package com.weibo.mars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Welcome extends Activity{
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        Start();
       
    }
	    public void Start() {
	                new Thread() {
	                        public void run() {
	                                try {
	                                        Thread.sleep(2500);
	                                } catch (InterruptedException e) {
	                                        e.printStackTrace();
	                                }
	                                Intent intent = new Intent();
	                                intent.setClass(Welcome.this, MarsweiboActivity.class);
	                                startActivity(intent);
	                                finish();
	                        }
	                }.start();
	        }
	}


