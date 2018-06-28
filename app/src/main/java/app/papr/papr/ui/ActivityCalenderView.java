package app.papr.papr.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import app.papr.papr.R;
import app.papr.papr.Util.App;

public class ActivityCalenderView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_view);
        App.setStatusBarIconsColor(this);


    }

}
