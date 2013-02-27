/*
 * SidePanel Application For Android
 * Copyright 2013 Young Bin Han
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package kr.hybdms.sidepanel;


import java.util.ArrayList;
import java.util.List;


import kr.hybdms.sidepanel.PanelArrayAdapter;
import kr.hybdms.sidepanel.R;

import kr.hybdms.sidepanel.util.SystemUiHider;


import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class SidePanel extends Activity {
	 private ArrayList<PanelItemDetail> rowItems = null;
	 private ListView listView;
	 private ArrayList<String> packageName = null;
	 private ArrayList<String> className = null;
	 
	 protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  boolean rightpanel = getSharedPreferences(getPackageName() + "_preferences", Context.MODE_PRIVATE).getBoolean("panelpos_right", true);
	  if(rightpanel){
	  overridePendingTransition(R.anim.left_slide_in_fast, 0);
	  setContentView(R.layout.right_side_panel);
	  }
	  else
	  {
		  overridePendingTransition(R.anim.right_slide_in_fast, 0);
		  setContentView(R.layout.activity_left_side_panel);
	  }
	  ImageView imgbtn = (ImageView) findViewById(R.id.transparentbackground);
	  ImageView panelbg = (ImageView) findViewById(R.id.panelbackground);
	  listView = (ListView)findViewById(R.id.panelcontents);
	  packageName = new ArrayList<String>();
	  className = new ArrayList<String>();
	  
	  
	  ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
	  List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(30);
	  rowItems = new ArrayList<PanelItemDetail>();
	  PackageManager pacMgr = getPackageManager();
	  
	  
	  for (ActivityManager.RunningTaskInfo runningTask : tasks) {
	   try {
	    rowItems.add(new PanelItemDetail(pacMgr.getApplicationIcon(runningTask.topActivity.getPackageName())));
	    packageName.add(runningTask.topActivity.getPackageName());
	    className.add(runningTask.topActivity.getClassName());
	    
	    Log.d("#@#", "getPackageName = " + runningTask.topActivity.getPackageName());
	    Log.d("#@#", "getClassName = " + runningTask.topActivity.getClassName());
	   } catch (NameNotFoundException e) {
	    e.printStackTrace();
	   }
	  }
	  
	  SharedPreferences myPreference = PreferenceManager.getDefaultSharedPreferences(this);
	  String itembg = myPreference.getString("itembg_list", "");
	  
	  if(itembg.equals("defaults"))
	  {
	  PanelArrayAdapter adapter = new PanelArrayAdapter(this,R.layout.panelrow_default, rowItems);
	  listView.setAdapter(adapter);
	  }
	  else if(itembg.equals("dark"))
	  {
	  PanelArrayAdapter adapter = new PanelArrayAdapter(this,R.layout.panelrow_dark, rowItems);
	  listView.setAdapter(adapter);
	  }
	  else if(itembg.equals("light"))
	  {
	  PanelArrayAdapter adapter = new PanelArrayAdapter(this,R.layout.panelrow_light, rowItems);
	  listView.setAdapter(adapter);
	  }
	  else
	  {
	  PanelArrayAdapter adapter = new PanelArrayAdapter(this,R.layout.panelrow_none, rowItems);
	  listView.setAdapter(adapter);
	  }
	  listView.setOnItemClickListener(new OnItemClickListener() {
	   public void onItemClick(AdapterView<?> parent, View view, int postion, long id) {
	   
		   boolean rightpanel = getSharedPreferences(getPackageName() + "_preferences", Context.MODE_PRIVATE).getBoolean("panelpos_right", true);
		   
		   Intent taskintent = getPackageManager().getLaunchIntentForPackage(packageName.get(postion).toString());
	       startActivity(taskintent);
	       if(rightpanel){
	       overridePendingTransition(R.anim.right_slide_in, R.anim.zoom_out);
	       }
	       else
	       {
	    	   overridePendingTransition(R.anim.left_slide_in, R.anim.zoom_out);
	       }
	       finish();
	   } 
	  });
	  SharedPreferences panelbgpref = PreferenceManager.getDefaultSharedPreferences(this);
	  String panelbgset = panelbgpref.getString("panelbg_list", "");
	  
	  if(panelbgset.equals("light"))
	  {
		  panelbg.setImageResource(R.drawable.panelbg_light);
	  }
	  else
	  {
		  panelbg.setImageResource(R.drawable.panelbg);
	  }
	  
	  imgbtn.setOnClickListener(new View.OnClickListener(){
			 public void onClick(View v) {
				 if(v.getId() ==R.id.transparentbackground){
					 moveTaskToBack(true);
						finish();
				 }
			 } 
		  });
	 }
	 
}