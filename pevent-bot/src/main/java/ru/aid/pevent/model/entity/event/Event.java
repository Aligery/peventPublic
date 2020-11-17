package ru.aid.pevent.model.entity.event;

import org.apache.commons.lang3.StringUtils;

public class Event {

    private int eventId;
    private int eventTypeId;
    private String eventName;
    private String description;
    private String url;
    private String subway;

    public Event() {
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(int eventTypeId) {
        this.eventTypeId = eventTypeId;
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

    public String getSubway() {
        return subway;
    }

    public void setSubway(String subway) {
        this.subway = subway;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Название меропртиятия: ");
        sb.append(eventName);
//        sb.append(", краткое описание: '");
//        sb.append(description);
        if (StringUtils.isNotEmpty(url)) {
            sb.append('\'');
            sb.append(", сайт места: '");
            sb.append(url);
        }
        if (StringUtils.isNotEmpty(subway)) {
            sb.append('\'');
            sb.append(", станция метро: '");
            sb.append(subway);
        }

        return  sb.toString();
    }
}
