package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.sharedviewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.entities.Task;

public class SharedViewModelBetweenTaskAndTaskGroup extends ViewModel {

    MutableLiveData<Task> chosenTask = new MutableLiveData<Task>();

    public void setChosenTask(Task task){
        chosenTask.setValue(task);
    }

    public LiveData<Task> getChosenTask(){
        return chosenTask;
    }
}
