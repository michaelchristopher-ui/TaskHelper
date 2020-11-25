package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.R;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.activities.MainActivity;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.entities.Task;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.entities.TaskGroup;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.sharedviewmodel.SharedViewModelBetweenListOfTaskGroupAndTaskGroup;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.sharedviewmodel.SharedViewModelBetweenTaskAndTaskGroup;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.viewmodel.TaskGroupViewModel;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.adapter.TaskAdapter;

//implements swiperefreshlayout for listview refresh
public class TaskGroupFragment extends Fragment implements TaskAdapter.OnTaskListener {
    private TaskGroupViewModel taskGroupViewModel;
    private TaskAdapter taskAdapter;
    private SharedViewModelBetweenTaskAndTaskGroup sharedViewModelBetweenTaskAndTaskGroup;
    private SharedViewModelBetweenListOfTaskGroupAndTaskGroup sharedViewModelBetweenListOfTaskGroupAndTaskGroup;


    //Here lies the class variables for the things that appear on layout
    private Button newTaskButton;
    TextView taskGroupDescription;
    TextView taskGroupName;
    final List<Task>[] listOfTaskGroupList = new List[1];

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedViewModelBetweenTaskAndTaskGroup = new ViewModelProvider(requireActivity()).get(SharedViewModelBetweenTaskAndTaskGroup.class);
        sharedViewModelBetweenListOfTaskGroupAndTaskGroup = new ViewModelProvider(requireActivity()).get(SharedViewModelBetweenListOfTaskGroupAndTaskGroup.class);

        final String[] taskGroupNameString = new String[1];

        //A task group name was passed to the shared model by the TaskGroupFragment.
        //We will use that and display it here
        sharedViewModelBetweenListOfTaskGroupAndTaskGroup.getChosenTaskGroup().observe(getViewLifecycleOwner(), (Observer) (fetchedTaskGroup) ->{
            TaskGroup chosenTaskGroup = (TaskGroup) fetchedTaskGroup;
            taskGroupName.setText(chosenTaskGroup.getTaskGroupName());
            taskGroupDescription.setText((chosenTaskGroup.getTaskGroupDescription()));
            taskGroupNameString[0] = chosenTaskGroup.getTaskGroupName();
            taskGroupViewModel.retrieveTaskList(taskGroupNameString[0]).observe(getViewLifecycleOwner(), new Observer<List<Task>>(){
                @Override
                public void onChanged(List<Task> tasks) {
                    listOfTaskGroupList[0] = tasks;
                    Log.d("OnChanged on TaskGroupFragment, checking current task group name string", taskGroupNameString[0]);
                    Log.d("OnChanged on TaskGroupFragment", tasks.toString());
                    taskAdapter.setTasks(tasks);
                }
            });
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_list_of_task, container, false);
        //Here lies the representation of the elements that appear on the layout
        newTaskButton = v.findViewById(R.id.newTaskButton);
        taskGroupDescription = v.findViewById(R.id.taskGroupDescription);
        taskGroupName = v.findViewById(R.id.taskGroupName);

        //Refer to why I chose RecyclerView vs ListView for this implementation
        RecyclerView listOfTask = v.findViewById(R.id.listOfTask);

        //RecyclerView related initializations
        //Since This Fragment implements an onTaskListener, we can actually pass this class into the taskAdapter construction
        Log.d("TaskGroupFragment", this.toString());
        taskAdapter = new TaskAdapter(getContext(), this);
        listOfTask.setAdapter(taskAdapter);
        listOfTask.setLayoutManager(new LinearLayoutManager(getContext()));

        //If buttons exist, then these are its set on click listeners
        newTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Call fragment change method on activity
                //Will be replaced with AddTaskFragment
                ((MainActivity) getActivity()).replaceFragment(4);
            }
        });
        taskGroupViewModel = new TaskGroupViewModel(getActivity().getApplication());

        return v;
    }


    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public LiveData<List<Task>> retrieveTaskList(String taskGroupName){
        return taskGroupViewModel.retrieveTaskList(taskGroupName);
    }

    @Override
    public void onTaskClick(int position) {
        //Hacky solution
        Task task = listOfTaskGroupList[0].get(position);
        sharedViewModelBetweenTaskAndTaskGroup.setChosenTask(task);

        //TaskFragment
        ((MainActivity) getActivity()).replaceFragment(2);

    }
}
