package com.example.simpletodo;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "COM";
    public static final String TAG1 = "COM1";
    private Button btn_search;
    private Button btn_AddItem;
    private Button btn_unDone;
    private Button btn_Done;
    private EditText edtText;
    private EditText edt_search;
    private ListView listView;
    private ArrayList<ToDo> items;
    private ToDoAdapter toDoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findOB();
        items = new ArrayList<>();
        items.add(new ToDo("One"));
        items.add(new ToDo("Two"));
        //ReadItems();
        toDoAdapter = new ToDoAdapter(MainActivity.this,R.layout.itemview,items);
        listView.setAdapter(toDoAdapter);
        btn_AddItem.setOnClickListener(view -> {
            items.add(new ToDo(edtText.getText().toString()));
            toDoAdapter.notifyDataSetChanged();
            edtText.setText("");
            WriteItems();
        });
        btn_search.setOnClickListener(view -> {
            for (ToDo item : items) {
                if(edt_search.getText().toString().equals(item.toDo)){
                    items.clear();
                    Log.d(TAG, "onCreate: ");
                    items.add(new ToDo(edt_search.getText().toString()));
                    toDoAdapter.notifyDataSetChanged();
                    edt_search.setText("");
                    WriteItems();
                }
            }
        });
        setUpListViewListener();
    }

    private void setUpListViewListener(){
            listView.setOnItemLongClickListener((adapterView, view, i, l) -> {
            items.remove(i);
            toDoAdapter.notifyDataSetChanged();
            WriteItems();
            return true;
        });
    }
    private void ReadItems(){
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir,"todo.txt");
        try{
            items = new ArrayList<ToDo>(FileUtils.readLines(todoFile));
        }catch (IOException e){
            items = new ArrayList<>();
        }
    }
    private void WriteItems(){
        File filesDir = getFilesDir();
        File todoFile = new File (filesDir,"todo.txt");
        try{
            FileUtils.writeLines(todoFile, items);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void findOB(){
        listView = findViewById(R.id.listview);
        btn_AddItem = findViewById(R.id.btn_AddItem);
        btn_search = findViewById(R.id.btn_Search);
        btn_Done = findViewById(R.id.btn_Done);
        btn_unDone = findViewById(R.id.btn_unDone);
        edtText = findViewById(R.id.edit);
        edt_search = findViewById(R.id.edt_Search);
    }
}