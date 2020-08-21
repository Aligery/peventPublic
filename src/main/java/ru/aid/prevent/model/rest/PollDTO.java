package ru.aid.prevent.model.rest;


import com.fasterxml.jackson.annotation.JsonProperty;

//TODO reference to Telegram documentation. Can be more effectivly
public class PollDTO {


    private String question;
    private String[] options;
    @JsonProperty("is_anonymous")
    private boolean isAnonyomous;
    @JsonProperty("chat_id")
    private long chatId;



    public PollDTO() {
    }


    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public boolean isAnonyomous() {
        return isAnonyomous;
    }

    public void setAnonyomous(boolean anonyomous) {
        isAnonyomous = anonyomous;
    }
}
