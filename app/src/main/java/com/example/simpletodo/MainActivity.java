package com.example.simpletodo;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    private EditText edtText;
    private EditText edtSearch;
    private ListView listView;
    private ArrayList<Task> tasks;
    private com.example.simpletodo.toDoAdapter toDoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        tasks = new ArrayList<>();
        tasks.add(new Task("One"));
        tasks.add(new Task("Two hai", State.done));
        tasks.add(new Task("One ba"));
        tasks.add(new Task("Two bon", State.done));
        tasks.add(new Task("One nam"));
        tasks.add(new Task("Two sau", State.done));
        tasks.add(new Task("One bay"));
        tasks.add(new Task("Two 4", State.done));
        toDoAdapter = new toDoAdapter(MainActivity.this,R.layout.itemview, tasks);
        listView.setAdapter(toDoAdapter);
        btnAddItem.setOnClickListener(view -> {
            tasks.add(new Task(edtText.getText().toString()));
            toDoAdapter.notifyDataSetChanged();
            edtText.setText("");
        });
        btnSearch.setOnClickListener(view -> {
            List<Task> arrayFilterTask = new ArrayList<>();
            String inPutTitle= edtSearch.getText().toString().toLowerCase();
            for (int i = 0; i< tasks.size(); i++) {
                String getTaskTitle = tasks.get(i).title.toLowerCase();
                if(inPutTitle.contains(getTaskTitle) == true){
                    arrayFilterTask.add(tasks.get(i));
                }
            }
            toDoAdapter.setTaskList(arrayFilterTask);
            toDoAdapter.notifyDataSetChanged();
        });

        btnDone.setOnClickListener(view -> {
            List<Task> arrayFilterTask = new ArrayList<>();
            for (Task task : tasks) {
                if(task.state == State.done){
                    arrayFilterTask.add(task);
                }
            }
            toDoAdapter.setTaskList(arrayFilterTask);
            toDoAdapter.notifyDataSetChanged();
        });
        btnUnDone.setOnClickListener(view -> {
            List<Task> arrayFilterTask = new ArrayList<>();
            for (Task task : tasks) {
                if(task.state == State.unDone){
                    arrayFilterTask.add(task);
                }
            }
            toDoAdapter.setTaskList(arrayFilterTask);
            toDoAdapter.notifyDataSetChanged();
        });
        btnAll.setOnClickListener(view -> {
            toDoAdapter.setTaskList(tasks);
            toDoAdapter.notifyDataSetChanged();
        });
    }
    private void bindViews(){
        listView = findViewById(R.id.listview);
        btnAddItem = findViewById(R.id.btn_AddItem);
        btnSearch = findViewById(R.id.btn_Search);
        btnAll = findViewById(R.id.btn_all);
        btnDone = findViewById(R.id.btn_Done);
        btnUnDone = findViewById(R.id.btn_unDone);
        edtText = findViewById(R.id.edit);
        edtSearch = findViewById(R.id.edt_Search);
    }
}