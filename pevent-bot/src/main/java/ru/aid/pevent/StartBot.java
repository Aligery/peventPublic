package ru.aid.pevent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.aid.pevent.handlers.PeventBotUpdateHandler;

public class StartBot {
    private static final Logger log = LoggerFactory.getLogger(StartBot.class);

    public static void main(String[] args) {
        try {
            log.info("Initialize Api Context");
            ApiContextInitializer.init();
            log.info("Instantiate Telegram Bots API");
            TelegramBotsApi botsApi = new TelegramBotsApi();
            log.info("Register PeventBOt bot");
            botsApi.registerBot(new PeventBotUpdateHandler());
        } catch (TelegramApiException e) {
            log.error("Catche error on register", e);
        }
    }


}
