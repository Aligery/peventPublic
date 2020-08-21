package ru.aid.prevent.service;

import ru.aid.prevent.model.ServiceType;

public class PlaceService {

    private static final String TYPES = "1.Бар 2.Квесты/ролевые игры 3.Коммандные игры 4.Настолки 5.Конференции/совместное обучение";

    public static String getTypes() {
        return TYPES;
    }

    public static String findNearFunByType(String commandMessage) {
        //TODO blahblahblah some findning stuff
        return commandMessage + "есть рядом, но функционал поиска я еще не делал";
    }
}
