package ru.aid.pevent.service;


import org.apache.ibatis.session.SqlSession;
import ru.aid.pevent.model.PlaceType;
import ru.aid.pevent.model.entity.event.Event;
import ru.aid.pevent.mybatis.SqlSessionResourceManager;
import ru.aid.pevent.mybatis.mapper.EventsMapper;

public class PlaceService {

    private static final String START_TYPES = "Бар \nКвесты/ролевые игры \nКоммандные игры (в разработке) \nНастолки (в разработке) \nКонференции/совместное обучение";

    public static String getStartMessageTypes() {
        return START_TYPES;
    }

    public static String findNearFunByType(PlaceType placeType, int telegramUserId) {
        try (SqlSession session = SqlSessionResourceManager.getInstance().openNewSession()) {

            EventsMapper eventsMapper = session.getMapper(EventsMapper.class);

            Event event = eventsMapper.getRandomNewEventByTypeId(placeType.getTypeId(), telegramUserId);
            if (event==null) {
                return "Пока ничего нового нет, ты все просмотрел";
            }
            eventsMapper.insertViewed(event.getEventId(), telegramUserId);
            session.commit();
            return "Смотри, что есть по твоему запросу " + event.toString();

        }
    }

}
