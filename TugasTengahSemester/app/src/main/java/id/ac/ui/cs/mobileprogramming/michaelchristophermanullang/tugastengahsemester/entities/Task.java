package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.entities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;
import java.util.List;

@Entity(tableName = "task", primaryKeys = {"taskName", "TaskGroupName"})
public class Task {
    @NonNull
    String taskName;

    @Nullable
    @TypeConverters(DateConverter.class)
    Date taskDueDate;

    @TypeConverters(StringListConverter.class)
    List<String> taskFileNames;

    @Nullable
    String TaskDescription;

    @NonNull
    String TaskGroupName;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getTaskDueDate() {
        return taskDueDate;
    }

    public void setTaskDueDate(Date taskDueDate) {
        this.taskDueDate = taskDueDate;
    }

    public List<String> getTaskFileNames() {
        return taskFileNames;
    }

    public void setTaskFileNames(List<String> taskFileNames) {
        this.taskFileNames = taskFileNames;
    }

    public String getTaskDescription() {
        return TaskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        TaskDescription = taskDescription;
    }

    public void setTaskGroupName(String taskGroupName) {this.TaskGroupName = taskGroupName;}

    public String getTaskGroupName() {return TaskGroupName;}
}
