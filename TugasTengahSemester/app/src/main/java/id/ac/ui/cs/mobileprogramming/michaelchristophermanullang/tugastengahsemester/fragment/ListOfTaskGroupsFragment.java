package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.R;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.activities.MainActivity;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.adapter.TaskAdapter;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.adapter.TaskGroupAdapter;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.entities.Task;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.entities.TaskGroup;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.sharedviewmodel.SharedViewModelBetweenListOfTaskGroupAndTaskGroup;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.sharedviewmodel.SharedViewModelBetweenTaskAndTaskGroup;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.viewmodel.ListOfTaskGroupsViewModel;

public class ListOfTaskGroupsFragment extends Fragment implements TaskGroupAdapter.OnTaskGroupListener {

    RecyclerView listOfTaskGroupsRecyclerView;
    Button newTaskGroupButton;
    private TaskGroupAdapter taskGroupAdapter;

    private ListOfTaskGroupsViewModel listOfTaskGroupsViewModel;

    private SharedViewModelBetweenListOfTaskGroupAndTaskGroup sharedViewModelBetweenListOfTaskGroupAndTaskGroup;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedViewModelBetweenListOfTaskGroupAndTaskGroup = new ViewModelProvider(requireActivity()).get(SharedViewModelBetweenListOfTaskGroupAndTaskGroup.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_of_task_groups, container, false);
        listOfTaskGroupsRecyclerView = v.findViewById(R.id.listOfTaskGroupsRecyclerView);
        newTaskGroupButton = v.findViewById(R.id.newTaskGroupButton);

        //RecyclerView related initializations
        //Since This Fragment implements an onTaskListener, we can actually pass this class into the taskAdapter construction
        taskGroupAdapter = new TaskGroupAdapter(getContext(), this);
        listOfTaskGroupsRecyclerView.setAdapter(taskGroupAdapter);
        listOfTaskGroupsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        newTaskGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).replaceFragment(5); //AddTaskGroupFragment
            }
        });

        listOfTaskGroupsViewModel = new ListOfTaskGroupsViewModel(getActivity().getApplication());
        LiveData<List<TaskGroup>> arrayList = retrieveGroupTaskList();
        listOfTaskGroupsViewModel.retrieveTaskGroupList().observe(getViewLifecycleOwner(), new Observer<List<TaskGroup>>(){

            @Override
            public void onChanged(List<TaskGroup> taskGroups) {
                taskGroupAdapter.setTaskGroups(taskGroups);
            }
        });


        return v;
    }

    public LiveData<List<TaskGroup>> retrieveGroupTaskList(){
        return listOfTaskGroupsViewModel.retrieveTaskGroupList();
    }

    public void selectTaskGroup(){
        return;
    }

    @Override
    public void onTaskGroupClick(int position) {
        TaskGroup taskGroup = retrieveGroupTaskList().getValue().get(position);
        sharedViewModelBetweenListOfTaskGroupAndTaskGroup.setChosenTaskGroup(taskGroup);

        //TaskFragment
        ((MainActivity) getActivity()).replaceFragment(1);
    }
}
