package kr.hybdms.sidepanel;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kr.hybdms.sidepanel.PanelArrayAdapter;
import kr.hybdms.sidepanel.R;
import kr.hybdms.sidepanel.Utilities;

import kr.hybdms.sidepanel.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class LeftSidePanel extends Activity {
    private ListView mListAppInfo;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set layout for the main screen
        setContentView(R.layout.activity_left_side_panel);        
        // load list application
        mListAppInfo = (ListView)findViewById(R.id.panelcontents);
        // create new adapter
        PanelArrayAdapter adapter = new PanelArrayAdapter(this, Utilities.getInstalledApplication(this), getPackageManager());
        // set adapter to list view
        mListAppInfo.setAdapter(adapter);
        // implement event when an item on list view is selected
        mListAppInfo.setOnItemClickListener(new OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
        		// get the list adapter
        		PanelArrayAdapter appInfoAdapter = (PanelArrayAdapter)parent.getAdapter();
        		// get selected item on the list
        		ApplicationInfo appInfo = (ApplicationInfo)appInfoAdapter.getItem(pos);
        		// launch the selected application
        		Utilities.launchApp(parent.getContext(), getPackageManager(), appInfo.packageName);
        	}
		});
    }
}