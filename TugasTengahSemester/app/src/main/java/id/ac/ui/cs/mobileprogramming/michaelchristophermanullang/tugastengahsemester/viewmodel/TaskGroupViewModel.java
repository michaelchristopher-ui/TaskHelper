package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.activities.MainActivity;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.entities.Task;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.entities.TaskGroup;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.repository.Repository;

public class TaskGroupViewModel extends AndroidViewModel {

    private Repository repository;

    public LiveData<List<Task>> taskModelList;
    public LiveData<List<TaskGroup>> taskGroupList;

    public TaskGroupViewModel (Application application) {
        super(application);
        repository = Repository.getRepository(application);
        taskModelList = repository.getTaskLiveData();
        //List<Task> retrieved = retrieveTaskListSync("Name");
    }

    //Sync
    public LiveData<List<Task>> retrieveTaskList(String taskGroupName){
        taskModelList = repository.getTaskLiveDataWithTaskGroupNameAsync(taskGroupName);
        return repository.getTaskLiveDataWithTaskGroupNameAsync(taskGroupName);
    }

    public List<Task> retrieveTaskListSync(String taskGroupName){
        return repository.getTaskLiveDataWithTaskGroupName(taskGroupName);
    }

    public TaskGroup getTaskGroupByName(String taskGroupName){
        taskGroupList = repository.getTaskGroupByName(taskGroupName);
        return taskGroupList.getValue().get(0);
    }






}
