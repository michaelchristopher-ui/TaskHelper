package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.dao.TaskDao;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.dao.TaskGroupDao;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.dao.TaskStepsDao;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.entities.Task;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.entities.TaskGroup;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.entities.TaskSteps;

//This class is designed using the Singleton design pattern.
//References:
//https://developer.android.com/codelabs/android-training-livedata-viewmodel#6
//Avoid naming the database class "Database" since we need to import androidx.room.Database
@Database( entities = {Task.class, TaskGroup.class, TaskSteps.class}, version = 5)
public abstract class AppDatabase extends RoomDatabase {

    //DAO Abstract Methods go here
    public abstract TaskDao taskDao();
    public abstract TaskGroupDao taskGroupDao();
    public abstract TaskStepsDao taskStepsDao();

    //Below is the Database implemented using the Singleton Design pattern.
    //There will only be one Database instance. If it exists we fetch it, if it does not we create
    //then fetch it.
    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context){
        if (INSTANCE == null) {
            synchronized (Database.class){
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,
                    "app_database").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}
