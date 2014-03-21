package com.fegmobile.imissyou;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fegmobile.imissyou.service.MediaPlayerService;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class MainActivity extends Activity
{
	private static final String AD_UNIT_ID = "ca-app-pub-7746920603636507/5500421677";
	RelativeLayout rlMain;
	TextView tvPlay;
	private AdView adView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		
	    adView = new AdView(this);
	    adView.setAdSize(AdSize.BANNER);
	    adView.setAdUnitId(AD_UNIT_ID);

	    LinearLayout layout = (LinearLayout) findViewById(R.id.lnBan);
	    layout.addView(adView);

	    AdRequest adRequest = new AdRequest.Builder().build();
	    
	    adView.loadAd(adRequest);
		
		final Intent serviceIntent = new Intent(getApplicationContext(), MediaPlayerService.class);
		
		rlMain = (RelativeLayout) findViewById(R.id.rlMain);
		tvPlay = (TextView) findViewById(R.id.tvPlay);
		tvPlay.setOnClickListener(new OnClickListener()
		{
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v)
			{
				if (tvPlay.getText().equals("Play")) {
					tvPlay.setText(R.string.stop);
					rlMain.setBackground(getResources().getDrawable(R.drawable.notas));
					startService(serviceIntent);
				} else {
					stopService(serviceIntent);
					tvPlay.setText(R.string.play);
					rlMain.setBackground(getResources().getDrawable(android.R.color.white));
				}
				
			}
		});
		
		return true;
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		if (adView != null) {
			adView.resume();
		}
	}
	
	@Override
	public void onPause()
	{
		if (adView != null) {
			adView.pause();
		}
		super.onPause();
	}
	
	/** Called before the activity is destroyed. */
	@Override
	public void onDestroy()
	{
		// Destroy the AdView.
		if (adView != null) {
			adView.destroy();
		}
		super.onDestroy();
	}
	
}
