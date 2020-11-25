package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;

import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.IAddTaskGroup;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.entities.TaskGroup;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.repository.Repository;

public class AddTaskGroupService extends Service {
    public AddTaskGroupService() {
        Log.d("AddTaskGroupService", "Service Started");
    }
    String LOG_TAG = "AddTaskGroupService";

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.d("intent", intent.toString());
        Log.d("onBind", "Bound");
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(LOG_TAG, "Service Started.");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "Service Stopped.");
    }

    private final IAddTaskGroup.Stub binder = new IAddTaskGroup.Stub() {
        @Override
        public void addTaskGroup(String taskGroupName, String taskGroupDescription) throws RemoteException {
            Repository repository = Repository.getRepository(getApplication());
            TaskGroup taskGroup = new TaskGroup();
            taskGroup.setTaskGroupDescription(taskGroupDescription);
            taskGroup.setTaskGroupName(taskGroupName);
            taskGroup.setTaskNames(new ArrayList<String>());
            //Executed method
            Log.d("binder method", "binder executed");
            repository.insertTaskGroup(taskGroup);
        }
    };


}
