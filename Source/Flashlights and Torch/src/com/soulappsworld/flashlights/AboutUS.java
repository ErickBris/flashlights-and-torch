package com.soulappsworld.flashlights;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.soulappsworld.flashlights.data.Constant_Data;

public class AboutUS extends Activity
{
 
	private String[] Applink={"http://www.google.com","https://play.google.com/store/search?q=King&c=apps","","https://play.google.com/store/apps/details?id=com.king.candycrushsaga"} ;
	private String[] Appname={"","","","",""};

	private String app_name;
	private String Share_App_subject;
	private String Share_App_Body_top;
	private String Share_App_Body_middle;
	private String Share_App_Body_bottom;
	
	private ConnectivityManager connMgr_info;
	private ImageButton imgbtnback;
	private String Share_Andoird;
	private String Share_Application;
	
	private InterstitialAd interstitial;

	AdRequest adRequest ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aboutus);

		app_name=getResources().getString(R.string.app_name);
		
		
		Share_App_subject=getResources().getString(R.string.Share_App_subject);
		Share_Andoird=getResources().getString(R.string.Share_Andoird);
		Share_Application=getResources().getString(R.string.Share_Application);
		Share_App_Body_top=getResources().getString(R.string.Share_App_Body_top);
		Share_App_Body_middle=getResources().getString(R.string.Share_App_Body_middle);
		Share_App_Body_bottom=getResources().getString(R.string.Share_App_Body_bottom);

		connMgr_info = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connMgr_info.getActiveNetworkInfo() != null && connMgr_info.getActiveNetworkInfo().isAvailable() && connMgr_info.getActiveNetworkInfo().isConnected()) 
		{
			
			
		}
		
		imgbtnback=(ImageButton)findViewById(R.id.imgbtnback);	
		
		AdView adView = (AdView) this.findViewById(R.id.adView);
		adRequest = new AdRequest.Builder()
		.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
		.addTestDevice("7AF8C338E6A4EA23E303067B6C1016ED")
		.build();
		adView.loadAd(adRequest);
		
		
		
		
		imgbtnback.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				finish();
			}
		});
		ListView list= (ListView)findViewById(R.id.lstAbout);

		//		Applink = getResources().getStringArray(R.array.Applink);
		//		Appname = getResources().getStringArray(R.array.Appname);

		Appname[0]=getResources().getString(R.string.About_US);
		Appname[1]=getResources().getString(R.string.MoreApps);
		Appname[2]=getResources().getString(R.string.Share_App);
		Appname[3]=getResources().getString(R.string.ContactUS);
		Appname[4]=getResources().getString(R.string.Rate_This_App);
		//Appname[5]=getResources().getString(R.string.Buy_Pro);

		AboutAdapter aboutAdapter=new AboutAdapter();
		list.setAdapter(aboutAdapter);

		list.setOnItemClickListener(new OnItemClickListener() 
		{
		

			public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3) 
			{
				if(position==0 || position==1 )
				{

					if (connMgr_info.getActiveNetworkInfo() != null && 
							connMgr_info.getActiveNetworkInfo().isAvailable() &&
							connMgr_info.getActiveNetworkInfo().isConnected())
					{

						Intent authIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Applink[position]));
						startActivity(authIntent);

					}
					else
					{
						Toast.makeText(AboutUS.this, getResources().getString(R.string.No_Internet_Connection), Toast.LENGTH_SHORT).show();
					}

				}
				else if(position==2 )
				{

					  LayoutInflater li = LayoutInflater.from(AboutUS.this);
				      View promptsView = li.inflate(R.layout.edit, null);

				      AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AboutUS.this);
				      // set prompts.xml to alertdialog builder
				      
				      alertDialogBuilder.setTitle(getString(R.string.app_name));
				      alertDialogBuilder.setView(promptsView);
				      alertDialogBuilder.setIcon(R.drawable.icon);
				      final EditText edtText = (EditText) promptsView.findViewById(R.id.edtName);
				      String body=getString(R.string.Share_App_Body_top)+" "+getString(R.string.app_name)+" "+
				      getString(R.string.Share_App_Body_middle)+ " "+Constant_Data.aApp_link+" "+
				      getString(R.string.Share_App_Body_bottom);
				      edtText.setText(body);
				      
				      // set dialog message
				      alertDialogBuilder
				      .setCancelable(false)
				      .setPositiveButton(getString(R.string.Send),
				        new DialogInterface.OnClickListener() {
				       public void onClick(DialogInterface dialog,int id) 
				       {        
				        // get user input and set it to result
				        // edit text
				        String finalString=(edtText.getText().toString());
				        
				        Intent email = new Intent(Intent.ACTION_SEND);
				        email.setType("text/plain");
				        email.putExtra(Intent.EXTRA_EMAIL,  "" );
				        email.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				        email.putExtra(Intent.EXTRA_SUBJECT, " "+app_name+" "+Share_Andoird+" "+Share_Application);				        
//				        String body=getString(R.string.Share_App_Body_top)+" "+getString(R.string.app_name)+" "+
//				        getString(R.string.Share_App_Body_middle)+ " "+APP_LINK+" "+
//				        getString(R.string.Share_App_Body_bottom);
				        
				        email.putExtra(Intent.EXTRA_TEXT,finalString);
				        
				        try
				        {
				         startActivity(Intent.createChooser(email, "Send Message..."));
				        }
				        catch (android.content.ActivityNotFoundException ex) 
				        {
				         
				        }
				       }
				      })
				      .setNegativeButton(getString(R.string.Cancel),
				        new DialogInterface.OnClickListener() {
				       public void onClick(DialogInterface dialog,int id) {
				        dialog.cancel();
				       }
				      });

				      // create alert dialog
				      AlertDialog alertDialog = alertDialogBuilder.create();

				      // show it
				      alertDialog.show();

				}
				else if(position==3 )
				{

					if (connMgr_info.getActiveNetworkInfo() != null && 
							connMgr_info.getActiveNetworkInfo().isAvailable() &&
							connMgr_info.getActiveNetworkInfo().isConnected())
					{


						Intent sendIntent = new Intent(Intent.ACTION_SEND);

						//Mime type of the attachment (or) u can use sendIntent.setType("*/*")
						sendIntent.setType("text/html");

						String aEmailList[] = {"info@gmail.com"};
						sendIntent.putExtra(Intent.EXTRA_EMAIL,aEmailList);

						//Subject for the message or Email
						sendIntent.putExtra(Intent.EXTRA_SUBJECT,getString(R.string.Contact_from)+" "+getString(R.string.app_name));

						//Body for the message or Email
						String body="";
						sendIntent.putExtra(Intent.EXTRA_TEXT ,body);

						startActivity(Intent.createChooser(sendIntent, "Send email..."));
					}
					else
					{
						Toast.makeText(AboutUS.this, getResources().getString(R.string.No_Internet_Connection), Toast.LENGTH_SHORT).show();
					}
					//			startActivity(sendIntent);	

				}
				else if(position==4 )
				{

					if (connMgr_info.getActiveNetworkInfo() != null && connMgr_info.getActiveNetworkInfo().isAvailable() && connMgr_info.getActiveNetworkInfo().isConnected()) 
					{

						Log.e("Artist_Temp_Tab_Screen.this.getPackageName()",AboutUS.this.getPackageName());
						try {
						
							Uri marketUri = Uri.parse( String.format( "market://details?id=%s", AboutUS.this.getPackageName() ) );
							Intent marketIntent = new Intent( Intent.ACTION_VIEW ).setData( marketUri );
							startActivity( marketIntent );


						} catch (Exception e) {
							// TODO: handle exception

						}
					}
					else
					{
						Toast.makeText(AboutUS.this, getResources().getString(R.string.No_Internet_Connection), Toast.LENGTH_SHORT).show();
					}
				}


				//				Intent authIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Applink[position]));
				//				startActivity(authIntent);
			}
		});		
	}

	public class AboutAdapter extends BaseAdapter
	{
		private LayoutInflater layoutInflater;
		public AboutAdapter() 
		{
			layoutInflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() 
		{
			return Appname.length;
		}

		@Override
		public Object getItem(int arg0) 
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) 
		{
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup arg2) 
		{
			ViewHolder holder;
			if(convertView==null)
			{
				holder = new ViewHolder();
				convertView=layoutInflater.inflate(R.layout.row_about,null);
				holder.txtTitle=(TextView)convertView.findViewById(R.id.appname);
				//				holder.imgView=(ImageView)convertView.findViewById(R.id.imgIcon);

				convertView.setTag(holder);
			}
			else
			{
				holder=(ViewHolder) convertView.getTag();
			}

			holder.txtTitle.setText(Appname[position]);
			//			holder.imgView.setBackgroundResource(iconList[position]);
			return convertView;
		}

		public class ViewHolder
		{
			TextView txtTitle;
			//			ImageView imgView;
		}
	}
	
	
	
}

