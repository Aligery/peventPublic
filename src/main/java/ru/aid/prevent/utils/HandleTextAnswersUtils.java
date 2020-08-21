package ru.aid.prevent.utils;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import ru.aid.prevent.keyboard.ReplyKeyboardMarkupBuilder;
import ru.aid.prevent.model.ServiceType;
import ru.aid.prevent.model.UpdateConstants;
import ru.aid.prevent.model.rest.PollDTO;
import ru.aid.prevent.service.HttpService;
import ru.aid.prevent.service.PlaceService;

public final class HandleTextAnswersUtils {

    private HandleTextAnswersUtils(){};

    public static SendMessage handleUpdateAndGetAnswer(Update update) {

        String commandMessage = update.getMessage().getText();
        System.out.println(update.toString());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setText("echo " + commandMessage);
        handleUserTextMessage(sendMessage, commandMessage);

        return sendMessage;
    }

    private static ReplyKeyboardMarkup startMenu() {
        return ReplyKeyboardMarkupBuilder
                .create()
                .row()
                .button(UpdateConstants.SEND_TO_CHAT)
                .button(UpdateConstants.GET_FUN) //this button must get location
                .row()
                .button("я не придумал что делает эта кнопка")
                .build();
    }

    private static ReplyKeyboardMarkup getFunMenu() {
        return ReplyKeyboardMarkupBuilder
                .create()
                .row()
                .button(ServiceType.BAR.getName())
                .button(ServiceType.BOARDGAME.getName())
                .row()
                .button(ServiceType.QUEST.getName())
                .button(ServiceType.STUDY.getName())
                .row()
                .button(ServiceType.TEAM_PLAY.getName())
                .button(UpdateConstants.GET_BACK)
                .build();
    }

    private static SendMessage handleUserTextMessage(SendMessage sendMessage, String commandMessage) {
        switch (commandMessage) {
            case UpdateConstants.GET_FUN:
                String placesNearby = PlaceService.getTypes();
                sendMessage.setText(placesNearby);
                sendMessage.setReplyMarkup(getFunMenu());
                break;
            case UpdateConstants.SEND_TO_CHAT:
                PollDTO pollDTO = new PollDTO();
                pollDTO.setAnonyomous(false);
                pollDTO.setChatId(-431507416);
                pollDTO.setOptions(new String[]{"Да", "Пизда"});
                pollDTO.setQuestion("Идем на? %брать последнее просмотренное событие%");
                HttpService.sendPollToGroupChat(pollDTO);
                break;
            case UpdateConstants.GET_BACK:
                sendMessage.setReplyMarkup(startMenu());
                break;
            case UpdateConstants.TEAM_PLAY_VALUE:
            case UpdateConstants.BOARDGAME_VALUE:
            case UpdateConstants.BAR_VALUE:
            case UpdateConstants.STUDY_VALUE:
            case UpdateConstants.QUEST_VALUE:
                String placeNearby = PlaceService.findNearFunByType(commandMessage);
                sendMessage.setText(placeNearby);
            default:
                sendMessage.setReplyMarkup(startMenu());
                break;
        }
        return sendMessage;
    }
}
