package com.example.taskmanager;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddEditTaskActivity extends AppCompatActivity {

    private EditText taskNameInput, dueDateInput;
    private RadioGroup priorityGroup;
    private Button saveTaskBtn;
    private TaskDatabaseHelper dbHelper;
    private int taskId = -1; // Default to -1 (new task)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        taskNameInput = findViewById(R.id.taskNameInput);
        dueDateInput = findViewById(R.id.dueDateInput);
        priorityGroup = findViewById(R.id.prioritySpinner);
        saveTaskBtn = findViewById(R.id.saveTaskBtn);
        dbHelper = new TaskDatabaseHelper(this);

        Intent intent = getIntent();
        if (intent.hasExtra("taskId")) {
            taskId = intent.getIntExtra("taskId", -1);
            taskNameInput.setText(intent.getStringExtra("taskName"));
            dueDateInput.setText(intent.getStringExtra("dueDate"));

            String priority = intent.getStringExtra("priority");
            if (priority.equals("High")) {
                priorityGroup.check(R.id.priorityHigh);
            } else if (priority.equals("Medium")) {
                priorityGroup.check(R.id.priorityMedium);
            } else {
                priorityGroup.check(R.id.priorityLow);
            }
        }

        saveTaskBtn.setOnClickListener(v -> saveTask());
    }

    private void saveTask() {
        String taskName = taskNameInput.getText().toString().trim();
        String dueDate = dueDateInput.getText().toString().trim();
        int selectedPriorityId = priorityGroup.getCheckedRadioButtonId();
        String priority = ((RadioButton) findViewById(selectedPriorityId)).getText().toString();

        if (taskName.isEmpty() || dueDate.isEmpty()) {
            Toast.makeText(this, "Please enter all details", Toast.LENGTH_SHORT).show();
            return;
        }

        Task task = new Task(taskId, taskName, dueDate, priority);

        if (taskId == -1) {
            dbHelper.addTask(task);
            Toast.makeText(this, "Task Added", Toast.LENGTH_SHORT).show();
        } else {
            dbHelper.updateTask(taskId, task);
            Toast.makeText(this, "Task Updated", Toast.LENGTH_SHORT).show();
        }

        setResult(RESULT_OK);
        finish();
    }
}
