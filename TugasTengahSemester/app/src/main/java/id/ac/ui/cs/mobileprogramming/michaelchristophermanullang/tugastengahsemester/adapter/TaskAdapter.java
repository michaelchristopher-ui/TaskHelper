package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.R;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.entities.Task;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private OnTaskListener onTaskListener;
    private final LayoutInflater inflater;
    private List<Task> tasks; // Cached Copy of Task

    class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView textItemView;
        OnTaskListener onTaskListener;


        private TaskViewHolder(View view, OnTaskListener onTaskListener){
            super(view);
            textItemView = view.findViewById(R.id.entityTaskText);
            this.onTaskListener = onTaskListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onTaskListener.onTaskClick(getAdapterPosition());
        }
    }

    //Constructor
    public TaskAdapter(Context context, OnTaskListener onTaskListener) {
        inflater = LayoutInflater.from((context));
        this.onTaskListener = onTaskListener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = inflater.inflate(R.layout.entity_task, parent, false);
        return new TaskViewHolder(itemview, onTaskListener );
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        if (tasks != null) {
            Task current = tasks.get(position);
            holder.textItemView.setText(current.getTaskName());
        } else {
            holder.textItemView.setText("No Tasks Yet. Create a new one!");
        }

    }

    public void setTasks(List<Task> tasks){
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // the tasks List has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (tasks != null){
            return tasks.size();
        }
        return 0;
    }

    public interface OnTaskListener{
        void onTaskClick(int position);
    }
}
