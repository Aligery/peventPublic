package ru.aid.pevent.dto;

import java.util.List;

public class ResponseKudaGo {
    private int count;
    private String next;
    private String previous;
    private List<KudaGoPlace> results;

    public ResponseKudaGo() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<KudaGoPlace> getResults() {
        return results;
    }

    public void setResults(List<KudaGoPlace> results) {
        this.results = results;
    }
}
