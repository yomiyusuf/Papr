package app.papr.papr.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import app.papr.papr.R;
import app.papr.papr.db.Task;

public class FragmentTasks extends Fragment {

    private TaskViewModel mTaskViewModel;
    public FragmentTasks(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tasks, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){

        RecyclerView recyclerView = getView().findViewById(R.id.recyclerViewTasks);
        final TaskListAdapter adapter = new TaskListAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mTaskViewModel = ViewModelProviders.of(getActivity()).get(TaskViewModel.class);

        Date today = getTodayDate();

        //Date today = Calendar.getInstance().getTime();

        mTaskViewModel.getTasksByDate(today).observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable List<Task> taskList) {
                adapter.setTasks(taskList);
            }
        });
    }

    public static Date getTodayDate(){
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
