package com.andrewporshnev;

public class Device {
    private final String number;
    private final String description;

    public Device(String number, String description) {
        this.number = number;
        this.description = description;
    }

    public String getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Device{" +
                "number='" + number + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
