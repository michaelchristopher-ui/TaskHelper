package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.List;

@Entity(tableName = "taskGroup")
public class TaskGroup {
    @NonNull
    @PrimaryKey
    String taskGroupName;

    @TypeConverters(StringListConverter.class)
    List<String> taskNames;

    String taskGroupDescription;

    @NonNull
    public String getTaskGroupName() {
        return taskGroupName;
    }

    public void setTaskGroupName(@NonNull String taskGroupName) {
        this.taskGroupName = taskGroupName;
    }

    public List<String> getTaskNames() {
        return taskNames;
    }

    public void setTaskNames(List<String> taskNames) {
        this.taskNames = taskNames;
    }

    public String getTaskGroupDescription() {
        return taskGroupDescription;
    }

    public void setTaskGroupDescription(String taskGroupDescription) {
        this.taskGroupDescription = taskGroupDescription;
    }
}
