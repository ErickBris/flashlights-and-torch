package com.soulappsworld.flashlights;

import java.util.Timer;
import java.util.TimerTask;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FT_Police_Light extends Activity 
{


	private Timer timer_policelight;
	private TimerTask timerTask_policelight;
	private LinearLayout llpolicelgt;

	int[] mycolor = new int[]{ 


			Color.BLUE 	,
			Color.RED 
		
	};
	int i=0;
	private int color;
	private TextView txtinfoname;
	private ImageButton imgbtnback,imgbtnfunction;
	private SharedPreferences preferences;
	private Editor editor;
	private int devicesize_flag;
	private ConnectivityManager connMgr_policelight;
	
	AdRequest adRequest;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ft_police_light);

	//	RelativeLayout rellaymain = (RelativeLayout)findViewById(R.id.rellaymain);
		
		AdView adView = (AdView) this.findViewById(R.id.adView);
		adRequest = new AdRequest.Builder()
		.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
		.addTestDevice("7AF8C338E6A4EA23E303067B6C1016ED")
		.build();
		adView.loadAd(adRequest);
		
		connMgr_policelight = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connMgr_policelight.getActiveNetworkInfo() != null && connMgr_policelight.getActiveNetworkInfo().isAvailable() && connMgr_policelight.getActiveNetworkInfo().isConnected()) 
		{
//			
//			WebView wbviewnews=(WebView)findViewById(R.id.webView);
//			wbviewnews.getSettings().setJavaScriptEnabled(true);
//			wbviewnews.setVerticalScrollBarEnabled(true);
//			wbviewnews.setHorizontalScrollBarEnabled(true);
//			wbviewnews.loadUrl("file:///android_asset/add.html");
			
//			try {
//				
//				AdView adView=(AdView)findViewById(R.id.myAdview);
//				adView.setAdListener(this);
//
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
			
//			final Activity act = this;  
//			rellaymain.post(new Runnable() {
//	
//				
//	
//				public void run() { 
//					try {
//						
//						//myController = new AdController(act, "414553826","709210155","709210155","709210155");
//						myController_FT_police_lgt = new AdController(act, "815872234","959132951","116796825","472493710");
//						myController_FT_police_lgt.setAdditionalDockingMargin(5);
//						//myController.loadStartAd("MY_LB_NOTIFICATION_ID", "MY_LB_ICON_ID");
//						myController_FT_police_lgt.loadAd();
//	
//	
//					} catch (Exception e) {
//						// TODO: handle exception
//					}
//				} 
//				//});
//			});
			
		}		
		
		llpolicelgt=(LinearLayout)findViewById(R.id.llpolicelgt);

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		editor = preferences.edit();
		
		txtinfoname=(TextView)findViewById(R.id.txtinfoname);
		txtinfoname.setText(getResources().getString(R.string.Police_Light));

		devicesize_flag=preferences.getInt("devicesize_flag", 2);


		if(devicesize_flag==4)
		{
			txtinfoname.setTextSize(getResources().getDimension(R.dimen.textdoubleextralargesize));
		}
		
		imgbtnfunction=(ImageButton)findViewById(R.id.imgbtnfunction);
		imgbtnfunction.setBackgroundResource(R.drawable.police_light_32x32);
		imgbtnback=(ImageButton)findViewById(R.id.imgbtnback);	

		imgbtnback.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub


				onBackPressed();
			}
		});
		
		if(timer_policelight!=null)
			timer_policelight.cancel();

		timerTask_policelight=new TimerTask() 
		{	


			@Override
			public void run() 
			{


				runOnUiThread(new Runnable() {
					public void run() {

						i++;
						if(i>1)
						{
							i=0;

						}
						float[] hsv = new float[3];

						Color.colorToHSV(mycolor[i], hsv);
						hsv[2]=7f * ( 2f + hsv[2]);
						//	hsv[2] *= 0.8f; // value component
						color = Color.HSVToColor(hsv);


						//						try {
						//							Thread.sleep(100);
						//						} catch (InterruptedException e) {
						//							// TODO Auto-generated catch block
						//							e.printStackTrace();
						//						}


						llpolicelgt.setBackgroundColor(color);

					}
				});

			}
		};
		timer_policelight=new Timer();
		timer_policelight.schedule(timerTask_policelight,300,200);

	}





	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();

		if(timer_policelight!=null)
			timer_policelight.cancel();

		finish();


	}

	
}