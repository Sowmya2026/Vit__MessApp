package com.example.vitmessapp;

public class MenuItem {
    private String name;
    private String description;
    // Other relevant fields

    // Constructor
    public MenuItem(String name, String description) {
        this.name = name;
        this.description = description;
        // Initialize other fields if needed
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // Setter for description
    public void setDescription(String description) {
        this.description = description;
    }

    // Other getters and setters for additional fields if needed
}
