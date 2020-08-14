package com.example.todoapp20.models;

import java.io.File;
import java.util.Date;

public class TODOItem {
    private String name;
    private String comment;
    private boolean isFinished;
    private Date dateNeeded;
    private File file;

    public TODOItem(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public Date getDateNeeded() {
        return dateNeeded;
    }

    public void setDateNeeded(Date dateNeeded) {
        this.dateNeeded = dateNeeded;
    }

    public File getFile(){
        return file;
    }

    public void setFile(File file){
        this.file = file;
    }
}
