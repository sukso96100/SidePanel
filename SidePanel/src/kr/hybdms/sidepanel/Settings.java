package kr.hybdms.sidepanel;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;

import com.actionbarsherlock.app.SherlockPreferenceActivity;
import kr.hybdms.sidepanel.R;

public class Settings extends SherlockPreferenceActivity implements OnPreferenceClickListener{

	  @SuppressWarnings("deprecation")
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        
 
        
        Preference pAppName = (Preference)findPreference("blog_intent");
        Preference pAppVersion = (Preference)findPreference("appinfo_intent");
         
        pAppName.setOnPreferenceClickListener(this);
        pAppVersion.setOnPreferenceClickListener(this);
    }
 
    @Override
    public boolean onPreferenceClick(Preference preference)
    {
        
        if(preference.getKey().equals("blog_intent"))
        {
        	Intent blog = new Intent(Intent.ACTION_VIEW);
		  	blog.setData(Uri.parse("http://hybdms.blogspot.kr"));
		  	startActivity(blog);
        }
        
        else if(preference.getKey().equals("appinfo_intent"))
        {
        	Intent appinfo = new Intent(Settings.this, Appinfo.class); 
	    	 startActivity(appinfo);
        }
        return false;
    }
    
}