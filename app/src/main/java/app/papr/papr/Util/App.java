package app.papr.papr.Util;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.view.View;

public class App{

    public static void setStatusBarIconsColor(Activity activity, boolean shouldChangeStatusBarTintToDark) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = activity.getWindow().getDecorView();
            if (shouldChangeStatusBarTintToDark) {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                decor.setSystemUiVisibility(0);
            }
        }
    }


}
