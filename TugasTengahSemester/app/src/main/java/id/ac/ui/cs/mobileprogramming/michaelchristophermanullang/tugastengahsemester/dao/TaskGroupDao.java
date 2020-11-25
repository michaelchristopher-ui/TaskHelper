package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.dao;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.entities.TaskGroup;

//The use of LiveData in this class is based on the reference below
//https://developer.android.com/codelabs/android-training-livedata-viewmodel#5
@Dao
public interface TaskGroupDao {
    @Query ("SELECT * FROM taskgroup")
    LiveData<List<TaskGroup>> getAll();

    @Query ("SELECT * FROM taskgroup")
    Cursor cursorGetAll();

    @Query("SELECT * FROM taskgroup WHERE taskGroupName LIKE :name")
    LiveData<List<TaskGroup>> getByName(String name);

    @Delete
    public void delete(TaskGroup taskGroup);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(TaskGroup taskGroup);
}
