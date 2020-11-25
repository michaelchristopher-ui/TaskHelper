package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent){
        Toast.makeText(context, "A new day to complete your tasks!", Toast.LENGTH_SHORT);
    }
}
