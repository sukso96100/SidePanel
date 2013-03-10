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
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.Vibrator;
import android.preference.PreferenceManager;
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
	            case MotionEvent.ACTION_DOWN:
	            	boolean vibeon = getSharedPreferences(getPackageName() + "_preferences", Context.MODE_PRIVATE).getBoolean("vibe_toggle", true);
	            	if(vibeon){ //진동설정 활성화 된 경우
	            	Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
	            	vibe.vibrate(10);} //약0.01초 진동
	            	else{}
	            	//패널열기
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
        	SharedPreferences myPreference = PreferenceManager.getDefaultSharedPreferences(this);
      	  String dw = myPreference.getString("detector_width", "");
      	  String dh = myPreference.getString("detector_height", "");
      	  String detectorpos = myPreference.getString("detector_pos", "");
        mTouchDetector = new ImageView(this);                                         //뷰 생성
        mTouchDetector.setImageResource(R.drawable.detector);
        
        int orgWidth = mTouchDetector.getDrawable().getIntrinsicWidth();
        int orgHeight = mTouchDetector.getDrawable().getIntrinsicHeight();
        int newWidth;
        int newHeight;
        
        if(dw.equals("025")){
            newWidth = orgWidth / 4;
        }
        else if(dw.equals("050")){
        	newWidth = orgWidth / 2;
        }
        else if(dw.equals("075")){
        	newWidth = (int) (orgWidth*0.75);
        }
        else if(dw.equals("100")){
        	newWidth = orgWidth;
        }
        else if(dw.equals("200")){
        	newWidth = orgWidth*2;
        }
        else if(dw.equals("300")){
        	newWidth = orgWidth*3;
        }
        else{
        	newWidth = orgWidth*4;
        }
         
        if(dh.equals("025")){
            newHeight = orgHeight / 4;
        }
        else if(dh.equals("050")){
        	newHeight = orgHeight / 2;
        }
        else if(dh.equals("075")){
        	newHeight = (int) (orgHeight*0.75);
        }
        else if(dh.equals("100")){
        	newHeight = orgHeight;
        }
        else if(dh.equals("200")){
        	newHeight = orgHeight*2;
        }
        else if(dh.equals("300")){
        	newHeight = orgHeight*3;
        }
        else{
        	newHeight = orgHeight*4;
        }
        mTouchDetector.setOnTouchListener(mViewTouchListener);              //팝업뷰에 터치 리스너 등록
        //최상위 윈도우에 넣기 위한 설정
        mParams = new WindowManager.LayoutParams(
        		newWidth,
        		newHeight,
                WindowManager.LayoutParams.TYPE_PHONE,//항상 최 상위. 터치 이벤트 받을 수 있음.
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,  //포커스를 가지지 않음
                PixelFormat.TRANSLUCENT);       //투명
        if(rightpanel){
        	if(detectorpos.equals("top")){
            mParams.gravity = Gravity.RIGHT | Gravity.TOP;   
        	}
        	else if(detectorpos.equals("center")){
                mParams.gravity = Gravity.RIGHT | Gravity.CENTER;   
            	}
        	else{
                mParams.gravity = Gravity.RIGHT | Gravity.BOTTOM;   
            	}
        }
        else
        {
        	if(detectorpos.equals("top")){
                mParams.gravity = Gravity.LEFT | Gravity.TOP;   
            	}
            	else if(detectorpos.equals("center")){
                    mParams.gravity = Gravity.LEFT | Gravity.CENTER;   
                	}
            	else{
                    mParams.gravity = Gravity.LEFT | Gravity.BOTTOM;   
                	}
        }
            
        
        //패널 인식영역 위치설정(왼쪽 / 오른쪽)
        mTouchDetector.setScaleType(ImageView.ScaleType.FIT_XY);

        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mWindowManager.addView(mTouchDetector, mParams);
        
        if(notificationison){
        //알림영역 표시
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