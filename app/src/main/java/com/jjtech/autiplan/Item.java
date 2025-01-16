package com.jjtech.autiplan;

public class Item {
    private String title;
    private int imageResource;
    private boolean completed;
    private String time;

    public Item(String title, int imageResource) {
        this.title = title;
        this.imageResource = imageResource;
        this.completed = false;
        this.time = ""; // Você pode adicionar horários específicos depois
    }

    // Getters e Setters
    public String getTitle() { return title; }
    public int getImageResource() { return imageResource; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
}