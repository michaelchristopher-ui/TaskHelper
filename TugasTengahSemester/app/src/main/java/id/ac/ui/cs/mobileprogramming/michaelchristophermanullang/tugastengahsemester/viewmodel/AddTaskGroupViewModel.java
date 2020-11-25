package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.entities.TaskGroup;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.repository.Repository;

public class AddTaskGroupViewModel extends AndroidViewModel {
    private final Repository repository;

    public AddTaskGroupViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getRepository(application);
    }

    public void addTaskGroup(String taskGroupName, String taskGroupDescription, List<String> taskNames){
        TaskGroup taskGroup = new TaskGroup();
        taskGroup.setTaskGroupName(taskGroupName);
        taskGroup.setTaskGroupDescription(taskGroupDescription);
        taskGroup.setTaskNames(taskNames);
        repository.insertTaskGroup(taskGroup);
    }
}
