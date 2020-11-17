package ru.aid.pevent.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class InlineKeyboardMarkupBuilder {

    private List<List<InlineKeyboardButton>> keyboard;


    private InlineKeyboardMarkupBuilder() {}


    public static InlineKeyboardMarkupBuilder create() {
        InlineKeyboardMarkupBuilder builder = new InlineKeyboardMarkupBuilder();

        builder.setKeyboard();

        return builder;
    }

    public InlineKeyboardMarkupBuilder row() {
        List<InlineKeyboardButton> newRow = new ArrayList<>();
        this.keyboard.add(newRow);
        return this;
    }


    public InlineKeyboardMarkupBuilder button(String buttonText, String callBackText) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(buttonText);
        button.setCallbackData(callBackText);

        //TODO very bad style to get last element, rework in future
        this.keyboard.get(this.keyboard.size()-1).add(button);

        return this;
    }


    public InlineKeyboardMarkup build() {
        InlineKeyboardMarkup replyKeyboard = new InlineKeyboardMarkup();
        replyKeyboard.setKeyboard(keyboard);
        return replyKeyboard;
    }

    private InlineKeyboardMarkupBuilder setKeyboard() {
        this.keyboard = new ArrayList<>();
        return this;
    }

}
