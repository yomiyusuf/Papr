package app.papr.papr.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import app.papr.papr.Util.DateConverter;

@Dao
@TypeConverters(DateConverter.class)
public interface TaskDao {

    @Insert
    void insert(Task... task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);

    @Query("DELETE FROM tasks")
    void deleteAll();

    @Query("SELECT * FROM tasks ORDER BY date_added ASC")
    LiveData<List<Task>> getAllTasks();

    @Query("SELECT * FROM tasks WHERE date_due = :date ORDER BY date_added ASC")
    LiveData<List<Task>> getAllTasksDueOnDate(Date date);
}
