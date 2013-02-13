package kr.hybdms.sidepanel;

import java.util.List;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class PanelArrayAdapter extends BaseAdapter {
	private Context mContext;
	private List<ApplicationInfo> mListAppInfo;
	private PackageManager mPackManager;
	
	public PanelArrayAdapter(Context c, List<ApplicationInfo> list, PackageManager pm) {
		mContext = c;
		mListAppInfo = list;
		mPackManager = pm;
	}

	@Override
	public int getCount() {
		return mListAppInfo.size();
	}

	@Override
	public Object getItem(int position) {
		return mListAppInfo.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// get the selected entry
		ApplicationInfo entry = mListAppInfo.get(position);
		
		// reference to convertView
		View v = convertView;
		
		// inflate new layout if null
		if(v == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			v = inflater.inflate(R.layout.panelrow, null);
		}
		
		// load controls from layout resources
		ImageView ivAppIcon = (ImageView)v.findViewById(R.id.appicon);
		TextView tvAppName = (TextView)v.findViewById(R.id.appnametext);
		
		// set data to display
		ivAppIcon.setImageDrawable(entry.loadIcon(mPackManager));
		tvAppName.setText(entry.loadLabel(mPackManager));
		
		// return view
		return v;
	}

	
	

}
