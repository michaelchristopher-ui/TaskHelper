package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.R;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.entities.Task;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.entities.TaskGroup;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.sharedviewmodel.SharedViewModelBetweenTaskAndTaskGroup;

public class TaskGroupAdapter extends RecyclerView.Adapter<TaskGroupAdapter.TaskGroupViewHolder> {
    private OnTaskGroupListener onTaskGroupListener;
    private final LayoutInflater inflater;
    private List<TaskGroup> taskGroupList;

    class TaskGroupViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView textItemView;
        OnTaskGroupListener onTaskGroupListener;
        public TaskGroupViewHolder(@NonNull View view, OnTaskGroupListener onTaskGroupListener) {
            super(view);
            textItemView = view.findViewById(R.id.entityTaskText);
            this.onTaskGroupListener = onTaskGroupListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onTaskGroupListener.onTaskGroupClick(getAdapterPosition());
        }
    }


    public TaskGroupAdapter(Context context, TaskGroupAdapter.OnTaskGroupListener onTaskGroupListener) {
        inflater = LayoutInflater.from((context));
        this.onTaskGroupListener = onTaskGroupListener;
    }


    @NonNull
    @Override
    public TaskGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = inflater.inflate(R.layout.entity_task, parent, false);
        return new TaskGroupViewHolder(itemview, onTaskGroupListener );
    }

    @Override
    public void onBindViewHolder(@NonNull TaskGroupViewHolder holder, int position) {
        if (taskGroupList != null) {
            TaskGroup current = taskGroupList.get(position);
            Log.d("CurrentTaskGroup", current.getTaskGroupName());
            holder.textItemView.setText(current.getTaskGroupName());
        } else {
            holder.textItemView.setText("No Task Groups Yet. Create a new one!");
        }
    }

    public void setTaskGroups(List<TaskGroup> taskGroupList){
        this.taskGroupList = taskGroupList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (taskGroupList != null){
            return taskGroupList.size();
        }
        return 0;
    }

    public interface OnTaskGroupListener{
        void onTaskGroupClick(int position);
    }
}
