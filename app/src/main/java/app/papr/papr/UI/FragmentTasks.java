package app.papr.papr.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import app.papr.papr.R;

public class FragmentTasks extends Fragment {
    public FragmentTasks(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tasks, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        EditText taskadd = (EditText) getView().findViewById(R.id.taskadd);

    }
}
