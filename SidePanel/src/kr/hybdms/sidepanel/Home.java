package kr.hybdms.sidepanel;

import java.util.ArrayList;

import com.actionbarsherlock.app.SherlockActivity;

import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import kr.hybdms.sidepanel.R;

public class Home extends SherlockActivity {
	 private ListView mListView;
	 private ArrayList<String> mArrayList;
	 private ArrayAdapter<String> mArrayAdpter;
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_home);       
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
		  String[] menuString = res.getStringArray(R.array.home_item_array);
		  for(String s: menuString){
		   mArrayList.add(s);
		  }
		 }
	 private OnItemClickListener itemClickListenerOfLanguageList = new OnItemClickListener()
	    {
		 @Override
	        public void onItemClick(AdapterView<?> adapterView, View clickedView, int position, long id)
	        {
			// TODO Auto-generated method stub
			 
			 if(position == 0){
				 Intent intent1 = new Intent(Home.this, Settings.class); 
		    	 startActivity(intent1);
				        }
			 else if(position == 2){
				  Intent it2 = new Intent(Intent.ACTION_VIEW);
			  	it2.setData(Uri.parse("https://play.google.com/store/apps/details?id=kr.hybdms.sidepanel"));
			  	startActivity(it2);
				        }
				  else if(position == 3){
					  Intent it = new Intent(Intent.ACTION_VIEW);
				    	it.setData(Uri.parse("http://hybdms.blogspot.kr"));
				    	startActivity(it);
				  }
				  else if(position == 4){
					  Intent intent5 = new Intent(Home.this, Appinfo.class); 
				    	 startActivity(intent5);
					  }
	  
}
};


}