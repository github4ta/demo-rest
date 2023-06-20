package com.typicode.jsonplaceholder;

public class PostObject {
    private int userId;
    private int id;
    private String title;
    private String body;

    public PostObject(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }
}
