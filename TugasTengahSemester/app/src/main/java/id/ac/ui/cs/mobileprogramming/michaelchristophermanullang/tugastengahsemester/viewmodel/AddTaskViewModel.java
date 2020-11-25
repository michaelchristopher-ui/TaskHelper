package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import java.util.Date;
import java.util.List;

import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.dao.TaskDao;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.entities.Task;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.repository.Repository;


public class AddTaskViewModel extends AndroidViewModel {
    private TaskDao taskDao;

    Repository repository;

    public AddTaskViewModel(Application application){
        super(application);
        repository = Repository.getRepository(application);
    }

    public void addTask(String taskName, Date taskDueDate, List<String> taskFileNames
            , String taskDescription, String taskGroupName){
        Task task = new Task();
        task.setTaskName(taskName);
        task.setTaskDueDate(taskDueDate);
        task.setTaskFileNames(taskFileNames);
        task.setTaskDescription(taskDescription);
        task.setTaskGroupName(taskGroupName);
        repository.insertTask(task);
    }
}
