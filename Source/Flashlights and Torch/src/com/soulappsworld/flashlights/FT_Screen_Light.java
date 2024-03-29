package com.soulappsworld.flashlights;

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

public class FT_Screen_Light extends Activity 
{

	private TextView txtinfoname;
	private ImageButton imgbtnback,imgbtnfunction;
	private ImageButton imgbtnscreenlgt;
	private LinearLayout llscrren_lgt;
	private SharedPreferences preferences;
	private Editor editor;
	private boolean screen_light;
	private int devicesize_flag;
	private ConnectivityManager connMgr_screenlight;
	
	AdRequest adRequest;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ft_screen_light);
		
		AdView adView = (AdView) this.findViewById(R.id.adView);
		adRequest = new AdRequest.Builder()
		.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
		.addTestDevice("7AF8C338E6A4EA23E303067B6C1016ED")
		.build();
		adView.loadAd(adRequest);

	//	RelativeLayout rellaymain = (RelativeLayout)findViewById(R.id.rellaymain);
		connMgr_screenlight = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connMgr_screenlight.getActiveNetworkInfo() != null && connMgr_screenlight.getActiveNetworkInfo().isAvailable() && connMgr_screenlight.getActiveNetworkInfo().isConnected()) 
		{
			
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
//						myController_FT_screen = new AdController(act, "815872234","959132951","116796825","472493710");
//						myController_FT_screen.setAdditionalDockingMargin(5);
//						//myController.loadStartAd("MY_LB_NOTIFICATION_ID", "MY_LB_ICON_ID");
//						myController_FT_screen.loadAd();
//	
//	
//					} catch (Exception e) {
//						// TODO: handle exception
//					}
//				} 
//				//});
//			});
		}		
		
		
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		editor = preferences.edit();

		screen_light=preferences.getBoolean("screen light", false);

		txtinfoname=(TextView)findViewById(R.id.txtinfoname);
		txtinfoname.setText(getResources().getString(R.string.Screen_Light));

		devicesize_flag=preferences.getInt("devicesize_flag", 2);


		if(devicesize_flag==4)
		{
			txtinfoname.setTextSize(getResources().getDimension(R.dimen.textdoubleextralargesize));
		}
		
		llscrren_lgt=(LinearLayout)findViewById(R.id.llscrren_lgt);

		imgbtnscreenlgt=(ImageButton)findViewById(R.id.imgbtnscreenlgt);


		imgbtnfunction=(ImageButton)findViewById(R.id.imgbtnfunction);
		imgbtnfunction.setBackgroundResource(R.drawable.screen_lgt_32x32);
		imgbtnback=(ImageButton)findViewById(R.id.imgbtnback);	

		imgbtnback.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				screen_light=preferences.getBoolean("screen light", false);

				if(screen_light)
				{
					editor.putBoolean("screen light", false);
					editor.commit();
				}
				else
				{
					llscrren_lgt.setBackgroundResource(R.drawable.background);
				}
				finish();
			}
		});

		if(screen_light)
		{
			llscrren_lgt.setBackgroundColor(Color.WHITE);
		}
		else
		{
			llscrren_lgt.setBackgroundResource(R.drawable.background);
		}
		imgbtnscreenlgt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				screen_light=preferences.getBoolean("screen light", false);

				if(screen_light)
				{
					llscrren_lgt.setBackgroundResource(R.drawable.background);
					editor.putBoolean("screen light", false);
					editor.commit();
					imgbtnscreenlgt.setBackgroundResource(R.drawable.screenlgt);
				}
				else
				{
					llscrren_lgt.setBackgroundColor(Color.WHITE);
					editor.putBoolean("screen light", true);
					editor.commit();
					imgbtnscreenlgt.setBackgroundResource(R.drawable.screenlgt_);
				}

			}
		});

	}


	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();

		screen_light=preferences.getBoolean("screen light", false);

		if(screen_light)
		{
			editor.putBoolean("screen light", false);
			editor.commit();
		}
		else
		{
			llscrren_lgt.setBackgroundResource(R.drawable.background);
		}
	}
	
}
