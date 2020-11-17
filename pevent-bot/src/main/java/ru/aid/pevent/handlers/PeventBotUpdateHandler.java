package ru.aid.pevent.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.aid.pevent.constants.SecurityConstants;
import ru.aid.pevent.service.UserChatService;



public class PeventBotUpdateHandler extends TelegramLongPollingBot {


    private static final Logger log = LoggerFactory.getLogger(PeventBotUpdateHandler.class);

    @Override
    public String getBotToken() {
        return SecurityConstants.BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage()!=null) {
            UserChatService.handleTelegramsIdToPeventDB(update);
        }

        BotApiMethod<Message> answer = HandleTextAnswers.handleUpdateAndGetAnswer(update);
            try {
                execute(answer);
            } catch (TelegramApiException e) {
                log.error("Error while executing answer: ", e);
            }
        }


    public String getBotUsername(){
        return "PeventBot";
    }

}
