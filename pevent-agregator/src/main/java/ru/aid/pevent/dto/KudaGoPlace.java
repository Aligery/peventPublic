package ru.aid.pevent.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KudaGoPlace {

    private int id;
    private String title;

    private String description;
    @JsonProperty("site_url")
    private String siteUrl;
    @JsonProperty("is_closed")
    private boolean isClosed;
    private String subway;

    public KudaGoPlace() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public String getSubway() {
        return subway;
    }

    public void setSubway(String subway) {
        this.subway = subway;
    }
}
