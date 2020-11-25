package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.entities.TaskSteps;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.repository.Repository;

public class AddTaskStepsViewModel extends AndroidViewModel {
    private Repository repository;

    public AddTaskStepsViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getRepository(application);
    }

    public void addTaskSteps(String taskStep, String taskName){
        TaskSteps taskSteps = new TaskSteps();
        taskSteps.setTaskStep(taskStep);
        taskSteps.setTaskName(taskName);
        repository.insertTaskSteps(taskSteps);
    }
}
