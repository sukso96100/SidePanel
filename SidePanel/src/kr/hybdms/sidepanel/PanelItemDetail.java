package kr.hybdms.sidepanel;

import android.graphics.drawable.Drawable;

public class PanelItemDetail {
    private Drawable imageId;
    public PanelItemDetail(Drawable images) {
        this.imageId = images;
    }
    public Drawable getImageId() {
        return imageId;
    }
    public void setImageId(Drawable imageId) {
        this.imageId = imageId;
    }
}