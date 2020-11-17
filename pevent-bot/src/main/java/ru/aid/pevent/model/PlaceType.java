package ru.aid.pevent.model;

import ru.aid.pevent.constants.UpdateConstants;

public enum PlaceType {
    BAR(1, UpdateConstants.BAR_VALUE),
    TEAM_PLAY(2, UpdateConstants.TEAM_PLAY_VALUE),
    STUDY(3, UpdateConstants.STUDY_VALUE),
    BOARD_GAME(4, UpdateConstants.BOARDGAME_VALUE),
    QUEST(5, UpdateConstants.QUEST_VALUE);

    private int typeId;
    private String name;

    PlaceType(int typeId, String name){
        this.typeId = typeId;
        this.name = name;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getName() {
        return name;
    }
}
