package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.R;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.entities.TaskGroup;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.entities.TaskSteps;

public class TaskStepsAdapter extends RecyclerView.Adapter<TaskStepsAdapter.TaskStepsViewHolder> {

    private final LayoutInflater inflater;
    private List<TaskSteps> taskStepsList;

    public TaskStepsAdapter(Context context) {
        inflater = LayoutInflater.from((context));
    }

    @NonNull
    @Override
    public TaskStepsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = inflater.inflate(R.layout.entity_task, parent, false);
        return new TaskStepsAdapter.TaskStepsViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskStepsViewHolder holder, int position) {
        if (taskStepsList != null) {
            TaskSteps current = taskStepsList.get(position);
            Log.d("CurrentTaskStep", current.getTaskStep());
            holder.textItemView.setText(current.getTaskStep());
        } else {
            holder.textItemView.setText("No Task Steps Yet. Create a new one!");
        }
    }


    //Harus kalo mau didisplay
    @Override
    public int getItemCount() {
        if (taskStepsList != null){
            return taskStepsList.size();
        }
        return 0;
    }

    public void setTaskSteps(List<TaskSteps> tasks) {
        this.taskStepsList = tasks;
        notifyDataSetChanged();
    }

    public class TaskStepsViewHolder extends RecyclerView.ViewHolder {
        private final TextView textItemView;

        public TaskStepsViewHolder(@NonNull View view) {
            super(view);
            textItemView = view.findViewById(R.id.entityTaskText);
        }

    }
}
