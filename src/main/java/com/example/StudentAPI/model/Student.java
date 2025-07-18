package com.example.StudentAPI.model;

import java.util.UUID;

public class Student {
    private UUID id;
    private String name;
    private String contactNumber;
    private String address;

    public Student(UUID id, String name, String contactNumber, String address) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
        this.address = address;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getAddress() {
        return address;
    }
}
