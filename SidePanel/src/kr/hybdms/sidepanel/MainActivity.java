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

import com.actionbarsherlock.app.SherlockActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ToggleButton;


public class MainActivity extends SherlockActivity implements OnClickListener {
	ToggleButton tg;
	Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 tg = (ToggleButton) findViewById(R.id.toggleButton1);
        tg.setOnClickListener(this);
        btn = (Button) findViewById(R.id.button1);
        btn.setOnClickListener(this);
	}
	 @Override
     public void onClick(View v) {
     // TODO Auto-generated method stub
     if((tg.isChecked()))
     {
         //When The Toggle Enabled
         System.out.println("Enabled");
         startService(new Intent(this, TouchDetectService.class));
     }
     else if(v == btn)
     {
    	 Intent appinfo = new Intent(MainActivity.this, Appinfo.class); 
    	 startActivity(appinfo);
     }
     else
     {
         //When The Toggle Disabled
             System.out.println("Disabled");
             stopService(new Intent(this, TouchDetectService.class));
     }
   
     } 
	 

}