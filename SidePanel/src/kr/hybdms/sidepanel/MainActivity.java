package kr.hybdms.sidepanel;

import com.actionbarsherlock.app.SherlockActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ToggleButton;
import android.view.View.OnClickListener;


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