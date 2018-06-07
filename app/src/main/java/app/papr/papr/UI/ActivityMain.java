package app.papr.papr.UI;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import app.papr.papr.R;

public class ActivityMain extends AppCompatActivity {
    boolean shouldChangeStatusBarTintToDark = true;
    final String FRAGMENT_TASKS = "fragmentTasks";

    FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setStatusBarIconsColor();
        if(findViewById(R.id.fragment_tasks_list_container) != null){
            if(savedInstanceState != null){ //check if being restored from a previous state to prevent overlapping fragments
                return;
            }
            FragmentTasks fragmentTasks = new FragmentTasks();
            fragmentTasks.setArguments(getIntent().getExtras()); // Pass the Intent's extras to the fragment as arguments In case this activity was started with special instructions from an Intent
            fm.beginTransaction()
                    .add(R.id.fragment_tasks_list_container, fragmentTasks, FRAGMENT_TASKS).commit();
        }
    }

    private void setStatusBarIconsColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            if (shouldChangeStatusBarTintToDark) {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                decor.setSystemUiVisibility(0);
            }
        }
    }

    public void LoadBottom(View view) {
        /*View bottomSheet = getLayoutInflater().inflate(R.layout.bottomsheet_add_task, null);
        BottomSheetDialog addTaskDialog = new BottomSheetDialog(this);
        addTaskDialog.setContentView(bottomSheet);
        addTaskDialog.show();*/

        FragmentBottomAddTask fragmentBottomAddTask = new FragmentBottomAddTask();
        fragmentBottomAddTask.show(getSupportFragmentManager(), fragmentBottomAddTask.getTag());
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
