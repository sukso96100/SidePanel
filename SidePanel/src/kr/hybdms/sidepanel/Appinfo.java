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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Appinfo extends SherlockActivity implements OnItemClickListener {
	 private ListView mListView;
	 private ArrayList<String> mArrayList;
	 private ArrayAdapter<String> mArrayAdpter;
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_appinfo);       
	        mListView = (ListView)findViewById(R.id.listView1); //ListView 가져오기	        
	        mArrayList = new ArrayList<String>(); //List 저장할 array
	        //Adapter 생성
	        mArrayAdpter = new ArrayAdapter<String>(this, 
	              android.R.layout.simple_list_item_1, mArrayList);
	        mListView.setAdapter(mArrayAdpter);//List에 adapter등록
	        mListView.setOnItemClickListener(itemClickListenerOfLanguageList);
	    }
	 @Override
	 protected void onResume() {
		  // TODO Auto-generated method stub
		  super.onResume();
		  Resources res = getResources();
		  String[] menuString = res.getStringArray(R.array.appinfo_array);
		  for(String s: menuString){
		   mArrayList.add(s);
		  }
		 }
	 private OnItemClickListener itemClickListenerOfLanguageList = new OnItemClickListener()
	    {
	        public void onItemClick(AdapterView<?> adapterView, View clickedView, int position, long id)
	        {
			// TODO Auto-generated method stub
	  
	  if(position == 1){
		Intent intent = new Intent(Intent.ACTION_VIEW);
     intent.setData(Uri.parse("http://play.google.com"));
     startActivity(intent);
	  }
	  else if(position == 2){
	  Intent it2 = new Intent(Intent.ACTION_VIEW);
  	it2.setData(Uri.parse("http://hybdms.blogspot.com"));
  	startActivity(it2);
	        }
	  else if(position == 4){
		  Intent it = new Intent(Intent.ACTION_SENDTO);
	    	it.setData(Uri.parse("sukso96100@gmail.com"));
	    	startActivity(it);
	  }
	  else if(position == 5){
		  Intent intent5 = new Intent(Appinfo.this, Notices.class); 
	    	 startActivity(intent5);
		  }
 }
};

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
}