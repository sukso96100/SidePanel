package kr.hybdms.sidepanel;

public class PanelRowItem {
    private int App_icon;
    private String App_lable;
 
    public PanelRowItem(int App_icon, String App_lable) {
        this.App_icon = App_icon;
        this.App_lable = App_lable;
    }
    public int getApp_icon() {
        return App_icon;
    }
    public void setAppicon(int Appicon) {
        this.App_icon = App_icon;
    }
    public String getApp_lable() {
        return App_lable;
    }
    public void setApp_lable(String App_lable) {
        this.App_lable = App_lable;
    }
}