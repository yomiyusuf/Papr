package app.papr.papr.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.papr.papr.R;
import app.papr.papr.db.Task;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskViewHolder> {

    private final LayoutInflater mInflater;
    private List<Task> mTasks;

    class TaskViewHolder extends RecyclerView.ViewHolder{
        private TextView desc;

        TaskViewHolder(View view){
            super(view);
            desc = view.findViewById(R.id.taskDesc);
        }
    }

    TaskListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    /*public TaskListAdapter(List<Task> taskList){
        this.mTasks = taskList;
    }*/

    void setTasks(List<Task> tasks){
        mTasks = tasks;
        notifyDataSetChanged();
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = mInflater.inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position){
        Task task = mTasks.get(position);
        holder.desc.setText(task.getDesc());
    }

    @Override
    public int getItemCount() {
        if (mTasks != null)
            return mTasks.size();
        else return 0;
    }
}
