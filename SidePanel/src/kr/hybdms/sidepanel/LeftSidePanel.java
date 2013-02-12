package kr.hybdms.sidepanel;


import java.util.ArrayList;
import java.util.List;

import kr.hybdms.sidepanel.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
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
public class LeftSidePanel extends Activity implements
OnItemClickListener {
	 
public static final String[] titles = new String[] { "SidePanel"};

public static final Integer[] images = { R.drawable.ic_launcher};

ListView listView;
List<PanelRowItem> rowItems;

/** Called when the activity is first created. */
@Override
public void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_left_side_panel);

rowItems = new ArrayList<PanelRowItem>();
for (int i = 0; i < titles.length; i++) {
    PanelRowItem item = new PanelRowItem(images[i], titles[i]);
    rowItems.add(item);
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