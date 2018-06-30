package app.papr.papr.ui.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.List;

import app.papr.papr.db.Task;
import app.papr.papr.db.TaskRepository;

public class CalendarViewViewModel extends AndroidViewModel {

    private TaskRepository mRepository;
    private LiveData<List<Task>> mTasks;

    private Date selectedDate;

    public CalendarViewViewModel(@NonNull Application application) {
        super(application);
        mRepository = new TaskRepository(application);
    }

    public void setSelectedDate(Date date){
        selectedDate = date;
    }

    public Date getSelectedDate() {
        return selectedDate;
    }

    public LiveData<List<Task>> getTasksByDate(Date date) {
        mTasks = mRepository.getAllTasksDueOnDate(date);
        return mTasks;
    }
}
