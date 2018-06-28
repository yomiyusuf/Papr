package app.papr.papr.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Database(entities = {Task.class}, version = PaprDB.DB_VERSION)
public abstract class PaprDB extends RoomDatabase {

    static final int DB_VERSION = 1;
    private static PaprDB INSTANCE;

    public abstract TaskDao taskDao();

    //Make the db a singleton to avoid having multiple instances of the database opened.
    public static PaprDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PaprDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PaprDB.class, "paprDb")
                            //.fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void,Void,Void>{

        private final TaskDao mDao;

        PopulateDbAsync(PaprDB db){
            mDao = db.taskDao();
        }

        @Override
        protected Void doInBackground(final Void... params){

            mDao.deleteAll();
            Calendar cal = Calendar.getInstance();

            Date today = getTodayDate();

            Task task = new Task("Read Article",today, today);
            Task task2 = new Task("Buy Newspaper",today, today);
            Task task3 = new Task("Get new bicycle",today, today);
            Task task4 = new Task("Create new fork",today, today);
            Task task5 = new Task("Complete project",today, today);

            mDao.insert(task, task2, task3, task4, task5);

            return null;
        }
    }

    private static Date getTodayDate(){
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        Date todayWithZeroTime = null;
        try {
            todayWithZeroTime = formatter.parse(formatter.format(today));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return todayWithZeroTime;
    }
}
