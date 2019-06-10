package com.example.eventregistration.Model;

public class EventList {

    int imageId;
    String eventName;

    public EventList() {
    }

    public EventList(int imageId, String eventName) {
        this.imageId = imageId;
        this.eventName = eventName;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
