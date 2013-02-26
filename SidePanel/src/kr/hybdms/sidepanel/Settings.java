/*
 * SidePanel Application For Android
 * Copyright 2013 Young Bin Han
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package kr.hybdms.sidepanel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
        boolean firstrun = getSharedPreferences("BOOT_PREF", MODE_PRIVATE).getBoolean("firstrun", true);
        
        if (firstrun){
        	Intent guide = new Intent(Settings.this, Guide.class); 
	    	 startActivity(guide);
        	
            getSharedPreferences("BOOT_PREF", MODE_PRIVATE)
                .edit()
                .putBoolean("firstrun", false)
                .commit();
        }

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
        Preference guideintent = (Preference)findPreference("guide_intent");
         
        pAppName.setOnPreferenceClickListener(this);
        pAppVersion.setOnPreferenceClickListener(this);
        guideintent.setOnPreferenceClickListener(this);
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
        else if(preference.getKey().equals("guide_intent"))
        {
        	Intent appinfo = new Intent(Settings.this, Guide.class); 
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