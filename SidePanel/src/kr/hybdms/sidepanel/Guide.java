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

import com.actionbarsherlock.app.SherlockActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ViewFlipper;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class Guide extends SherlockActivity
implements View.OnTouchListener,
CompoundButton.OnCheckedChangeListener, OnClickListener {
CheckBox checkBox; 
ViewFlipper flipper;

// 터치 이벤트 발생 지점의 x좌표 저장
float xAtDown;
float xAtUp;

/** Called when the activity is first created. */
@Override
public void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_guide);


flipper = (ViewFlipper)findViewById(R.id.viewFlipper1);
flipper.setOnTouchListener(this);

RelativeLayout g1 = (RelativeLayout) View.inflate(this, R.layout.guideview_1, null);
RelativeLayout g2 = (RelativeLayout) View.inflate(this, R.layout.guideview_2, null);
RelativeLayout g3 = (RelativeLayout) View.inflate(this, R.layout.guideview_3, null);
RelativeLayout g4 = (RelativeLayout) View.inflate(this, R.layout.guideview_4, null);
RelativeLayout g5 = (RelativeLayout) View.inflate(this, R.layout.guideview_5, null);
RelativeLayout g6 = (RelativeLayout) View.inflate(this, R.layout.guideview_6, null);

flipper.addView(g1);
flipper.addView(g2);
flipper.addView(g3);
flipper.addView(g4);
flipper.addView(g5);
flipper.addView(g6);

Button a=(Button)findViewById(R.id.button1);
a.setOnClickListener(this);
}

// View.OnTouchListener의 abstract method
// flipper 터지 이벤트 리스너
@Override
public boolean onTouch(View v, MotionEvent event) {
// 터치 이벤트가 일어난 뷰가 ViewFlipper가 아니면 return
if(v != flipper) return false;		

if(event.getAction() == MotionEvent.ACTION_DOWN) {
xAtDown = event.getX(); // 터치 시작지점 x좌표 저장			
}
else if(event.getAction() == MotionEvent.ACTION_UP){
xAtUp = event.getX(); 	// 터치 끝난지점 x좌표 저장

if( xAtUp < xAtDown ) {
// 왼쪽 방향 에니메이션 지정
flipper.setInAnimation(AnimationUtils.loadAnimation(this,
R.anim.left_slide_in));
flipper.setOutAnimation(AnimationUtils.loadAnimation(this,
R.anim.right_slide_out));

// 다음 view 보여줌
flipper.showNext();
}
else if (xAtUp > xAtDown){
// 오른쪽 방향 에니메이션 지정
flipper.setInAnimation(AnimationUtils.loadAnimation(this,
R.anim.right_slide_in));
flipper.setOutAnimation(AnimationUtils.loadAnimation(this,
R.anim.left_slide_out));
// 전 view 보여줌
flipper.showPrevious();			
}
}		
return true;
}

// CompoundButton.OnCheckedChangeListener의 abstract method
// 책크박스 책크 이벤트 리스너
@Override
public void onCheckedChanged(CompoundButton view, boolean isChecked) {


if(isChecked == true) {
// 왼쪽 에니메이션 설정
flipper.setInAnimation(AnimationUtils.loadAnimation(this,
R.anim.left_slide_in));
flipper.setOutAnimation(AnimationUtils.loadAnimation(this,
R.anim.left_slide_out));

// 자동 Flipping 시작 (간격 3초)
flipper.setFlipInterval(3000);
flipper.startFlipping();
}
else 
{
// 자동 Flipping 해지
flipper.stopFlipping();
}		
}

@Override
public void onClick(View v) {
	// TODO Auto-generated method stub

  	  finish();
}
}