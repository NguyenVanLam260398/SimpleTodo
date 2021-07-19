package com.example.simpletodo;
import android.view.View;
import android.widget.Button;

public class TaskCellView {
    int index;
    Button btnTodo;
    Button btnDone;
    Button btnDelete;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public TaskCellView(int index, Button btnTodo, Button btnDone, Button btnDelete) {
        this.index = index;
        this.btnTodo = btnTodo;
        this.btnDone = btnDone;
        this.btnDelete = btnDelete;
    }
    public void show(){
        btnTodo.setVisibility(View.VISIBLE);
        btnDone.setVisibility(View.VISIBLE);
        btnDelete.setVisibility(View.VISIBLE);
    }
    public void hiden(){
        btnTodo.setVisibility(View.INVISIBLE);
        btnDone.setVisibility(View.INVISIBLE);
        btnDelete.setVisibility(View.INVISIBLE);
    }
}
