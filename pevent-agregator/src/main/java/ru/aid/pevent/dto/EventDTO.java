package ru.aid.pevent.dto;

public class EventDTO {

    private int eventTypeId;
    private String extIdent;
    private String eventName;
    private String description;
    private String url;
    private boolean isDeleted;
    private String subway;

    public EventDTO() {
    }

    public int getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(int eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public String getExtIdent() {
        return extIdent;
    }

    public void setExtIdent(String extIdent) {
        this.extIdent = extIdent;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getSubway() {
        return subway;
    }

    public void setSubway(String subway) {
        this.subway = subway;
    }
}
