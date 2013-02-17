package kr.hybdms.sidepanel;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class PanelArrayAdapter extends ArrayAdapter<PanelItemDetail> {
    Context context;
    public PanelArrayAdapter(Context context, int resourceId,
            List<PanelItemDetail> items) {
        super(context, resourceId, items);
        this.context = context;
    }
    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        PanelItemDetail rowItem = getItem(position);
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.panelrow, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.appicon);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        holder.imageView.setImageDrawable(rowItem.getImageId());
        return convertView;
    }
}