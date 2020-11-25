package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.R;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.activities.MainActivity;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.adapter.TaskAdapter;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.entities.Task;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.sharedviewmodel.SharedViewModelBetweenListOfTaskGroupAndTaskGroup;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.viewmodel
        .AddTaskViewModel;

public class AddTaskFragment extends Fragment {
    AddTaskViewModel addTaskViewModel;

    Button submitNewTaskButton;
    EditText date;
    EditText nameText;
    EditText descriptionText;
    String taskGroupName = "";
    SharedViewModelBetweenListOfTaskGroupAndTaskGroup sharedViewModelBetweenListOfTaskGroupAndTaskGroup;

    public void addTask(String taskName, Date taskDueDate, String taskDescription, String taskGroupName){
        List<String> taskFileNames = new ArrayList<String>();
        addTaskViewModel.addTask(taskName, taskDueDate, taskFileNames, taskDescription, taskGroupName);
        return;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_task, container, false);
        //Here lies the representation of the elements that appear on the layout
        submitNewTaskButton = v.findViewById(R.id.submitNewTask);
        descriptionText = v.findViewById(R.id.editTextTextMultiLine3);
        nameText = v.findViewById(R.id.editTextTextPersonName);

        addTaskViewModel = new AddTaskViewModel(getActivity().getApplication());
        sharedViewModelBetweenListOfTaskGroupAndTaskGroup = new ViewModelProvider(requireActivity())
                .get(SharedViewModelBetweenListOfTaskGroupAndTaskGroup.class);

        //If buttons exist, then these are its set on click listeners
        submitNewTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Submit to database first.
                //I referenced this page for how to get text from EditText
                //https://stackoverflow.com/questions/4531396/get-value-of-a-edit-text-field

                Log.d("submitNewTaskButton onClick", sharedViewModelBetweenListOfTaskGroupAndTaskGroup.getChosenTaskGroup()
                        .getValue().getTaskGroupName());

                addTask(nameText.getText().toString(), new Date(),descriptionText.getText().toString(),
                        sharedViewModelBetweenListOfTaskGroupAndTaskGroup.getChosenTaskGroup()
                                .getValue().getTaskGroupName());
                //Call fragment change method on activity
                //Will be replaced with AddTaskFragment
                ((MainActivity) getActivity()).replaceFragment(1);
            }
        });
        return v;
    }
}
