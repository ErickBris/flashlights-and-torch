package com.soulappsworld.flashlights;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class FT_Setting  extends Activity{

	private ImageButton imgbtnback;
	private TextView txtinfoname;
	AdRequest adRequest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ft_setting);
		
		AdView adView = (AdView) this.findViewById(R.id.adView);
		adRequest = new AdRequest.Builder()
		.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
		.addTestDevice("7AF8C338E6A4EA23E303067B6C1016ED")
		.build();
		adView.loadAd(adRequest);

		txtinfoname=(TextView)findViewById(R.id.txtinfoname);
		txtinfoname.setText(getResources().getString(R.string.menu_settings));

		imgbtnback=(ImageButton)findViewById(R.id.imgbtnback);	
		imgbtnback.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				finish();
			}
		});
	}

}
