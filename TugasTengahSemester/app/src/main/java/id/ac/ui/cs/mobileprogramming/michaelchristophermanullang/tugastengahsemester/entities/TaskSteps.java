package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"taskStep", "taskName"})
public class TaskSteps {
    @NonNull
    String taskStep;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @NonNull
    String taskName;

    @NonNull
    public String getTaskStep() {
        return taskStep;
    }

    public void setTaskStep(@NonNull String taskStep) {
        this.taskStep = taskStep;
    }
}
