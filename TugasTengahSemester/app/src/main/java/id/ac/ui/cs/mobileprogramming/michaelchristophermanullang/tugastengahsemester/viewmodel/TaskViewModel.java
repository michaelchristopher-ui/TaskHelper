package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.entities.TaskSteps;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.repository.Repository;

public class TaskViewModel extends AndroidViewModel {
    private Repository repository;
    LiveData<List<TaskSteps>> taskSteps;

    public TaskViewModel (Application application) {
        super(application);
        repository = Repository.getRepository(application);
        taskSteps = retrieveTaskStepsList("");
    }
    public LiveData<List<TaskSteps>> retrieveTaskStepsList(String taskName){
        return repository.getTaskStepsByTaskName(taskName);
    }
}
