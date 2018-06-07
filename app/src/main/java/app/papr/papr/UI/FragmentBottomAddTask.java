package app.papr.papr.UI;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import app.papr.papr.R;

public class FragmentBottomAddTask extends BottomSheetDialogFragment {

    public  FragmentBottomAddTask(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.xxxx, container, false);

        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        return rootView;
    }

    @Override
    public void onDismiss(DialogInterface dialog){
        Toast.makeText(getActivity(), "Fragment Dismissed!",
                Toast.LENGTH_SHORT).show();
    }
}
