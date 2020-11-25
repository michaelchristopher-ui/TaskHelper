package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.sharedviewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.entities.TaskSteps;

public class SharedViewModelBetweenTaskAndTaskSteps extends ViewModel {

    MutableLiveData<TaskSteps> chosenTaskSteps = new MutableLiveData<TaskSteps>();

    public void setChosenTaskSteps(TaskSteps taskSteps){
        chosenTaskSteps.setValue(taskSteps);
    }

    public LiveData<TaskSteps> getChosenTaskSteps(){
        return chosenTaskSteps;
    }

}
