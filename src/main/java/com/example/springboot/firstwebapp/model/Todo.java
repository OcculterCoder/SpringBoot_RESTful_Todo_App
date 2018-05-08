package com.example.springboot.firstwebapp.model;

import java.util.Date;

import javax.validation.constraints.Size;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class Todo {
    private int id;
    private String user;
    @Size(min=10, message="Enter at least 10 characters")
    private String desc;
    private Date targetDate;
    private boolean isDone;
    
    //This blank constructor is necessary in order to make
    //command bean/hibernate validation successful. Because when i am using 
    //service.addTodo() method i am not entering id but in 
    //@RequestMapping(value = "/addTodo", method = RequestMethod.GET) i am using
    //todo constructor giving id. it will throw error if i don't use blank contructor
    
    public Todo(){
    	super();
    }
    

    public Todo(int id, String user, String desc, Date targetDate,
            boolean isDone) {
        super();
        this.id = id;
        this.user = user;
        this.desc = desc;
        this.targetDate = targetDate;
        this.isDone = isDone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Todo other = (Todo) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format(
                "Todo id=%s, user=%s, desc=%s, targetDate=%s, isDone=%s\n", id,
                user, desc, targetDate, isDone);
    }

}