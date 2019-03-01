package com.example.stone.demoapplication.entity;

public class HomeItemBean {
    private String name;
    private Class<?> activityName;

    public HomeItemBean() {

    }

    public HomeItemBean(String name, Class<?> activityName) {
        this.name = name;
        this.activityName = activityName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getActivityName() {
        return activityName;
    }

    public void setActivityName(Class<?> activityName) {
        this.activityName = activityName;
    }
}
