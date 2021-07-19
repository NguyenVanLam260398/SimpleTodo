package com.example.simpletodo;

enum State{
    done, unDone
}
public class Task {
    public String title;
    public State state;

    public Task(String title) {
        this.title = title;
        this.state = State.unDone;
    }

    public Task(String title, State state) {
        this.title = title;
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
