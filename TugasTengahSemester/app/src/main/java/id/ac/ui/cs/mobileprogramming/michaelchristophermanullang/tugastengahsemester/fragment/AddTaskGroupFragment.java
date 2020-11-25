package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.R;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.activities.MainActivity;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.viewmodel.AddTaskGroupViewModel;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.viewmodel.AddTaskViewModel;

public class AddTaskGroupFragment extends Fragment {
    AddTaskGroupViewModel addTaskGroupViewModel;
    EditText taskGroupName;
    EditText descriptionEditText;


    Button submitNewTaskGroup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_task_group, container, false);
        taskGroupName = v.findViewById(R.id.taskGroupName);
        descriptionEditText = v.findViewById(R.id.descriptionEditText);
        submitNewTaskGroup = v.findViewById(R.id.submitNewTaskGroup);

        addTaskGroupViewModel = new AddTaskGroupViewModel(getActivity().getApplication());

        submitNewTaskGroup.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                addTaskGroup(taskGroupName.getText().toString(), descriptionEditText.getText().toString());
                ((MainActivity) getActivity()).replaceFragment(0); //AddTaskGroupFragment
            }
        });

        return v;
    }

    public void addTaskGroup(String taskGroupName, String taskGroupDescription){
        List<String> taskNames = new ArrayList<>();
        addTaskGroupViewModel.addTaskGroup(taskGroupName, taskGroupDescription, taskNames);
    }


}
