package com.example.tajagame;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window;
import android.view.inputmethod.EditorInfo;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import java.math.*;

public class MainActivity extends Activity implements OnEditorActionListener {

	/*
	 * EditText : 입력가능한 대상자.
	 * TextView : 텍스트를 보여주기만 하는 상자.
	 * */
	
	
	private EditText game_input;//글자 입력할때 EditText라는 
	private TextView Target;
	private TextView counter;
	private TextView score;
	private int game_counter = 0;
	
	int trg1_x = 0;
	int trg1_y = 0;
	
	private String trg_text[] = {"asdf", "ee", "break", "swag", "qwer", "zxcv", "1234", "4321"};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//메인 타이틀바 제거
        setContentView(R.layout.activity_main);// res/layout에 있는 레이아웃 파일 중 어떤걸 적용할지 선택.
        
        game_input = (EditText)findViewById(R.id.etext_input);
        //findViewById, 지정된 ID로 Layout의 구성요소를 찾아서 game_input에 넣어준다. 그러면 game_input는 해당 구성요소의 역할을 하게된다.
        
        game_input.setOnEditorActionListener(this);//EditText인 game_input에 Action Listener(행위를 받으면 지정된 행동을 취하게 한다)를 넣어준다.
        //game_input.setImeOptions(EditorInfo.IME_ACTION_NEXT);
       Target = (TextView)findViewById(R.id.target1);
      counter = (TextView)findViewById(R.id.counter);
      score = (TextView)findViewById(R.id.score);
      counter.setText(new StringBuilder().append("30"));
      CountDownTimer cdTimer = new CountDownTimer(30000,1000)
      {

		@Override
		public void onFinish() {
			final CountDownTimer temp = this;
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);//javascript의 alert()와 같은 기능. 경고창 같은거.
			alertDialog.setTitle("끝");//경고창 제목
			alertDialog.setMessage(new StringBuilder().append("점수 : ").append(score.getText().toString()));//경고창 내용
			alertDialog.setPositiveButton("Done", new DialogInterface.OnClickListener()//확인키 추가
			{
				@Override
				public void onClick(DialogInterface dialog, int which) {//확인키를 눌렀을 때의 행동
					game_counter = 0;
					score.setText(new StringBuilder().append("0"));
					temp.start();
					return;
				}
			});alertDialog.show();
			
		}

		@Override
		public void onTick(long arg0) {
			// TODO Auto-generated method stub
			int left = ((int)arg0/1000);
			String leftTime = Integer.toString(left);
			counter.setText(new StringBuilder().append(leftTime));
		}
    	  
      };cdTimer.start();
    }

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public boolean onEditorAction(TextView v, int actionid, KeyEvent event) {
	//Action Listener을 추가한 EditText의 행동. 어떤 Action을 Listen해서 어떤 행동을 할 것인가.
		if((actionid == EditorInfo.IME_ACTION_DONE) || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER))
		{//Done Key혹은 Enter Key를 Listen했을때 일어나는 행동등..
			
			String input_txt = game_input.getText().toString().replace("\n","");
			input_txt.replace("\r", "");//입력한 TEXT이며 replace하는건 엔터키로 입력을 해서 엔터키가 같이 들어가서 텍스트 비교시 틀렸다고 나오기도 하기 때문.
			String trg_txt = Target.getText().toString();
			
			
			if(input_txt.equals(trg_txt))//맞출때
			{
				game_counter += 10;
				score.setText(new StringBuilder().append(Integer.toString(game_counter)));
				Target.setText(new StringBuilder().append(trg_text[(int)(Math.random()*trg_text.length)]));//새 텍스트로 대체
			}
			else
			{
				
			}
			game_input.setText(null);
			
			
			/*
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);//javascript의 alert()와 같은 기능. 경고창 같은거.
			alertDialog.setTitle("TEXT");//경고창 제목
			alertDialog.setMessage("EditText Action Listener Text");//경고창 내용
			alertDialog.setPositiveButton("Done", new DialogInterface.OnClickListener()//확인키 추가
			{
				@Override
				public void onClick(DialogInterface dialog, int which) {//확인키를 눌렀을 때의 행동
					return;
				}
			});alertDialog.show();*/
		}
		return false;
	}
	
	
	

	
    
}
