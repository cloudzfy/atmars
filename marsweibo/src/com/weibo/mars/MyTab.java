package com.weibo.mars;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

public class MyTab extends TabActivity{
	TabHost mTabHost;
	@Override
	 public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        mTabHost=getTabHost();
        mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator("TAB1").setContent(R.id.tabtextview1));
        mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator("TAB2").setContent(R.id.tabtextview2));
        
        mTabHost.setCurrentTab(0);
        
        mTabHost.setOnTabChangedListener(new OnTabChangeListener(){
        	public void onTabChanged(String TabId){
        		Dialog dialog=new AlertDialog.Builder(MyTab.this).setTitle("提示").setMessage("当前选中："+TabId+"标签")
        				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
							
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								dialog.cancel();
							}
						}).create();
        		dialog.show();
        	}
        });
        
	}
	

}
