package ru.aid.pevent.data;

public enum TypeAggregateInfo {

    BAR(1, UrlConstants.BAR_URI),
    STUDY(3,UrlConstants.STUDY_URI),
    QUEST(5,UrlConstants.QUEST_URI_EVENT, UrlConstants.QUEST_URI_PLACE);

    private int typeId;
    private String[] uri;

    TypeAggregateInfo(int typeId, String... uri) {
        this.typeId = typeId;
        this.uri = uri;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String[] getUri() {
        return uri;
    }

    public void setUri(String[] uri) {
        this.uri = uri;
    }
}
