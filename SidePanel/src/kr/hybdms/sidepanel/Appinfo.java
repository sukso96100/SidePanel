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
import android.net.Uri;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import com.actionbarsherlock.app.SherlockPreferenceActivity;
import kr.hybdms.sidepanel.R;

public class Appinfo extends SherlockPreferenceActivity implements OnPreferenceClickListener{
	  @SuppressWarnings("deprecation")
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_appinfo);



        Preference changelogs = findPreference("changelogs_intent");
        Preference update = findPreference("update_intent");
        Preference feedback = findPreference("feedback_intent");
        Preference tutorial = findPreference("tutorial_intent");
        Preference blog = findPreference("blog_intent");
        Preference thirdparty = findPreference("thirdparty_intent");
         
        changelogs.setOnPreferenceClickListener(this);
        update.setOnPreferenceClickListener(this);
        feedback.setOnPreferenceClickListener(this);
        tutorial.setOnPreferenceClickListener(this);
        blog.setOnPreferenceClickListener(this);
        thirdparty.setOnPreferenceClickListener(this);

}

  @Override
  public boolean onPreferenceClick(Preference preference)
  {
      

      
    if(preference.getKey().equals("changelogs_intent"))
      {
      	Intent changelogs = new Intent(Appinfo.this, Changelogs.class); 
	    	 startActivity(changelogs);
      }
    else if(preference.getKey().equals("update_intent"))
    {
    	Intent update = new Intent(Intent.ACTION_VIEW);
        update.setData(Uri.parse("http://play.google.com/store/apps/details?id=kr.hybdms.sidepanel"));
        startActivity(update);
    }
    else if(preference.getKey().equals("feedback_intent"))
    {
    	Intent feedback = new Intent(Intent.ACTION_SENDTO);
  	  feedback.setData(Uri.parse("mailto:sukso96100@gmail.com"));
	    	  startActivity(feedback);
    }
    else if(preference.getKey().equals("tutorial_intent"))
    {
    	Intent tutorial = new Intent(Appinfo.this, Guide.class); 
	    	 startActivity(tutorial);
    }
    else if(preference.getKey().equals("blog_intent"))
    {
    	Intent blog = new Intent(Intent.ACTION_VIEW);
	  	blog.setData(Uri.parse("http://hybdms.blogspot.kr"));
	  	startActivity(blog);
    }
    else if(preference.getKey().equals("thirdparty_intent"))
    {
    	Intent thirdparty = new Intent(Appinfo.this, Notices.class); 
	    	 startActivity(thirdparty);
    }

      return false;
  }


}