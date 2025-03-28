package com.example.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskAdapter.OnTaskClickListener {

    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private TaskDatabaseHelper dbHelper;
    private List<Task> taskList;
    private Button addTaskBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        addTaskBtn = findViewById(R.id.addTaskBtn);
        dbHelper = new TaskDatabaseHelper(this);

        loadTasks(); // Load tasks from database

        addTaskBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEditTaskActivity.class);
            startActivityForResult(intent, 1);
        });
    }

    private void loadTasks() {
        taskList = dbHelper.getAllTasks();
        taskAdapter = new TaskAdapter(taskList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(taskAdapter);
    }

    @Override
    public void onDeleteClick(int position) {
        Task taskToDelete = taskList.get(position);
        dbHelper.deleteTask(taskToDelete.getId());
        taskList.remove(position);
        taskAdapter.notifyItemRemoved(position);
        Toast.makeText(this, "Task Deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            loadTasks(); // Refresh task list
        }
    }
}
