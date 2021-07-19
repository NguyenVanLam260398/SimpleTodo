package com.example.simpletodo;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnSearch;
    private Button btnAddItem;
    private Button btnUnDone;
    private Button btnDone;
    private Button btnAll;
    private EditText editText;
    private EditText editSearch;
    private ListView listView;
    private ArrayList<Task> tasks;
    private TasksAdapter TasksAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        tasks = new ArrayList<>();
        tasks.add(new Task("One"));
        tasks.add(new Task("Two hai", State.DONE));
        tasks.add(new Task("One ba"));
        tasks.add(new Task("Two bon", State.DONE));
        tasks.add(new Task("One nam"));
        tasks.add(new Task("Two sau", State.DONE));
        tasks.add(new Task("One bay"));
        tasks.add(new Task("Two 4", State.DONE));
        TasksAdapter = new TasksAdapter(MainActivity.this,R.layout.itemview, tasks);
        listView.setAdapter(TasksAdapter);
        btnAddItem.setOnClickListener(view -> {
            tasks.add(new Task(editText.getText().toString()));
            TasksAdapter.notifyDataSetChanged();
            editText.setText("");
        });
        btnSearch.setOnClickListener(view -> {
            List<Task> arrayFilterTask = new ArrayList<>();
            String inPutTitle= editSearch.getText().toString().toLowerCase();
            for (int i = 0; i< tasks.size(); i++) {
                String getTaskTitle = tasks.get(i).title.toLowerCase();
                if(getTaskTitle.contains(inPutTitle)){
                    arrayFilterTask.add(tasks.get(i));
                }
            }
            TasksAdapter.setTaskList(arrayFilterTask);
            TasksAdapter.notifyDataSetChanged();
        });

        btnDone.setOnClickListener(view -> {
            List<Task> arrayFilterTask = new ArrayList<>();
            for (Task task : tasks) {
                if(task.state == State.DONE){
                    arrayFilterTask.add(task);
                }
            }
            TasksAdapter.setTaskList(arrayFilterTask);
            TasksAdapter.notifyDataSetChanged();
        });
        btnUnDone.setOnClickListener(view -> {
            List<Task> arrayFilterTask = new ArrayList<>();
            for (Task task : tasks) {
                if(task.state == State.UNDONE){
                    arrayFilterTask.add(task);
                }
            }
            TasksAdapter.setTaskList(arrayFilterTask);
            TasksAdapter.notifyDataSetChanged();
        });
        btnAll.setOnClickListener(view -> {
            TasksAdapter.setTaskList(tasks);
            TasksAdapter.notifyDataSetChanged();
        });
    }
    private void bindViews(){
        listView = findViewById(R.id.listview);
        btnAddItem = findViewById(R.id.btn_AddItem);
        btnSearch = findViewById(R.id.btn_Search);
        btnAll = findViewById(R.id.btn_all);
        btnDone = findViewById(R.id.btn_Done);
        btnUnDone = findViewById(R.id.btn_unDone);
        editText = findViewById(R.id.edit);
        editSearch = findViewById(R.id.edt_Search);
    }
}