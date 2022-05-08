package com.soulappsworld.flashlights;

import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.soulappsworld.flashlights.data.Constant_Data;

public class FT_SplashScreen extends Activity {

	private Timer timer;
	private TimerTask timerTask;
	private int width ;
	private int height;
	private SharedPreferences preferences;
	private SharedPreferences.Editor editor;
	private int devicesize_flag;
	private ImageView imgsplashicon;
	private TextView txtFlashlight;
	private TextView txtplus;
	private TextView txtTorch;
	boolean hasFlash ;
	private Camera cam_Splash_FlashLight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_ft__splash_screen);
		Runtime.getRuntime().gc();

		Constant_Data.infocounter=0;
		txtFlashlight=(TextView)findViewById(R.id.txtFlashlight);
		txtplus=(TextView)findViewById(R.id.txtplus);
		txtTorch=(TextView)findViewById(R.id.txtTorch);

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		editor = preferences.edit();

		imgsplashicon=(ImageView)findViewById(R.id.imgsplashicon);

		hasFlash= this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
		Log.e("hasFlash", ""+hasFlash);
		editor.putBoolean("hasFlash", hasFlash);
		editor.commit();
		try {
			cam_Splash_FlashLight = Camera.open();

			Parameters params= cam_Splash_FlashLight.getParameters();

			List<String> flashmodes=params.getSupportedFlashModes();
			Log.e("flashmodes size", flashmodes.size()+"");
			
			if(flashmodes.size()==1)
			{
				editor.putBoolean("hasFlash", false);
				editor.commit();
			}
			else
			{
				editor.putBoolean("hasFlash", hasFlash);
				editor.commit();
			}
			


		} catch (RuntimeException e) {
			Log.i("", "Camera.open() failed: " + e.getMessage());
		}
		
		

		devicesize_flag=isTablet(this);

		editor.putInt("devicesize_flag", devicesize_flag);
		editor.commit();

		if(devicesize_flag==4)
		{

			imgsplashicon.setImageResource(R.drawable.icon_500x500);
			txtFlashlight.setTextSize(getResources().getDimension(R.dimen.texttripleextralargesize));
			txtplus.setTextSize(getResources().getDimension(R.dimen.texttripleextralargesize));
			txtTorch.setTextSize(getResources().getDimension(R.dimen.texttripleextralargesize));
		}
		Log.e("tblatflag", ""+devicesize_flag);

		try
		{
			String language="en";

			Locale locale=Locale.getDefault();

			Log.e("Language code:- ",locale.getLanguage());

			if(locale.getLanguage().equals("ar"))
			{
				language="ar";
			}
			else if(locale.getLanguage().equals("de"))
			{
				language="de";
			}
			else if(locale.getLanguage().equals("en"))
			{
				language="en";
			}
			else if(locale.getLanguage().equals("es"))
			{
				language="es";
			}
			else if(locale.getLanguage().equals("fr"))
			{
				language="fr";
			}
			else if(locale.getLanguage().equals("he"))
			{
				language="he";
			}
			else if(locale.getLanguage().equals("it"))
			{
				language="it";
			}
			else if(locale.getLanguage().equals("iw"))
			{
				language="iw";
			}
			else if(locale.getLanguage().equals("ja"))
			{
				language="ja";
			}
			else if(locale.getLanguage().equals("ko"))
			{
				language="ko";
			}
			else if(locale.getLanguage().equals("ln"))
			{
				language="ln";
			}
			else if(locale.getLanguage().equals("pt"))
			{
				language="pt";
			}
			else if(locale.getLanguage().equals("ru"))
			{
				language="ru";
			}
			else if(locale.getLanguage().equals("th"))
			{
				language="th";
			}
			else if(locale.getLanguage().equals("zh_CN") || locale.getLanguage().equals("zh"))
			{
				language="zh_CN";
			}
			else if(locale.getLanguage().equals("zh_TW"))
			{
				language="zh_TW";
			}
			
			Log.i("Default Locale: ",language);
			Locale locale2 = new Locale(language);
			Locale.setDefault(locale2);
			Configuration config2 = new Configuration();
			config2.locale = locale2;
			getBaseContext().getResources().updateConfiguration(config2,getBaseContext().getResources().getDisplayMetrics());

		}
		catch (Exception e) 
		{
			Log.e("Getting Device ID:- Error", e.toString());
		}




		Display display = getWindowManager().getDefaultDisplay(); 
		width = display.getWidth();
		height= display.getHeight();
		Constant_Data.screenwidth=width;
		Constant_Data.screenheight=height;

		Log.e("width", ""+width);
		Log.e("height", ""+height);




		if(getResources().getConfiguration().orientation==Configuration.ORIENTATION_PORTRAIT)
		{


			if(timer!=null)
				timer.cancel();

			timerTask=new TimerTask() 
			{	
				@Override
				public void run() 
				{
					if(timer!=null)
						timer.cancel();


					
						Intent mainIntent = new Intent(FT_SplashScreen.this,FT_Main_Activity.class);
						FT_SplashScreen.this.startActivity(mainIntent);
						FT_SplashScreen.this.finish();
					
				}
			};
			timer=new Timer();
			timer.schedule(timerTask,3000,100);
		}


	}

	public int isTablet(Context context) {

		int devicesize=2;
		boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE);
		Log.e("xlarge", ""+xlarge);
		boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);

		Log.e("large", ""+large);
		boolean nrml = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL);
		Log.e("nrml", ""+nrml);
		boolean small = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_SMALL);
		Log.e("small", ""+small);

		if(small)
		{
			devicesize=1;
		}
		if(nrml)
		{
			devicesize=2;
		}
		if(large)
		{
			devicesize=3;
		}
		if(xlarge)
		{
			devicesize=4;
		}

		return devicesize;


	}
	@Override
	protected void onDestroy() 
	{

		
		System.gc();
		Runtime.getRuntime().gc();
		super.onDestroy();
		try
		{
			if(cam_Splash_FlashLight!=null)
			{
				
				cam_Splash_FlashLight.release();
				cam_Splash_FlashLight = null;
				
			}
			unbindDrawables(findViewById(R.id.llspashscreen)); 
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