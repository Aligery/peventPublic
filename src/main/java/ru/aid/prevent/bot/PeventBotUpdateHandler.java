package ru.aid.prevent.bot;

import org.apache.commons.lang3.StringUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.aid.prevent.utils.HandleTextAnswersUtils;

import java.net.http.HttpClient;

public class PeventBotUpdateHandler extends TelegramLongPollingBot {

    //TODO replace to properties or ia hz
    public static final String BOT_TOKEN = "937933979:AAEisoxF_0uMNNAskVJoqtWdevXLV_Jowa8";
    //TODO all magic words to properties file


    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    public void onUpdateReceived(Update update) {
        long chatId = update.getMessage().getChatId();

        long groupChatId = -431507416;
        //TODO save chatId to Database
        SendMessage sendMessage = HandleTextAnswersUtils.handleUpdateAndGetAnswer(update);
        if (StringUtils.isNotEmpty(sendMessage.getText())) {
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }


    public String getBotUsername(){
        return "BotName";
    }



}
