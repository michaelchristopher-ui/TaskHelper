package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.fragment;

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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.R;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.activities.MainActivity;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.adapter.TaskAdapter;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.adapter.TaskStepsAdapter;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.entities.Task;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.entities.TaskSteps;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.sharedviewmodel.SharedViewModelBetweenTaskAndTaskGroup;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.viewmodel.TaskGroupViewModel;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.viewmodel.TaskViewModel;

public class TaskFragment extends Fragment {

    TextView taskName;
    TextView taskDescription;
    TextView taskDueDate;
    RecyclerView listOfTaskFile;
    Button newTaskFileButton;
    TextView taskDateOfCreation;

    SharedViewModelBetweenTaskAndTaskGroup sharedViewModelBetweenTaskAndTaskGroup;
    private TaskStepsAdapter taskStepsAdapter;
    private TaskViewModel taskViewModel;
    final List<TaskSteps>[] taskStepsList = new List[1];


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("TaskFragment", "at Oncreateview");
        View v = inflater.inflate(R.layout.fragment_list_of_task_steps, container, false);

        taskName = v.findViewById(R.id.taskName);
        taskDescription = v.findViewById(R.id.taskDescription);
        taskDateOfCreation = v.findViewById(R.id.taskDateOfCreation);

        listOfTaskFile = v.findViewById(R.id.listOfTaskSteps);
        taskStepsAdapter = new TaskStepsAdapter(getContext());
        listOfTaskFile.setAdapter(taskStepsAdapter);
        listOfTaskFile.setLayoutManager(new LinearLayoutManager(getContext()));

        newTaskFileButton = v.findViewById(R.id.newTaskStepsButton);

        newTaskFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).replaceFragment(3);
            }
        });

        taskViewModel = new TaskViewModel(getActivity().getApplication());

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedViewModelBetweenTaskAndTaskGroup = new ViewModelProvider(requireActivity()).get(SharedViewModelBetweenTaskAndTaskGroup.class);

        final String[] taskNameString = new String[1];

        //A task was passed to the shared model by the TaskGroupFragment.
        //We will use that and display it here
        sharedViewModelBetweenTaskAndTaskGroup.getChosenTask().observe(getViewLifecycleOwner(), (Observer) (task) ->{
            Task chosenTask = (Task) task;
            taskName.setText(chosenTask.getTaskName());
            taskDescription.setText((chosenTask.getTaskDescription()));
            taskDateOfCreation.setText(chosenTask.getTaskDueDate().toString());
            taskNameString[0] = chosenTask.getTaskName();
            Log.d("String",taskNameString[0]);
            taskViewModel.retrieveTaskStepsList(taskNameString[0]).observe(getViewLifecycleOwner(), new Observer<List<TaskSteps>>(){
                @Override
                public void onChanged(List<TaskSteps> tasks) {
                    taskStepsList[0] = tasks;
                    taskStepsAdapter.setTaskSteps(tasks);
                }
            });
        });
    }

    public void addFile(){
        return;
    }

    public void saveFile(String fileName){
        return;
    }
}
