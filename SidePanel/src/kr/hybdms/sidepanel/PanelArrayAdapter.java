package kr.hybdms.sidepanel;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import kr.hybdms.sidepanel.PanelRowItem;
import android.content.Context;

public class PanelArrayAdapter extends ArrayAdapter<PanelRowItem> {
 
    Context context;
 
    public PanelArrayAdapter(Context context, int resourceId,
            List<PanelRowItem> items) {
        super(context, resourceId, items);
        this.context = context;
    }
 
    /*private view holder class*/
    private class ViewHolder {
        ImageView imageAppicon;
        TextView txtApplable;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        PanelRowItem rowItem = getItem(position);
 
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.panelrow, null);
            holder = new ViewHolder();
            holder.txtApplable = (TextView) convertView.findViewById(R.id.appnametext);
            holder.imageAppicon = (ImageView) convertView.findViewById(R.id.appicon);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
 
        holder.txtApplable.setText(rowItem.getApp_lable());
        holder.imageAppicon.setImageResource(rowItem.getApp_icon());
 
        return convertView;
    }
}