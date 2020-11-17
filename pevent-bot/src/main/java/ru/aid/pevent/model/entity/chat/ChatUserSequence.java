package ru.aid.pevent.model.entity.chat;

public class ChatUserSequence {

    private long telegramChatId;
    private long telegramUserId;
    private boolean isLeader;
    private boolean isDeleted;

    public long getTelegramChatId() {
        return telegramChatId;
    }

    public void setTelegramChatId(long telegramChatId) {
        this.telegramChatId = telegramChatId;
    }

    public long getTelegramUserId() {
        return telegramUserId;
    }

    public void setTelegramUserId(long telegramUserId) {
        this.telegramUserId = telegramUserId;
    }

    public boolean isLeader() {
        return isLeader;
    }

    public void setLeader(boolean leader) {
        isLeader = leader;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
