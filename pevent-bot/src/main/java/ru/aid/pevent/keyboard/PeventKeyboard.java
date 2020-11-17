package ru.aid.pevent.keyboard;

import org.apache.commons.collections4.CollectionUtils;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import ru.aid.pevent.model.PlaceType;
import ru.aid.pevent.constants.UpdateConstants;
import ru.aid.pevent.model.entity.chat.ChatPevent;

import java.util.List;

public final class PeventKeyboard {

    public static ReplyKeyboardMarkup startMenu() {
        return ReplyKeyboardMarkupBuilder
                .create()
                .row()
                .button(UpdateConstants.GET_FUN) //this button must get location in future mb
//                .row()
//                .button(UpdateConstants.SEND_POLL_NOW)
                .build();
    }

    public static InlineKeyboardMarkup inlineKeyboardForEvent(){
        return InlineKeyboardMarkupBuilder
                .create()
                .row()
                .button(UpdateConstants.SEND_POLL_NOW, UpdateConstants.SEND_TO_POLL_QUERY)
                .build();
    }

    public static ReplyKeyboardMarkup funMenu() {
        return ReplyKeyboardMarkupBuilder
                .create()
                .row()
                .button(PlaceType.BAR.getName())
//                .button(PlaceType.BOARD_GAME.getName())
                .row()
                .button(PlaceType.QUEST.getName())
                .button(PlaceType.STUDY.getName())
                .row()
//                .button(PlaceType.TEAM_PLAY.getName())
                .button(UpdateConstants.START_MENU)
                .build();
    }

    public static InlineKeyboardMarkup chatsMenu(List<ChatPevent> chatNames) throws IllegalStateException {
        if (CollectionUtils.isNotEmpty(chatNames) && chatNames.size()>1) {
            InlineKeyboardMarkupBuilder builder = InlineKeyboardMarkupBuilder.create();
            for (ChatPevent event : chatNames) {
                builder.row().button(UpdateConstants.SEND_POLL_TO_CHAT_REF + event.getChatName(), String.valueOf(event.getTelegramChatId()));
            }
            return builder.build();
        } else {
            throw new IllegalStateException("List chatNames cant be empty");
        }
    }

    private PeventKeyboard(){}
}
