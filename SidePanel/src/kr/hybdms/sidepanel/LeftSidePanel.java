package kr.hybdms.sidepanel;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import kr.hybdms.sidepanel.PanelArrayAdapter;
import kr.hybdms.sidepanel.R;

import kr.hybdms.sidepanel.util.SystemUiHider;


import android.app.Activity;
import android.app.ActivityManager;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Context;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class LeftSidePanel extends Activity implements
OnItemClickListener {
ListView listView;
List<PanelItemDetail> rowItems;
/** Called when the activity is first created. */
@Override
public void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_left_side_panel);

ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(100);
List <Drawable> applicationDrawables = new ArrayList <Drawable>();
PackageManager pacMgr = getPackageManager();
rowItems = new ArrayList<PanelItemDetail>();
  for (ActivityManager.RunningTaskInfo runningTask: tasks)
  {
    try {
      applicationDrawables.add (pacMgr.getApplicationIcon(runningTask.topActivity.getPackageName()));
    } catch (NameNotFoundException e) {
      e.printStackTrace();
    }
  }

listView = (ListView) findViewById(R.id.panelcontents);
PanelArrayAdapter adapter = new PanelArrayAdapter(this,
        R.layout.panelrow, rowItems);
listView.setAdapter(adapter);
listView.setOnItemClickListener(this);
}

@Override
public void onItemClick(AdapterView<?> parent, View view, int position,
    long id) {
Toast toast = Toast.makeText(getApplicationContext(),
    "Item " + (position + 1) + ": " + rowItems.get(position),
    Toast.LENGTH_SHORT);
toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
toast.show();
}
}