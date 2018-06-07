package app.papr.papr.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity(tableName = "tasks")
public class Task {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @NonNull
    private String desc;

    @NonNull
    private Date date_added;

    @NonNull
    private Date date_due;

    @NonNull
    private boolean completed;

    public Task(String desc, Date date_added, Date date_due) {
        this.desc = desc;
        this.date_added = date_added;
        this.date_due = date_due;
        this.completed = false;
    }

    public String getDesc() {
        return desc;
    }

    public Date getDate_added() {
        return date_added;
    }

    public Date getDate_due() {
        return date_due;
    }

    public boolean isCompleted() {
        return completed;
    }
}
