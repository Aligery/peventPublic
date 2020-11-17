package ru.aid.pevent.handlers;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.aid.pevent.keyboard.PeventKeyboard;
import ru.aid.pevent.constants.UpdateConstants;
import ru.aid.pevent.model.PlaceType;
import ru.aid.pevent.model.entity.chat.ChatPevent;
import ru.aid.pevent.model.entity.event.Event;
import ru.aid.pevent.mybatis.SqlSessionResourceManager;
import ru.aid.pevent.mybatis.mapper.ChatsMapper;
import ru.aid.pevent.mybatis.mapper.EventsMapper;
import ru.aid.pevent.service.PlaceService;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class HandleTextAnswers {


    private static final Logger log = LoggerFactory.getLogger(HandleTextAnswers.class);

    private static final String regexJava = "(?<=chatId:)([\\s\\S]+?)(?=\\s)";
    private static final Pattern pattern = Pattern.compile(regexJava);

    private HandleTextAnswers(){};

    public static BotApiMethod<Message> handleUpdateAndGetAnswer(Update update) {
        //if command poll and chat return SendPoll
        if (update.getCallbackQuery()!=null && update.getCallbackQuery().getData().equalsIgnoreCase(UpdateConstants.SEND_TO_POLL_QUERY)) {
            List<ChatPevent> chats = getChatsByUser(update.getCallbackQuery().getFrom().getId());
            return pollToUserAnswersCommand(update, chats);
        } else if (update.getCallbackQuery()!=null) {
            long chatId = getChatIdFromMessage(update.getCallbackQuery().getData());
            return pollToChat(update, chatId);
        } else {
            return messageToChat(update);
        }

    }



    private static BotApiMethod<Message> messageToChat(Update update) {
        String commandMessage = update.getMessage().getText();

        int telegramUserId = update.getMessage().getFrom().getId();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setText("Команада: " + commandMessage + " не найдена");
        switch (commandMessage) {
            case UpdateConstants.START_COMMAND:
                sendMessage.setText("Добро пожаловать в бота, для обгечения в организации мероприятия, добавь меня в групповой чат, выбери тип мероприятия и я постараюсь помочь тебе его организовать и собрать конечную статистику");
                sendMessage.setReplyMarkup(PeventKeyboard.startMenu());
                break;
            case UpdateConstants.GET_FUN:
                String placesNearby = PlaceService.getStartMessageTypes();
                sendMessage.setText(placesNearby);
                sendMessage.setReplyMarkup(PeventKeyboard.funMenu());
                break;
            case UpdateConstants.START_MENU:
                sendMessage.setText("Что вы хотите сделать?");
                sendMessage.setReplyMarkup(PeventKeyboard.startMenu());
                break;
            case UpdateConstants.TEAM_PLAY_VALUE:
                sendMessage.setText(PlaceService.findNearFunByType(PlaceType.TEAM_PLAY, telegramUserId));
                if (!sendMessage.getText().equalsIgnoreCase("Пока ничего нового")) {
                    sendMessage.setReplyMarkup(PeventKeyboard.inlineKeyboardForEvent());
                }
                break;
            case UpdateConstants.BOARDGAME_VALUE:
                sendMessage.setText(PlaceService.findNearFunByType(PlaceType.BOARD_GAME, telegramUserId));
                if (!sendMessage.getText().startsWith("Пока ничего нового нет")) {
                    sendMessage.setReplyMarkup(PeventKeyboard.inlineKeyboardForEvent());
                }
                break;
            case UpdateConstants.BAR_VALUE:
                sendMessage.setText(PlaceService.findNearFunByType(PlaceType.BAR, telegramUserId));
                if (!sendMessage.getText().startsWith("Пока ничего нового нет")) {
                    sendMessage.setReplyMarkup(PeventKeyboard.inlineKeyboardForEvent());
                }
                break;
            case UpdateConstants.STUDY_VALUE:
                sendMessage.setText(PlaceService.findNearFunByType(PlaceType.STUDY, telegramUserId));
                if (!sendMessage.getText().startsWith("Пока ничего нового нет")) {
                    sendMessage.setReplyMarkup(PeventKeyboard.inlineKeyboardForEvent());
                }
                break;
            case UpdateConstants.QUEST_VALUE:
                sendMessage.setText(PlaceService.findNearFunByType(PlaceType.QUEST, telegramUserId));
                if (!sendMessage.getText().startsWith("Пока ничего нового нет")) {
                    sendMessage.setReplyMarkup(PeventKeyboard.inlineKeyboardForEvent());
                }
                break;
            default:
                break;
        }
        return sendMessage;
    }

    private static BotApiMethod<Message> pollToChat(Update update, long chatId) {
        SendPoll sendPoll = new SendPoll();
        sendPoll.setAnonymous(false);
        sendPoll.setChatId(chatId);

        sendPoll.setOptions(getOptionsForChat());

        StringBuffer buffer = new StringBuffer();
        buffer.append("Погнали в ");
        try (SqlSession sqlSession = SqlSessionResourceManager.getInstance().openNewSession()) {
            EventsMapper eventsMapper = sqlSession.getMapper(EventsMapper.class);
            Event event = eventsMapper.getLastEventByUserForSendToChat(update.getCallbackQuery().getFrom().getId());
            buffer.append(event.getEventName());
            if (StringUtils.isNotEmpty(event.getSubway())) {
                buffer.append(" Станция метро: ");
                buffer.append(event.getSubway());
            }
            buffer.append(" . Вот сайт мероприятия: ");
            buffer.append(event.getUrl());
        }

        sendPoll.setQuestion(buffer.toString());
        return sendPoll;
    }

    private static List<String> getOptionsForChat() {
        List<String> options = new ArrayList<>();
        options.add(UpdateConstants.YES);
        options.add(UpdateConstants.NO);
        return options;
    }

    private static List<ChatPevent> getChatsByUser(long telegramUserId) {
        try(SqlSession session = SqlSessionResourceManager.getInstance().openNewSession()) {
            ChatsMapper chatsMapper = session.getMapper(ChatsMapper.class);
            return chatsMapper.getChatsByTelegramUserId(telegramUserId);
        }
    }

    private static BotApiMethod<Message> chatsByUserToInlineKeyboardMessage(List<ChatPevent> chatPevents, Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("В какой чат вы хотите отправить запрос?");
        sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
        sendMessage.setReplyMarkup(PeventKeyboard.chatsMenu(chatPevents));
        return sendMessage;
    }



    private static BotApiMethod<Message> pollToUserAnswersCommand(Update update, List<ChatPevent> chats) {
        if (chats.size()==1) {
            return pollToChat(update, chats.get(0).getTelegramChatId());
        } else if (chats.size() > 1) {
            return chatsByUserToInlineKeyboardMessage(chats, update);
        } else {
            return chatsNotFoundMessage(update);
        }
    }

    private static long getChatIdFromMessage(String message) {
        return Long.valueOf(message);
    }

    private static BotApiMethod<Message> chatsNotFoundMessage(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getCallbackQuery().getFrom().getId().longValue());
        sendMessage.setText("Вы не добавили бота ни в какой чат.");
        return sendMessage;
    }
}
