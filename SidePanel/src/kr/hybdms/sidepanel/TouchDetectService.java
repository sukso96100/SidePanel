package kr.hybdms.sidepanel;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class TouchDetectService extends Service {
    @Override
    public IBinder onBind(Intent arg0) { return null; }
    
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}