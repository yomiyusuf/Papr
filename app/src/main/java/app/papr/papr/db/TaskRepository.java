package app.papr.papr.db;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.Date;
import java.util.List;

public class TaskRepository {

    private TaskDao mTaskDao;
    private LiveData<List<Task>> mTasks;

    public TaskRepository(Application application){
        PaprDB db = PaprDB.getDatabase(application);
        mTaskDao = db.taskDao();

    }

    public void insert(Task... task){
        new insertAsyncTask(mTaskDao).execute(task);
    }

    //public LiveData<List<Task>> getAllTasks(){return mTasks;}

    public LiveData<List<Task>> getAllTasksDueOnDate(Date date){
        mTasks = mTaskDao.getAllTasksDueOnDate(date);
        return mTasks;
    }

    private static class insertAsyncTask extends AsyncTask<Task, Void, Void>{

        private TaskDao mAsyncTaskDao;

        insertAsyncTask(TaskDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            mAsyncTaskDao.insert(tasks);
            return null;
        }
    }
}
