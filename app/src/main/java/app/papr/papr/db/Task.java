package app.papr.papr.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import app.papr.papr.Util.DateConverter;

@Entity(tableName = "tasks", indices = {@Index(value = {"date_added"})})
@TypeConverters(DateConverter.class)
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

    //constructor without id for other parts of the app to use. Use @Ignore annotation to hide it from Room
    @Ignore
    public Task(String desc, Date date_added, Date date_due) {
        this.desc = desc;
        this.date_added = date_added;
        this.date_due = date_due;
        this.completed = false;
    }

    //constructor for Room to use
    public Task(int id, String desc, Date date_added, Date date_due) {
        this.id = id;
        this.desc = desc;
        this.date_added = date_added;
        this.date_due = date_due;
        this.completed = false;
    }

    public int getId(){ return id; }

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


    public void setCompleted(@NonNull boolean done) {
        this.completed = done;
    }
}
