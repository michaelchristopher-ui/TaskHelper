package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.R;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.fragment.StopwatchFragment;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.broadcastreceiver.MyReceiver;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.fragment.AddTaskFragment;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.fragment.AddTaskGroupFragment;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.fragment.AddTaskStepsFragment;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.fragment.AkhirSemesterFragment;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.fragment.ListOfTaskGroupsFragment;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.fragment.TaskFragment;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.fragment.TaskGroupFragment;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    MainViewModel mainViewModel;
    List<Fragment> fragments;

    static {
        System.loadLibrary("cpp_code");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Agar Alert Dapat Bekerja
        //ActivityCompat.requestPermissions(this, new String[]{FOREGROUND_SERVICE}, PackageManager.PERMISSION_GRANTED);

        //Create and insertion of fragments into a fragment list
        fragments = new ArrayList<Fragment>();
        fragments.add(new ListOfTaskGroupsFragment()); //0
        fragments.add(new TaskGroupFragment()); //1
        fragments.add(new TaskFragment()); //2
        fragments.add(new AddTaskStepsFragment()); //3
        fragments.add(new AddTaskFragment()); //4
        fragments.add(new AddTaskGroupFragment()); //5
        fragments.add(new AkhirSemesterFragment()); //6
        fragments.add(new StopwatchFragment());//7



        replaceFragment(0);

        Context contextMain = getApplicationContext();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                // code goes here.
                Uri uri = Uri.parse("content://taskgroup.provider/taskgroup");

                Cursor cursor = getApplication().getContentResolver().query(uri, null, null, null, null);

                if (cursor == null){
                    return;
                }
                if(cursor.moveToFirst()) {
                    StringBuilder strBuild=new StringBuilder();
                    int counter = 2;
                    while (!cursor.isAfterLast() && counter != 0) {
                        strBuild.append(" "+cursor.getString(cursor.getColumnIndex("taskGroupName")));
                        counter--;
                        cursor.moveToNext();
                    }
                    Log.d("String", strBuild.toString());
                    Toast.makeText(contextMain, strBuild.toString(), Toast.LENGTH_LONG);
                }
            }
        });

        t1.start();

        IntentFilter filter = new IntentFilter();
        filter.addAction(getPackageName() + "android.intent.action.DATE_CHANGED");

        MyReceiver myReceiver = new MyReceiver();
        registerReceiver(myReceiver, filter);
    }


    // This replace fragment method is going to be called from the fragments
    public void replaceFragment(int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainLayout, fragments.get(index));
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        replaceFragment(0);
    }

    public Fragment getFragment(int index) {
        return fragments.get(index);
    }

    public void startup(Intent intent){
        try {
            startService(intent);
            Log.d("start", "start");
        } catch (Exception e){
            Log.d("exception", e.toString());
        }
    }
}