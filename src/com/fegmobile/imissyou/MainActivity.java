package com.fegmobile.imissyou;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fegmobile.imissyou.service.MediaPlayerService;

public class MainActivity extends Activity
{
	RelativeLayout rlMain;
	TextView tvPlay;
	
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
	
}
