package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.entities.TaskSteps;

@Dao
public interface TaskStepsDao {

    @Query("SELECT * FROM tasksteps WHERE taskName == :name")
    LiveData<List<TaskSteps>> getTaskStepsLiveDataByTaskName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(TaskSteps taskSteps);

}
