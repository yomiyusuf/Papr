package app.papr.papr.ui;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import app.papr.papr.R;

public class FragmentBottomAddTask extends BottomSheetDialogFragment {
    Button btn_save;
    EditText edit_text_new_tasks;
    RadioGroup rg_day;
    RadioButton rb_today;


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

        rg_day = getView().findViewById(R.id.radio_btn_group_day);
        //set the default selected RadioButton to today.
        rg_day.check(R.id.rb_today);

        btn_save = getView().findViewById(R.id.btn_save_tasks);
        btn_save.setOnClickListener(new View.OnClickListener() {
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
