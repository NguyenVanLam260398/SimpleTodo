package com.example.simpletodo;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
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
        items.add(new ToDo("One",false));
        items.add(new ToDo("Two",false));
        toDoAdapter = new ToDoAdapter(MainActivity.this,R.layout.itemview,items);
        listView.setAdapter(toDoAdapter);
        btn_AddItem.setOnClickListener(view -> {
            items.add(new ToDo(edtText.getText().toString(),false));
            toDoAdapter.notifyDataSetChanged();
            edtText.setText("");
        });
        btn_search.setOnClickListener(view -> {
            for (int i=0;i< items.size();i++) {
                String text=edt_search.getText().toString();
                if(text.equals(items.get(i).toDo) ){
                    ToDo toDo=items.get(i);
                    items.clear();
                    items.add(toDo);
                }
            }
            toDoAdapter.notifyDataSetChanged();
        });

        btn_Done.setOnClickListener(view -> {
            for (int i=0;i< items.size();i++) {
                if(items.get(i).isDone == false){
                    items.remove(i);
                }
            }
            toDoAdapter.notifyDataSetChanged();
        });
        btn_unDone.setOnClickListener(view -> {
            for (int i=0;i< items.size();i++) {
                if(items.get(i).isDone == true){
                    items.remove(i);
                }
            }
            toDoAdapter.notifyDataSetChanged();
        });

        setUpListViewListener();
    }

    private void setUpListViewListener(){
            listView.setOnItemLongClickListener((adapterView, view, i, l) -> {
            items.remove(i);
            toDoAdapter.notifyDataSetChanged();
            return true;
        });
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