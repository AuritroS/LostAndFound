package com.example.lostandfound;
public class ItemModel {
    private int id;
    private String name;
    private String phone;
    private String description;
    private String date;
    private String location;
    private String status; // Lost or Found

    public ItemModel(int id, String name, String phone, String description, String date, String location, String status) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.description = description;
        this.date = date;
        this.location = location;
        this.status = status;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getDescription() { return description; }
    public String getDate() { return date; }
    public String getLocation() { return location; }
    public String getStatus() { return status; }
}
