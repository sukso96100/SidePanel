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

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Autostarter extends BroadcastReceiver {
	  @Override
	  public void onReceive(Context context, Intent intent) {    
		  boolean settings = context.getSharedPreferences(context.getPackageName() + "_preferences", Context.MODE_PRIVATE).getBoolean("autostart_toggle", true);
		  boolean serviceon = context.getSharedPreferences(context.getPackageName() + "_preferences", Context.MODE_PRIVATE).getBoolean("service_toggle", true);
		 

		  if(settings && serviceon){
	    if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
	      Log.i("BOOTSVC", "Intent received");    
	      ComponentName cn = new ComponentName(context.getPackageName(), TouchDetectService.class.getName());
	      ComponentName svcName = context.startService(new Intent().setComponent(cn));
	      if (svcName == null) 
	        Log.e("BOOTSVC", "Could not start service " + cn.toString());
	    }
	  }
		  else
		  {
		  }
		  }
	}
