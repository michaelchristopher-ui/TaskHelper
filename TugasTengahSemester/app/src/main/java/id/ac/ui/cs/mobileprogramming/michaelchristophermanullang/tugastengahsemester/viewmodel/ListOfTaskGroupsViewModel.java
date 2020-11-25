package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.entities.TaskGroup;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.repository.Repository;

public class ListOfTaskGroupsViewModel extends AndroidViewModel {

    private final Repository repository;
    LiveData<List<TaskGroup>> taskGroupModelList;

    public ListOfTaskGroupsViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getRepository(application);
        taskGroupModelList = repository.getTaskGroupLiveData();
    }

    public LiveData<List<TaskGroup>> retrieveTaskGroupList(){
        taskGroupModelList = repository.getTaskGroupLiveData();
        return taskGroupModelList;
    }
}
