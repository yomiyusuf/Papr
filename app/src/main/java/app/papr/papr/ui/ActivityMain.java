package app.papr.papr.ui;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TouchDelegate;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import app.papr.papr.R;
import app.papr.papr.Util.App;

public class ActivityMain extends AppCompatActivity {
    boolean shouldChangeStatusBarTintToDark = true;
    final String FRAGMENT_TASKS = "fragmentTasks";

    FragmentManager fm = getSupportFragmentManager();

    TextView tv_date;
    View parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.setStatusBarIconsColor(this, shouldChangeStatusBarTintToDark);
        if(findViewById(R.id.fragment_tasks_list_container) != null){
            if(savedInstanceState != null){ //check if being restored from a previous state to prevent overlapping fragments
                return;
            }
            FragmentTodayTasks fragmentTasks = new FragmentTodayTasks();
            fragmentTasks.setArguments(getIntent().getExtras()); // Pass the Intent's extras to the fragment as arguments In case this activity was started with special instructions from an Intent
            fm.beginTransaction()
                    .add(R.id.fragment_tasks_list_container, fragmentTasks, FRAGMENT_TASKS).commit();
        }

        parent = findViewById(R.id.root);
        tv_date = findViewById(R.id.tv_date);

        //increase the touch area of tv_date to the right
        setTouchDelegate(parent, tv_date);

        tv_date.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActivityMain.this,  ActivityCalenderView.class);

                Bundle b = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    //b = ActivityOptions.makeScaleUpAnimation(view, 0, 0, view.getWidth(),
                    //                                         view.getHeight()).toBundle();
                    Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
                    bitmap.eraseColor(Color.parseColor("#308cf8"));

                    b = ActivityOptions.makeThumbnailScaleUpAnimation(view, bitmap, 0, 0).toBundle();
                }
                startActivity(i, b);

            }
        });
    }

    public void LoadBottom(View view) {
        /*View bottomSheet = getLayoutInflater().inflate(R.layout.bottomsheet_add_task, null);
        BottomSheetDialog addTaskDialog = new BottomSheetDialog(this);
        addTaskDialog.setContentView(bottomSheet);
        addTaskDialog.show();*/

        FragmentBottomAddTask fragmentBottomAddTask = new FragmentBottomAddTask();
        fragmentBottomAddTask.show(getSupportFragmentManager(), fragmentBottomAddTask.getTag());
    }


    private void setTouchDelegate(View parent, final View view){

        //Post in the parent's message queue to ensure that the parent lays out its children before call to getHeight
        parent.post(new Runnable() {
            @Override
            public void run() {
                Rect delegateArea = new Rect();
                view.getHitRect(delegateArea);
                delegateArea.right += 200;
                TouchDelegate expandedArea = new TouchDelegate(delegateArea, view);
                //give the delagate to an ancestor of the view we're delagating the area to
                if(View.class.isInstance(view.getParent())){
                    ((View) view.getParent()).setTouchDelegate(expandedArea);
                }
            }
        });
    }

    private void ShowKeyboard() {
        InputMethodManager imm = (InputMethodManager)   getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    private void HideKeyboard(){
        View view = this.getCurrentFocus();
        InputMethodManager imm = (InputMethodManager)getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
