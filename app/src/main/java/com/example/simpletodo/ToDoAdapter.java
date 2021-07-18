package com.example.simpletodo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ToDoAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<ToDo> toDoList;

    public ToDoAdapter(Context context, int layout, List<ToDo> toDoList) {
        this.context = context;
        this.layout = layout;
        this.toDoList = toDoList;
    }

    @Override
    public int getCount() {
        return toDoList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout,null);
        TextView tv_ToDo = view.findViewById(R.id.tv_content);
        Button btn_Todo = view.findViewById(R.id.btn_TODO);
        Button btn_Done = view.findViewById(R.id.btn_Done);
        Button btn_Delete = view.findViewById(R.id.btn_delete);
        ImageView imgLine = view.findViewById(R.id.img_Line);
        tv_ToDo.setText(toDoList.get(i).toDo);
        btn_Todo.setVisibility(View.INVISIBLE);
        btn_Done.setVisibility(View.INVISIBLE);
        btn_Delete.setVisibility(View.INVISIBLE);
        imgLine.setVisibility(View.INVISIBLE);
        Log.d(MainActivity.TAG,"Zo ham");

        view.setOnTouchListener(new OnSwipeTouchListener(context){
            @Override
            public void onSwipeLeft() {
                btn_Todo.setVisibility(View.VISIBLE);
                btn_Done.setVisibility(View.VISIBLE);
                btn_Delete.setVisibility(View.VISIBLE);
                Log.d(MainActivity.TAG1,"Zo hamSwipe");

            }
        });
        btn_Delete.setOnClickListener(view1 -> {
            toDoList.remove(i);
            this.notifyDataSetChanged();
        });
        btn_Done.setOnClickListener(view1 -> {
            imgLine.setVisibility(View.VISIBLE);
            btn_Todo.setVisibility(View.INVISIBLE);
            btn_Done.setVisibility(View.INVISIBLE);
            btn_Delete.setVisibility(View.INVISIBLE);
        });
        btn_Todo.setOnClickListener(view1 -> {
            btn_Todo.setVisibility(View.INVISIBLE);
            btn_Done.setVisibility(View.INVISIBLE);
            btn_Delete.setVisibility(View.INVISIBLE);
        });

        return view;
    }
}
