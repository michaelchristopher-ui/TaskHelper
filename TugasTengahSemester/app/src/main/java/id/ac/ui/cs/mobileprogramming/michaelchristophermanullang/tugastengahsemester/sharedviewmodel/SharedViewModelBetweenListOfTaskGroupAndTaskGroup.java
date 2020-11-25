package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.sharedviewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.entities.TaskGroup;

public class SharedViewModelBetweenListOfTaskGroupAndTaskGroup extends ViewModel {

    MutableLiveData<TaskGroup> chosenTaskGroup = new MutableLiveData<TaskGroup>();

    public void setChosenTaskGroup(TaskGroup taskGroup){
        chosenTaskGroup.setValue(taskGroup);
    }

    public LiveData<TaskGroup> getChosenTaskGroup(){
        return chosenTaskGroup;
    }
}
