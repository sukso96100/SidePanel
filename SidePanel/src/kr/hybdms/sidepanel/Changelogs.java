package kr.hybdms.sidepanel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.actionbarsherlock.app.SherlockActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Changelogs extends SherlockActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changelogs);
        
        TextView helloTxt = (TextView)findViewById(R.id.changelogs);
        helloTxt.setText(readTxt());
    }
    
    private String readTxt(){

     InputStream inputStream = getResources().openRawResource(R.raw.changelogs);
     
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