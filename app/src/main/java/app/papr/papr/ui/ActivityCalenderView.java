package app.papr.papr.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import app.papr.papr.R;
import app.papr.papr.Util.App;
import app.papr.papr.Util.DateUtil;
import app.papr.papr.ui.ViewModels.CalendarViewViewModel;

public class ActivityCalenderView extends AppCompatActivity implements OnDateSelectedListener{

    private CalendarViewViewModel mCalendarViewModel;

    MaterialCalendarView calender;
    TextView dateHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_view);
        App.setStatusBarIconsColor(this, true);
        mCalendarViewModel = ViewModelProviders.of(this).get(CalendarViewViewModel.class);

        calender = findViewById(R.id.calendar);
        dateHeader = findViewById(R.id.tv_tasks_header);

        if (mCalendarViewModel.getSelectedDate() == null)
            mCalendarViewModel.setSelectedDate(DateUtil.getTodayDate());

        calender.setOnDateChangedListener(this);

        //calender.setDateSelected(mCalendarViewModel.getSelectedDate(), true);
        dateHeader.setText(Html.fromHtml("Tasks for Tue" + "<b> 6th, July 2018 </b>"));
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        mCalendarViewModel.getTasksByDate(date.getDate());
        Toast.makeText(this, date.toString(), Toast.LENGTH_SHORT).show();
    }
}
