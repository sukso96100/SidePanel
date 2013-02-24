package kr.hybdms.sidepanel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceManager;

import com.actionbarsherlock.app.SherlockPreferenceActivity;
import kr.hybdms.sidepanel.R;

public class Settings extends SherlockPreferenceActivity implements OnPreferenceClickListener, OnPreferenceChangeListener{

	  @SuppressWarnings("deprecation")
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        
        CheckBoxPreference checkboxPref = (CheckBoxPreference)getPreferenceManager().findPreference("service_toggle");

        checkboxPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {            

        public boolean onPreferenceChange(Preference preference, Object newValue) {

               boolean myValue = (Boolean) newValue;

               if(myValue)
                       startService(new Intent(Settings.this, TouchDetectService.class));
               else
                       stopService(new Intent(Settings.this, TouchDetectService.class));

              return true;
         }
     }); 
        
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

	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		// TODO Auto-generated method stub
		return false;
	}
    
    
    
}