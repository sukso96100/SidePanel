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

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.graphics.PixelFormat;

public class TouchDetectService extends Service {
	private ImageView mTouchDetector;							
	private WindowManager.LayoutParams mParams;		
	private WindowManager mWindowManager;	
	
	private static final int MY_NOTIFICATION_ID=1;
	private NotificationManager notificationManager;
	private Notification myNotification;
	final static String ACTION = "NotifyServiceAction";
	final static String STOP_SERVICE = "";
	final static int RQS_STOP_SERVICE = 1;
	

	private OnTouchListener mViewTouchListener = new OnTouchListener() {
	    @Override public boolean onTouch(View v, MotionEvent event) {
	        switch(event.getAction()) {

	            case MotionEvent.ACTION_MOVE:
	            	
	            	Intent lsp = new Intent(getBaseContext(), SidePanel.class);
	            	lsp.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	            	getApplication().startActivity(lsp);
	                break;
	        }
	        
	        return true;
	    }
	};
	
    @Override
    public IBinder onBind(Intent arg0) { return null; }
    
    @Override
    public void onCreate() {
        super.onCreate();


               
        boolean rightpanel = getSharedPreferences(getPackageName() + "_preferences", Context.MODE_PRIVATE).getBoolean("panelpos_right", true);
        boolean notificationison = getSharedPreferences(getPackageName() + "_preferences", Context.MODE_PRIVATE).getBoolean("noti_toggle", true);
        Log.i("BOOTSVC", "Service started at the BOOT_COMPLETED.");

        if(rightpanel)
        {
        mTouchDetector = new ImageView(this);                                         //뷰 생성
        mTouchDetector.setImageResource(R.drawable.detector);
        mTouchDetector.setOnTouchListener(mViewTouchListener);              //팝업뷰에 터치 리스너 등록
        //최상위 윈도우에 넣기 위한 설정
        mParams = new WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_PHONE,//항상 최 상위. 터치 이벤트 받을 수 있음.
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,  //포커스를 가지지 않음
            PixelFormat.TRANSLUCENT);                                        //투명
        mParams.gravity = Gravity.RIGHT | Gravity.CENTER;                   //왼쪽 상단에 위치하게 함.
        
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);  //윈도우 매니저
        mWindowManager.addView(mTouchDetector, mParams);      //윈도우에 뷰 넣기. permission 필요.   //윈도우에 뷰 넣기. permission 필요.
        }
        else
        {
        	mTouchDetector = new ImageView(this);                                         //뷰 생성
            mTouchDetector.setImageResource(R.drawable.detector);
            mTouchDetector.setOnTouchListener(mViewTouchListener);              //팝업뷰에 터치 리스너 등록
            //최상위 윈도우에 넣기 위한 설정
            mParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,//항상 최 상위. 터치 이벤트 받을 수 있음.
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,  //포커스를 가지지 않음
                PixelFormat.TRANSLUCENT);                                        //투명
            mParams.gravity = Gravity.LEFT | Gravity.CENTER;                   //왼쪽 상단에 위치하게 함.
            
            mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);  //윈도우 매니저
            mWindowManager.addView(mTouchDetector, mParams);      //윈도우에 뷰 넣기. permission 필요.   //윈도우에 뷰 넣기. permission 필요.
        }
        
        
        if(notificationison){
     // Send Notification
        notificationManager =
         (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        myNotification = new Notification(R.drawable.ic_stat_sidepanel,
        		getText(R.string.service_notification),
          System.currentTimeMillis());
        Context context = getApplicationContext();
        CharSequence notificationTitle = getText(R.string.service_running);
        CharSequence notificationText = getText(R.string.service_running_desc);
        Intent myIntent = new Intent(getBaseContext(), Settings.class);;
        PendingIntent pendingIntent
          = PendingIntent.getActivity(getBaseContext(),
            0, myIntent,
            Intent.FLAG_ACTIVITY_NEW_TASK);
        myNotification.defaults |= Notification.DEFAULT_SOUND;
        myNotification.flags |= Notification.FLAG_AUTO_CANCEL;
        myNotification.flags = Notification.FLAG_ONGOING_EVENT;
        myNotification.setLatestEventInfo(context,
           notificationTitle,
           notificationText,
           pendingIntent);
        notificationManager.notify(MY_NOTIFICATION_ID, myNotification);
        }
        else
        {
        }
        
        
        }
    

   

  

	@Override
    public void onDestroy() {
        if(mWindowManager != null) {        //서비스 종료시 뷰 제거. *중요 : 뷰를 꼭 제거 해야함.
            if(mTouchDetector != null) mWindowManager.removeView(mTouchDetector);
        }
        super.onDestroy();
        notificationManager.cancel(MY_NOTIFICATION_ID);
    }
}