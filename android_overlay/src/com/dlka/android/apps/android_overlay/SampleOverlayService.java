package com.dlka.android.apps.android_overlay;

/*
Copyright 2011 jawsware international

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemClock;
import android.widget.Toast;

import com.jawsware.core.share.OverlayService;

public class SampleOverlayService extends OverlayService {
	public static SampleOverlayService instance;
	private SampleOverlayView overlayView;
	  final static private long ONE_SECOND = 1000;
      private long DEFINED_TIME = ONE_SECOND * 1200;
	  PendingIntent pi;
      BroadcastReceiver br;
      AlarmManager am;
      
//	public class Reminder {
//	    Timer timer;
//	    TimerTask task;
//	    
//	    public Reminder(int seconds) {
//	        timer = new Timer();
//	        timer.schedule(new RemindTask(), seconds*1000);
//		}
//
//	    class RemindTask extends TimerTask {
//	        public void run() {
//	           Log.d("timer","Time's up!%n");
//	            timer.cancel(); //Terminate the timer thread
//	            //instance.stopSelf();
//	         }
//	    }
//
//	}
	private void setup() {
	      br = new BroadcastReceiver() {
	             @Override
	             public void onReceive(Context c, Intent i) {
	                    Toast.makeText(c, "Pause machen!", Toast.LENGTH_LONG).show();
	                    //app start
	                    onLock();
	                    }
	             };
	      registerReceiver(br, new IntentFilter("com.dlka.android.apps.android_overlay") );
	      pi = PendingIntent.getBroadcast( this, 0, new Intent("com.dlka.android.apps.android_overlay"),
	0 );
	      am = (AlarmManager)(this.getSystemService( Context.ALARM_SERVICE ));
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		setup();
	    //getDefinedTime();
		am.set( AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + 
	    		 DEFINED_TIME, pi );
	    Toast.makeText(this, "Pause in "+DEFINED_TIME/1000/60+" Minuten", Toast.LENGTH_LONG).show();
	}
	
//	private void getDefinedTime() {
//		 EditText input = new EditText(this);
//	     input.setId(1000);          
//	     AlertDialog dialog = new AlertDialog.Builder(this)
//	             .setView(input).setTitle("Wie lange soll es bis zur Pause sein?")
//	             .setPositiveButton("Ok",
//	                     new DialogInterface.OnClickListener() {
//
//	                         @Override
//	                         public void onClick(DialogInterface dialog,
//	                                 int which) {
//	                             EditText theInput = (EditText) ((AlertDialog) dialog)
//	                                     .findViewById(1000);
//	                           DEFINED_TIME = (Integer.parseInt(theInput.getText().toString())*1000);
//	                             
//	                                 
//	                             
//	                         }
//	                     }).create();
//	     dialog.show();
//	}

	public void onLock(){
        instance = this;
        overlayView = new SampleOverlayView(this);
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		 am.cancel(pi);
	     unregisterReceiver(br);
		if (overlayView != null) {
			overlayView.destory();
		}

	}
	
	static public void stop() {
		if (instance != null) {
			instance.stopSelf();
			// mHandler.removeCallbacks(mUpdateTimeTask);
		}
	}
	
	@Override
	protected Notification foregroundNotification(int notificationId) {
		Notification notification;

		notification = new Notification(R.drawable.ic_launcher, getString(R.string.title_notification), System.currentTimeMillis());

		notification.flags = notification.flags | Notification.FLAG_ONGOING_EVENT | Notification.FLAG_ONLY_ALERT_ONCE;

		notification.setLatestEventInfo(this, getString(R.string.title_notification), getString(R.string.message_notification), notificationIntent());

		return notification;
	}


	private PendingIntent notificationIntent() {
		Intent intent = new Intent(this, SampleOverlayHideActivity.class);

		PendingIntent pending = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

		return pending;
	}

}
