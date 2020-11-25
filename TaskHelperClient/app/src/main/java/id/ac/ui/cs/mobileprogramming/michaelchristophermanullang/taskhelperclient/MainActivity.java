package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.taskhelperclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.KeyEventDispatcher;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.IAddTaskGroup;

public class MainActivity extends Activity {

    private IAddTaskGroup iAddTaskGroup = null;
    private Button bindBt;
    private Button callBt;
    private Button unbindBt;

    String taskGroupName;
    String taskGroupDescription;
    EditText taskGroupNameEdit;
    EditText taskGroupDescriptionEdit;
    Boolean isServiceConnected = false;

    private ServiceConnection serviceCon = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("service", service.toString());
            iAddTaskGroup = IAddTaskGroup.Stub.asInterface(service);
            Log.d("serviceCon", "Service Connnected");
            isServiceConnected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isServiceConnected = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initService();
        initViews();
    }

    private void initService() {
        Intent i = new Intent("id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.service.AddTaskGroupService.BIND");
        String packageName = "id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester";
        //i.setPackage(packageName);
        i.setClassName(packageName, packageName + ".service.AddTaskGroupService");
        getApplicationContext().bindService(i, serviceCon, Context.BIND_AUTO_CREATE);
    }

    private void callService() {
        try {
            taskGroupName = taskGroupNameEdit.getText().toString();
            taskGroupDescription = taskGroupDescriptionEdit.getText().toString();
            iAddTaskGroup.addTaskGroup(taskGroupName, taskGroupDescription);
            Log.d("call service", "Success");
        } catch (RemoteException exception){
            Log.d("exception on call service", exception.toString());
        }
    }

    private void initViews() {
        callBt = findViewById(R.id.call);
        callBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isServiceConnected) {
                    callService();
                } else {
                    Toast.makeText(MainActivity.this, "Service not connected",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        taskGroupNameEdit = findViewById(R.id.taskGroupName);
        taskGroupDescriptionEdit = findViewById(R.id.descriptionEdit);
    }
}