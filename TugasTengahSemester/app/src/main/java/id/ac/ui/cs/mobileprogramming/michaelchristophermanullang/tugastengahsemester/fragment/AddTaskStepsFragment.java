package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.R;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.activities.MainActivity;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.entities.TaskSteps;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.sharedviewmodel.SharedViewModelBetweenListOfTaskGroupAndTaskGroup;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.sharedviewmodel.SharedViewModelBetweenTaskAndTaskGroup;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.sharedviewmodel.SharedViewModelBetweenTaskAndTaskSteps;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.viewmodel.AddTaskStepsViewModel;

public class AddTaskStepsFragment extends Fragment {
    EditText editTaskSteps;
    Button submitTaskSteps;
    private AddTaskStepsViewModel addTaskStepsViewModel;
    private SharedViewModelBetweenTaskAndTaskGroup sharedViewModelBetweenTaskAndTaskGroup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_task_steps, container, false);

        editTaskSteps = v.findViewById(R.id.editTaskSteps);
        submitTaskSteps = v.findViewById(R.id.submitTaskSteps);

        addTaskStepsViewModel = new AddTaskStepsViewModel(getActivity().getApplication());
        sharedViewModelBetweenTaskAndTaskGroup = new ViewModelProvider(requireActivity())
                .get(SharedViewModelBetweenTaskAndTaskGroup.class);

                submitTaskSteps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskStep = editTaskSteps.getText().toString();
                addTaskSteps(taskStep, sharedViewModelBetweenTaskAndTaskGroup.getChosenTask().getValue().getTaskName());
                ((MainActivity) getActivity()).replaceFragment(2);
            }
        });
        return v;
    }

    public void addTaskSteps(String taskStep, String taskName){
        addTaskStepsViewModel.addTaskSteps(taskStep, taskName);
    }
}
