package ru.aid.pevent.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class ReplyKeyboardMarkupBuilder {

    private List<KeyboardRow> keyboard;


    private ReplyKeyboardMarkupBuilder() {}


    public static ReplyKeyboardMarkupBuilder create() {
        ReplyKeyboardMarkupBuilder builder = new ReplyKeyboardMarkupBuilder();

        builder.setKeyboard();

        return builder;
    }

    public ReplyKeyboardMarkupBuilder row() {
        KeyboardRow newRow = new KeyboardRow();
        this.keyboard.add(newRow);
        return this;
    }

    public ReplyKeyboardMarkupBuilder button(KeyboardButton button) {
        return this;
    }

    public ReplyKeyboardMarkupBuilder button(String text) {
        KeyboardButton button = new KeyboardButton(text);

        //TODO very bad style to get last element, rework in future
        this.keyboard.get(this.keyboard.size()-1).add(button);

        return this;
    }


    public ReplyKeyboardMarkup build() {
        ReplyKeyboardMarkup replyKeyboard = new ReplyKeyboardMarkup();
        replyKeyboard.setSelective(true);
        replyKeyboard.setResizeKeyboard(true);
        replyKeyboard.setOneTimeKeyboard(false);
        replyKeyboard.setKeyboard(keyboard);

        return replyKeyboard;
    }

    private ReplyKeyboardMarkupBuilder setKeyboard() {
        this.keyboard = new ArrayList<>();
        return this;
    }
}