package com.soulappsworld.flashlights;

import java.io.IOException;
import java.util.List;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class FT_Simple_FlashLight extends Activity implements SurfaceHolder.Callback
{

	private TextView txtinfoname;
	private ImageButton imgbtnback,imgbtnfunction;
	private ImageButton imgbtnsimpleflashlgt;
	private	Camera cam_Simple_FlashLight;
	private SharedPreferences preferences;
	private Editor editor;
	private boolean simple_flashlight;
	private SurfaceView _surfaceView_Simple_FlashLight;
	private SurfaceHolder _surfaceHolder_Simple_FlashLight;
	private int devicesize_flag;
	private boolean torchmode=false;
	private ConnectivityManager connMgr_simpleflashlight;
	private Handler handler_wait;

	AdRequest adRequest;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ft_simple_flashlight);

		AdView adView = (AdView) this.findViewById(R.id.adView);
		adRequest = new AdRequest.Builder()
		.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
		.addTestDevice("7AF8C338E6A4EA23E303067B6C1016ED")
		.build();
		adView.loadAd(adRequest);
		
		
		//	RelativeLayout rellaymain = (RelativeLayout)findViewById(R.id.rellaymain);
		connMgr_simpleflashlight = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connMgr_simpleflashlight.getActiveNetworkInfo() != null && connMgr_simpleflashlight.getActiveNetworkInfo().isAvailable() && connMgr_simpleflashlight.getActiveNetworkInfo().isConnected()) 
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
			//						myController_FT_simple = new AdController(act, "815872234","959132951","116796825","472493710");
			//						myController_FT_simple.setAdditionalDockingMargin(5);
			//						//myController.loadStartAd("MY_LB_NOTIFICATION_ID", "MY_LB_ICON_ID");
			//						myController_FT_simple.loadAd();
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

		devicesize_flag=preferences.getInt("devicesize_flag", 2);

		handler_wait= new Handler();
		handler_wait.postDelayed(new Runnable() {

			@Override
			public void run() {




			}
		}, 100); // second


		try {
			cam_Simple_FlashLight = Camera.open();


			cam_Simple_FlashLight.startPreview();


		} catch (RuntimeException e) {
			Log.i("", "Camera.open() failed: " + e.getMessage());
		}

		txtinfoname=(TextView)findViewById(R.id.txtinfoname);
		txtinfoname.setText(getResources().getString(R.string.Torch));




		if(devicesize_flag==4)
		{
			txtinfoname.setTextSize(getResources().getDimension(R.dimen.textdoubleextralargesize));
		}

		imgbtnsimpleflashlgt=(ImageButton)findViewById(R.id.imgbtnsimpleflashlgt);
		imgbtnback=(ImageButton)findViewById(R.id.imgbtnback);	
		imgbtnfunction=(ImageButton)findViewById(R.id.imgbtnfunction);
		imgbtnfunction.setBackgroundResource(R.drawable.torch_icon_32x32);

		_surfaceView_Simple_FlashLight = (SurfaceView)findViewById(R.id.surfaceView);
		_surfaceHolder_Simple_FlashLight = _surfaceView_Simple_FlashLight.getHolder();
		_surfaceHolder_Simple_FlashLight.addCallback(FT_Simple_FlashLight.this);
		_surfaceHolder_Simple_FlashLight.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}


	@Override
	public void onResume() {
		super.onResume();


		devicesize_flag=preferences.getInt("devicesize_flag", 2);

		imgbtnback.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub


				if(simple_flashlight)
				{

					ledoff();
				}
				finish();
			}
		});

		imgbtnsimpleflashlgt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub



				if(simple_flashlight)
				{
					imgbtnsimpleflashlgt.setBackgroundResource(R.drawable.lgt_start);
					ledoff();
				}
				else
				{
					imgbtnsimpleflashlgt.setBackgroundResource(R.drawable.lgt_stop);
					ledon();
				}

			}
		});
		Log.i("", "onResume");
	}




	private void ledon() {


		if(cam_Simple_FlashLight!=null)
		{

			try {

				Parameters params= cam_Simple_FlashLight.getParameters();

				List<String> flashmodes=params.getSupportedFlashModes();
				Log.e("flashmodes size", flashmodes.size()+"");
				if (flashmodes.size()==0) 
				{

				}
				else
				{
					for(int i=0; i<flashmodes.size(); i++)
					{
						Log.e("torchmode list"+i, flashmodes.get(i));

						if(flashmodes.get(i).equalsIgnoreCase(Parameters.FLASH_MODE_TORCH))
						{
							torchmode=true;
							Log.e("torchmode"+i, torchmode+"");
						}
					}
				}


				if(torchmode)
				{
					params.setFlashMode(Parameters.FLASH_MODE_TORCH);
				}
				else
				{
					params.setFlashMode(Parameters.FLASH_MODE_ON);
				}

				params.setFlashMode(Parameters.FLASH_MODE_TORCH);
				cam_Simple_FlashLight.setParameters(params);

				simple_flashlight=true;

			} catch (Exception e) {
				// TODO: handle exception
			}





		}
	}

	private void ledoff() {


		try {
			Parameters params= cam_Simple_FlashLight.getParameters();

			params.setFlashMode(Parameters.FLASH_MODE_OFF);
			cam_Simple_FlashLight.setParameters(params);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		simple_flashlight=false;

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();


		if(simple_flashlight)
		{

			ledoff();
		}


	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		if(cam_Simple_FlashLight!=null)
		{
			cam_Simple_FlashLight.stopPreview();
			cam_Simple_FlashLight.release();
			cam_Simple_FlashLight = null;
		}

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub



		try {
			cam_Simple_FlashLight.setPreviewDisplay(holder);

		}
		catch (IOException e) {
			Log.e("", e.getMessage());
			e.printStackTrace();

		}



	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}

	


}
