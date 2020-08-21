package ru.aid.prevent;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import ru.aid.prevent.bot.PeventBotUpdateHandler;

public class StartBot {

    //TODO mb web settings client in future

    public static void main(String[] args) {
        try {
        // Initialize Api Context
        ApiContextInitializer.init();
        // Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();
        // Register our bot

            botsApi.registerBot(new PeventBotUpdateHandler());
        } catch (Exception e) {
            //TODO refactor
            e.printStackTrace();
        }
    }

}
