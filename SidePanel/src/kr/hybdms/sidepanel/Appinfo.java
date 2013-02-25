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

import com.actionbarsherlock.app.SherlockActivity;

import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Appinfo extends SherlockActivity implements OnClickListener {
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_appinfo);       
	        Button a=(Button)findViewById(R.id.button3);
	        Button b=(Button)findViewById(R.id.button2);
	        Button c=(Button)findViewById(R.id.button1);
	        a.setOnClickListener(this);
	        b.setOnClickListener(this);
	        c.setOnClickListener(this);
}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()) {
	           case R.id.button1:
	        	  Intent thirdparty = new Intent(Appinfo.this, Notices.class); 
	   	    	  startActivity(thirdparty);	 
	           break;
	           case R.id.button2:
	             Intent feedback = new Intent(Intent.ACTION_SENDTO);
	        	  feedback.setData(Uri.parse("mailto:sukso96100@gmail.com"));
	   	    	  startActivity(feedback);
	           break;
	           case R.id.button3:
	             Intent update = new Intent(Intent.ACTION_VIEW);
	             update.setData(Uri.parse("http://play.google.com/store/apps/details?id=kr.hybdms.sidepanel"));
	             startActivity(update);
			}
		}
}