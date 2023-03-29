package com.mohammadnasrallah.maliaassignment.domain;

import java.util.Objects;

public class MenuItem {
    private int id;
    private String title;

    public MenuItem(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}

