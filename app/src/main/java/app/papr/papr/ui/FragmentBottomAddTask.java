package app.papr.papr.ui;

import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import app.papr.papr.R;

public class FragmentBottomAddTask extends BottomSheetDialogFragment {
    Button btnSave;

    public  FragmentBottomAddTask(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bottomsheet_add_task, container, false);

        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstances){
        btnSave = getView().findViewById(R.id.btn_save_tasks);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"saved", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDismiss(DialogInterface dialog){
        Toast.makeText(getActivity(), "Fragment Dismissed!",
                Toast.LENGTH_SHORT).show();
    }
}
