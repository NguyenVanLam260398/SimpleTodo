package com.example.simpletodo;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class TasksAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<Task> taskList;
    List<TaskCellView> taskCellViewslist = new ArrayList<>();
    DoneElement doneElement;
    Remove remove;

    public TasksAdapter(Context context, int layout, List<Task> taskList, DoneElement doneElement, Remove remove) {
        this.context = context;
        this.layout = layout;
        this.taskList = taskList;
        this.doneElement = doneElement;
        this.remove = remove;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public int getCount() {
        return taskList.size();
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
    public View getView(int i, View view1, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layout,null);
        view.setTag(i);
        TextView tvToDo = view.findViewById(R.id.tv_content);
        Button btnTodo = view.findViewById(R.id.btn_TODO);
        Button btnDone = view.findViewById(R.id.btn_Done);
        Button btnDelete = view.findViewById(R.id.btn_delete);
        ImageView imgLine = view.findViewById(R.id.img_Line);

        tvToDo.setText(taskList.get(i).title);
        btnTodo.setVisibility(View.INVISIBLE);
        btnDone.setVisibility(View.INVISIBLE);
        btnDelete.setVisibility(View.INVISIBLE);
        taskCellViewslist.add(new TaskCellView(i,btnTodo,btnDone,btnDelete));
        if(taskList.get(i).state == State.UNDONE){
            imgLine.setVisibility(View.INVISIBLE);
        }else {
            imgLine.setVisibility(View.VISIBLE);
        }

        view.setOnTouchListener(new OnSwipeTouchListener(context){
            @Override
            public void onSwipeLeft() {
                for (TaskCellView taskCellView : taskCellViewslist) {
                    if(taskCellView.getIndex() == (int) view.getTag()){
                        taskCellView.show();
                    }else{
                        taskCellView.hiden();
                    }
                }
            }
        });

        btnDelete.setOnClickListener(view2 -> {
            remove.reMove(taskList.get(i).title);
            taskList.remove(i);
            this.notifyDataSetChanged();
        });

        btnDone.setOnClickListener(view2 -> {
            taskList.get(i).state = State.DONE;
            doneElement.doneElement(taskList);
            this.notifyDataSetChanged();
        });

        btnTodo.setOnClickListener(view2 -> {
            if(taskList.get(i).state == State.DONE){
                taskList.get(i).state = State.UNDONE;
            }else{
                taskList.get(i).state = State.UNDONE;
            }
            doneElement.doneElement(taskList);
            this.notifyDataSetChanged();
        });

        return view;
    }
}
