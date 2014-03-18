package com.fegmobile.imissyou.service;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.IBinder;

/**
 * @author felipealmeida
 *
 */
public class MediaPlayerService extends Service
{
	
	MediaPlayer player;
	
	/* (non-Javadoc)
	 * @see android.app.Service#onBind(android.content.Intent)
	 */
	@Override
	public IBinder onBind(Intent arg0)
	{
		return null;
	}
	
	/* (non-Javadoc)
	 * @see android.app.Service#onCreate()
	 */
	@Override
	public void onCreate()
	{
		try {
			AssetFileDescriptor afd = getAssets().openFd("media/missyou.mp3");
			player = new MediaPlayer();
			player.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
			player.setLooping(true);
			player.prepare();
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.onCreate();
	}
	
	/* (non-Javadoc)
	 * @see android.app.Service#onStartCommand(android.content.Intent, int, int)
	 */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		if (!player.isPlaying()) player.start();
		return super.onStartCommand(intent, flags, startId);
	}
	
	/* (non-Javadoc)
	 * @see android.app.Service#onDestroy()
	 */
	@Override
	public void onDestroy()
	{
		if (player.isPlaying()) player.stop();
		super.onDestroy();
	}
	
}
