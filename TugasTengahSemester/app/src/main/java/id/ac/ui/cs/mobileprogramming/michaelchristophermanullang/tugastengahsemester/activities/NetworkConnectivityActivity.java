package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.R;

public class NetworkConnectivityActivity extends AppCompatActivity {

    private AlertDialog mDialog;
    private ConnectivityManager.NetworkCallback mCallback;
    private ConnectivityManager mCManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkRequest request = new NetworkRequest.Builder().build();

        mCallback = new ConnectivityManager.NetworkCallback(){
            @Override
            public void onLost(Network network){
                //This will be executed when network lost
                if (mDialog != null) {
                    mDialog.dismiss();
                }
            }

            @Override
            public void onAvailable(Network network){
                //This will be executed if network becomes available when this activity is at the front
                AlertDialog.Builder builder = new AlertDialog.Builder(NetworkConnectivityActivity.this, R.style.Theme_AppCompat_Dialog_Alert)
                        .setTitle(R.string.titleSerious)
                        .setMessage(R.string.messageSerious)
                        .setPositiveButton(R.string.okSerious, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //This closes the app and only works for a minimum API level of 21
                                finishAndRemoveTask();
                            }
                        });

                //Show the dialog
                mDialog = builder.show();
            }
        };

        mCManager.registerNetworkCallback(request, mCallback);
    }


    @Override
    public void onStop(){
        mCManager.unregisterNetworkCallback(mCallback);
        super.onStop();

    }

    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
