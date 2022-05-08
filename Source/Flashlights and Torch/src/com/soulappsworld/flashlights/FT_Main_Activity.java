package com.soulappsworld.flashlights;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.soulappsworld.flashlights.data.Constant_Data;

public class FT_Main_Activity extends Activity 
{


	
	private SharedPreferences preferences;
	
	private TextView txtheadingname;
	private ImageButton imgbtninfo;
	private View imgbtnsetting;
	private ImageButton imgbtnflashlgt,imgbtnscreenlgt,imgbtnflashlgtblink,imgbtntraficsignallgt;
	private ImageButton imgbtnpolicelight,imgbtnscreenbackground;
	private boolean hasFlash;
	
	private int devicesize_flag;
	
	private ConnectivityManager connMgr_flashlightmain;
	
	private Handler m_handler_flashlight,m_handler_blinking;
 
	/** The log tag. */
	private static final String LOG_TAG = "InterstitialSample";
	private Button btn_Buy_PRO;
	
	AdRequest adRequest;
	
	InterstitialAd interstitial;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		
		hasFlash=preferences.getBoolean("hasFlash", true);
		Log.e("hasFlash", ""+hasFlash);


		
		if(hasFlash)
		{
			setContentView(R.layout.ft_main_activity);
			AdView adView = (AdView) this.findViewById(R.id.adView);
			adRequest = new AdRequest.Builder()
			.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
			.addTestDevice("7AF8C338E6A4EA23E303067B6C1016ED")
			.build();
			adView.loadAd(adRequest);
			
		    interstitial = new InterstitialAd(this);
		    interstitial.setAdUnitId("ca-app-pub-9366591393970813/4561978780");
	
		    interstitial.loadAd(adRequest);
			
			imgbtnflashlgt=(ImageButton)findViewById(R.id.imgbtnflashlgt);
			imgbtnflashlgtblink=(ImageButton)findViewById(R.id.imgbtnflashlgtblnk);
			
			imgbtnflashlgt.setOnClickListener(new OnClickListener() {



				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub


					m_handler_flashlight= new Handler();
					m_handler_flashlight.postDelayed(new Runnable() {

						@Override
						public void run() {

							Intent authIntent = new Intent(FT_Main_Activity.this,FT_Simple_FlashLight.class);
							startActivity(authIntent);


						}
					}, 10); // second

					//				Intent authIntent = new Intent(FT_Main_Activity.this,FT_Simple_FlashLight.class);
					//				startActivity(authIntent);

				}
			});

			
			imgbtnflashlgtblink.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					m_handler_blinking= new Handler();
					m_handler_blinking.postDelayed(new Runnable() {

						@Override
						public void run() {

							Intent authIntent = new Intent(FT_Main_Activity.this,FT_FlashLight_Blinking.class);
							startActivity(authIntent);


						}
					}, 10); // second

					//				Intent authIntent = new Intent(FT_Main_Activity.this,FT_FlashLight_Blinking.class);
					//				startActivity(authIntent);
				}
			});
			
			
		}
		else
		{
			setContentView(R.layout.ft_main_activity_2);
			
			AdView adView = (AdView) this.findViewById(R.id.adView);
			adRequest = new AdRequest.Builder()
			.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
			.addTestDevice("7AF8C338E6A4EA23E303067B6C1016ED")
			.build();
			adView.loadAd(adRequest);
			
		    interstitial = new InterstitialAd(this);
		    interstitial.setAdUnitId("ca-app-pub-9366591393970813/4561978780");
	
		    interstitial.loadAd(adRequest);
		}
		

		
		
		Constant_Data.startWakeLock(FT_Main_Activity.this);

		// RelativeLayout rellaymain = (RelativeLayout)findViewById(R.id.rellaymain);

		connMgr_flashlightmain = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connMgr_flashlightmain.getActiveNetworkInfo() != null && connMgr_flashlightmain.getActiveNetworkInfo().isAvailable() && connMgr_flashlightmain.getActiveNetworkInfo().isConnected()) 
		{
			
			
		}


		
		txtheadingname=(TextView)findViewById(R.id.txtheadingname);
		txtheadingname.setText(getResources().getString(R.string.app_name));
	

		imgbtnsetting=(ImageButton)findViewById(R.id.imgbtnsetting);
		imgbtnsetting.setVisibility(ImageButton.GONE);
		imgbtninfo=(ImageButton)findViewById(R.id.imgbtninfo);

		
		imgbtnscreenlgt=(ImageButton)findViewById(R.id.imgbtnscreenlgt);
		
		imgbtntraficsignallgt=(ImageButton)findViewById(R.id.imgbtntraficsignallgt);
		imgbtnpolicelight=(ImageButton)findViewById(R.id.imgbtnpolicelight);
		imgbtnscreenbackground=(ImageButton)findViewById(R.id.imgbtnscreenbackground);

		devicesize_flag=preferences.getInt("devicesize_flag", 2);

		btn_Buy_PRO=(Button)findViewById(R.id.btn_Buy_PRO);

		btn_Buy_PRO.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (connMgr_flashlightmain.getActiveNetworkInfo() != null && connMgr_flashlightmain.getActiveNetworkInfo().isAvailable() && connMgr_flashlightmain.getActiveNetworkInfo().isConnected()) 
				{

					Intent authIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constant_Data.PRO_link));
					startActivity(authIntent);
				}
				else
				{
					Toast.makeText(FT_Main_Activity.this, getResources().getString(R.string.No_Internet_Connection), Toast.LENGTH_SHORT).show();
				}				
			}
		});
		
		if(devicesize_flag==4)
		{
			//fl_header_layout.setBackgroundResource(R.drawable.header_back_800x100);
			txtheadingname.setTextSize(getResources().getDimension(R.dimen.textdoubleextralargesize));
			//			imgbtnflashlgt.setImageResource(R.drawable.icon_torch_150x150);
			//			imgbtnscreenlgt.setImageResource(R.drawable.icon_screen_lgt_150x150);
			//			imgbtnflashlgtblink.setImageResource(R.drawable.icon_flashlight_150x150);
			//			imgbtntraficsignallgt.setImageResource(R.drawable.icon_trafic_150x150);
			//			imgbtnpolicelight.setImageResource(R.drawable.icon_police_light_150x150);
			//			imgbtnscreenbackground.setImageResource(R.drawable.icon_bg_color_150x150);
		}

	


		//		imgbtnsetting.setOnClickListener(new OnClickListener() {
		//
		//			@Override
		//			public void onClick(View v) {
		//				// TODO Auto-generated method stub
		//
		//				Intent authIntent = new Intent(FT_Main_Activity.this,FT_Setting.class);
		//				startActivity(authIntent);
		//
		//			}
		//		});


		imgbtninfo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent authIntent = new Intent(FT_Main_Activity.this,AboutUS.class);
				startActivity(authIntent);
			}
		});
		
		imgbtnscreenlgt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent authIntent = new Intent(FT_Main_Activity.this,FT_Screen_Light.class);
				startActivity(authIntent);
				
				interstitial.show();
			}
		});
		
		
		imgbtntraficsignallgt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent authIntent = new Intent(FT_Main_Activity.this,FT_Traffic_Signal.class);
				startActivity(authIntent);
				
				interstitial.show();
			}
		});

		imgbtnpolicelight.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent authIntent = new Intent(FT_Main_Activity.this,FT_Police_Light.class);
				startActivity(authIntent);
				
		 
				interstitial.show();
	 
		
			 
				
			}
		});
		imgbtnscreenbackground.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent authIntent = new Intent(FT_Main_Activity.this,FT_Background.class);
				startActivity(authIntent);
			}
		});

		if (connMgr_flashlightmain.getActiveNetworkInfo() != null && connMgr_flashlightmain.getActiveNetworkInfo().isAvailable() && connMgr_flashlightmain.getActiveNetworkInfo().isConnected()) 
		{
			//			try {
			//				
			//				AdView adView=(AdView)findViewById(R.id.myAdview);
			//				adView.setAdListener(this);
			//
			//			} catch (Exception e) {
			//				// TODO: handle exception
			//			}
		}

	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		if (connMgr_flashlightmain.getActiveNetworkInfo() != null && connMgr_flashlightmain.getActiveNetworkInfo().isAvailable() && connMgr_flashlightmain.getActiveNetworkInfo().isConnected()) 
		{
			Log.e("Constant_Data.infocounter", Constant_Data.infocounter+"");


			 


		}
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub


		
		
		if (connMgr_flashlightmain.getActiveNetworkInfo() != null && connMgr_flashlightmain.getActiveNetworkInfo().isAvailable() && connMgr_flashlightmain.getActiveNetworkInfo().isConnected()) 
		{
			
		}
		
		super.onBackPressed();
	}

	@Override
	protected void onDestroy() 
	{
		Constant_Data.stopWakeLock();
		System.gc();
		Runtime.getRuntime().gc();
		super.onDestroy();
		try
		{
			unbindDrawables(findViewById(R.id.llmainactivity)); 
		}
		catch (Exception e) 
		{
			Log.e("splash scren Error in onDestroy", e.toString());
		}

	}

	private void unbindDrawables(View view) 
	{
		if (view.getBackground() != null) 
		{
			view.getBackground().setCallback(null);
		}
		if (view instanceof ViewGroup) 
		{
			for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) 
			{
				unbindDrawables(((ViewGroup) view).getChildAt(i));
			}
			((ViewGroup) view).removeAllViews();
		}
	}
	
	

 
}



