package app.papr.papr.ui.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.List;

import app.papr.papr.db.Task;
import app.papr.papr.db.TaskRepository;

public class TaskViewModel extends AndroidViewModel {

    private TaskRepository mRepository;
    private LiveData<List<Task>> mTasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        mRepository = new TaskRepository(application);
    }

    public LiveData<List<Task>> getAllTasks(){
        return mTasks;
    }

    public LiveData<List<Task>> getTasksByDate(Date date) {
        mTasks = mRepository.getAllTasksDueOnDate(date);
        return mTasks;
    }

    public void insert (Task task) {
        mRepository.insert(task);
    }
}
