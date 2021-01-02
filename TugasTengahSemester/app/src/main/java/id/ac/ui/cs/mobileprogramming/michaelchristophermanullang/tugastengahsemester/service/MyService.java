package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import java.util.Timer;
import java.util.TimerTask;

import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.R;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.fragment.StopwatchFragment;

public class MyService extends Service {
    private static final String CHANNEL_ID = "NotificationChannelID";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final Integer[] timeRemaining = {intent.getIntExtra("TimeValue", 0)};
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void run() {
                Intent intent1local = new Intent();
                intent1local.setAction("Counter");
                timeRemaining[0]--;
                NotificationUpdate(timeRemaining[0]);
                if (timeRemaining[0] <= 0){
                    timer.cancel();
                }
                intent1local.putExtra("TimeRemaining", timeRemaining[0]);
                sendBroadcast(intent1local);
            }
        }, 0,1000);
        return super.onStartCommand(intent, flags, startId);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void NotificationUpdate(Integer timeLeft){
        try {
            Intent notificationIntent = new Intent(this, StopwatchFragment.class);
            final PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
            final Notification[] notification = {new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("Tugas Akhir")
                    .setContentText("Time Remaining : " + timeLeft.toString())
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentIntent(pendingIntent)
                    .build()};
            startForeground(1, notification[0]);
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "My Counter Service", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

