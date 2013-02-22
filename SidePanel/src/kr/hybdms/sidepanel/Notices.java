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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.actionbarsherlock.app.SherlockActivity;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class Notices extends SherlockActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notices);
        
        TextView helloTxt = (TextView)findViewById(R.id.notices);
        helloTxt.setText(readTxt());
    }
    
    private String readTxt(){

     InputStream inputStream = getResources().openRawResource(R.raw.third_party_notices);
     
     ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
     
     int i;
  try {
   i = inputStream.read();
   while (i != -1)
      {
       byteArrayOutputStream.write(i);
       i = inputStream.read();
      }
      inputStream.close();
  } catch (IOException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  
     return byteArrayOutputStream.toString();
    }
}