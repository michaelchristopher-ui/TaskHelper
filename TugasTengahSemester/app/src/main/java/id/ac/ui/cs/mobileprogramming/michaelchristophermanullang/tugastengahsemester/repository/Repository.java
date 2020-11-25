package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.dao.TaskDao;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.dao.TaskGroupDao;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.dao.TaskStepsDao;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.database.AppDatabase;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.entities.Task;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.entities.TaskGroup;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.entities.TaskSteps;

public class Repository {

    //All Dao will be init here
    private TaskGroupDao taskGroupDao;
    private TaskDao taskDao;
    private TaskStepsDao taskStepsDao;

    //LiveDatas will be put here
    private LiveData<List<Task>> taskLiveData;
    private LiveData<List<TaskGroup>> taskGroupLiveData;
    private LiveData<List<TaskSteps>> taskStepsLiveData;

    private static Repository INSTANCE;

    public Repository(Application application){
        AppDatabase database = AppDatabase.getDatabase(application);

        //Get all the DAOs from the database
        taskGroupDao = database.taskGroupDao();
        taskDao = database.taskDao();
        taskStepsDao = database.taskStepsDao();

        //Get initial values of data inside the database
        taskLiveData = taskDao.getAll();
        taskGroupLiveData = taskGroupDao.getAll();
    }

    public static Repository getRepository(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new Repository(application);
        }
        return INSTANCE;
    }

    //Special for Provider
    public static Repository getRepository(){
        return INSTANCE;
    }

    public LiveData<List<Task>> getTaskLiveData(){
        return taskLiveData;
    }

    public LiveData<List<Task>> getTaskLiveDataWithTaskGroupNameAsync(String taskGroupName) {
        taskLiveData = taskDao.getByTaskGroupName(taskGroupName);
        return taskDao.getByTaskGroupName(taskGroupName);
    }

    public List<Task> getTaskLiveDataWithTaskGroupName(String taskGroupName) {
        return taskDao.getByTaskGroupNameSync(taskGroupName);
    }

    public void insertTask (Task task) {
        new insertAsyncTask(taskDao).execute(task);
    }

    public LiveData<List<TaskGroup>> getTaskGroupLiveData() {
        return taskGroupLiveData;
    }

    public void insertTaskGroup(TaskGroup taskGroup) { new insertTaskGroupAsyncTask(taskGroupDao).execute(taskGroup);
    }

    public LiveData<List<TaskGroup>> getTaskGroupByName(String taskGroupName) { return taskGroupDao.getByName(taskGroupName);
    }

    public void insertTaskSteps(TaskSteps taskSteps) { new insertTaskStepsAsyncTask(taskStepsDao).execute(taskSteps);}

    public LiveData<List<TaskSteps>> getTaskStepsByTaskName(String taskSteps) { return taskStepsDao.getTaskStepsLiveDataByTaskName(taskSteps);}

    private static class insertTaskStepsAsyncTask extends AsyncTask<TaskSteps, Void, Void> {
        private TaskStepsDao taskStepsDao;

        insertTaskStepsAsyncTask(TaskStepsDao dao) { taskStepsDao = dao; }

        @Override
        protected Void doInBackground(final TaskSteps... params){
            taskStepsDao.insert(params[0]);
            return null;
        }
    }

    private static class insertAsyncTask extends AsyncTask<Task, Void, Void> {
        private TaskDao taskDao;

        insertAsyncTask(TaskDao dao){
            taskDao = dao;
        }

        @Override
        protected Void doInBackground(final Task... params) {
            taskDao.insert(params[0]);
            return null;
        }
    }

    private static class insertTaskGroupAsyncTask extends AsyncTask<TaskGroup, Void, Void> {
        private TaskGroupDao taskGroupDao;

        insertTaskGroupAsyncTask(TaskGroupDao dao) { taskGroupDao = dao;}

        @Override
        protected Void doInBackground(TaskGroup... params) {
            taskGroupDao.insert(params[0]);
            return null;
        }
    }
}
