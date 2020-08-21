package ru.aid.prevent.model;

public enum ServiceType {

    TEAM_PLAY(UpdateConstants.TEAM_PLAY_VALUE),
    BOARDGAME(UpdateConstants.BOARDGAME_VALUE),
    BAR(UpdateConstants.BAR_VALUE),
    STUDY(UpdateConstants.STUDY_VALUE),
    QUEST(UpdateConstants.QUEST_VALUE);


    private String name;

    ServiceType(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

}
