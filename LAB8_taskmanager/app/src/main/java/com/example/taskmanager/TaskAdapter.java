package com.example.taskmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> taskList;
    private OnTaskClickListener listener;

    public interface OnTaskClickListener {
        void onDeleteClick(int position);
    }

    public TaskAdapter(List<Task> taskList, OnTaskClickListener listener) {
        this.taskList = taskList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.name.setText(task.getName());
        holder.dueDate.setText("Due: " + task.getDueDate());
        holder.priority.setText("Priority: " + task.getPriority());

        holder.itemView.setOnLongClickListener(v -> {
            listener.onDeleteClick(position);
            return true;
        });
    }

    @Override
    public int getItemCount() { return taskList.size(); }

    static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView name, dueDate, priority;

        TaskViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.taskName);
            dueDate = itemView.findViewById(R.id.taskDueDate);
            priority = itemView.findViewById(R.id.taskPriority);
        }
    }
}
