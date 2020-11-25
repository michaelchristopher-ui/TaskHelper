package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.entities.Task;

//The use of LiveData in this class is based on the reference below
//https://developer.android.com/codelabs/android-training-livedata-viewmodel#5
@Dao
public interface TaskDao {
    @Query ("SELECT * FROM task")
    LiveData<List<Task>> getAll();

    @Query("SELECT * FROM task WHERE taskName LIKE :name")
    LiveData<List<Task>> getByName(String name);

    @Query("SELECT * FROM task WHERE taskGroupName LIKE :name")
    LiveData<List<Task>> getByTaskGroupName(String name);

    @Query("SELECT * FROM task WHERE taskGroupName LIKE :name")
    List<Task> getByTaskGroupNameSync(String name);

    @Delete
    public void delete(Task task);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Task task);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    public void update(Task task);
}
