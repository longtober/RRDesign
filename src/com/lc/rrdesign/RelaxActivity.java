package com.lc.rrdesign;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class RelaxActivity extends Activity {

	RelaxCount counter,counterSnooze;
	AlertDialog.Builder alert;
	Random randomMethod;
	int activitieNumber;
	WebView webView;
	Button change;
	static int timeRelax = 0;
	static int snoozeTimes;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_relax);
		webView = (WebView) findViewById(R.id.webView);
		webView.getSettings().setJavaScriptEnabled(true);
		change = (Button) findViewById(R.id.changeMethod);
		
		
		change.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				methodRandom();
			}
		});
		
		DialogSnooze();
		if (timeRelax > 0)
		{
			counter = new RelaxCount(timeRelax*1000*60, 1000);
			counter.start();
		}
		else 
		{
			counter = new RelaxCount(1*1000*60, 1000);
			counter.start();
		}
		
		//Make random method

		methodRandom();
	}


	private void methodRandom() {
		randomMethod = new Random();
		int n = randomMethod.nextInt(10000);
		activitieNumber = n % 5;
	
		switch(activitieNumber)
		{
		case 0:
			webView.loadUrl("file:///android_asset/Game/2048.htm");
			break;
		case 1:
			webView.loadUrl("file:///android_asset/Story/Story.htm");
			break;
		case 2:
			webView.loadUrl("file:///android_asset/Yoga/Yoga.htm");
			break;
		case 3:
			webView.loadUrl("file:///android_asset/Quiz/Quiz.htm");
			break;
		case 4:
			webView.loadUrl("https://www.youtube.com/channel/UCfa352_VIp2AI7R9Euh0QfA");
			break;
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.relax, menu);
		return true;
	}

	private void DialogSnooze() {
		/// Make a dialog snooze
		alert = new AlertDialog.Builder(this);
	    alert.setTitle("Relax").setMessage("Just Finish!! Let's Study");
	    //Creat a button on dialog 
	    alert.setPositiveButton("Snooze", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int whichButton) {
	            //Your action here
	        	counterSnooze = new RelaxCount(60000, 1000);
	        	counterSnooze.start();
	        }
	    });
	    alert.setNegativeButton("Study ",
	        new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int whichButton) {
	            	try {
						this.finalize();
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	Intent study = new Intent(RelaxActivity.this,StudyActivity.class);
	            	startActivity(study);
	            }
	        });
	}
	
	public class RelaxCount extends CountDownTimer {
		RelaxCount(long millisInFuture, long countDownInterval) {
			// TODO Auto-generated constructor stub
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {
			// TODO Auto-generated method stub
			Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
			Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
			r.play();
			alert.show();		
//			Intent startRelax = new Intent(mContext, RelaxActivity.class);
//			mContext.startActivity(startRelax);
		}

		@Override
		public void onTick(long millisUntilFinished) {
			// TODO Auto-generated method stub
			int m,s;
			m = (int) millisUntilFinished / 60000;
			s =  (int) (millisUntilFinished - m*60000) /1000;
		
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	 
	public static void settingRelax(int time2, int snoozetime) {
		// TODO Auto-generated method stub
		timeRelax =time2;
		snoozeTimes = snoozetime;
	}
	
}
