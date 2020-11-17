package ru.aid.pevent.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import ru.aid.pevent.model.entity.event.Event;

public interface EventsMapper {

    Event getRandomNewEventByTypeId(@Param("typeId") int typeId, @Param("telegramUserId") int telegramUserId);

    void insertViewed(@Param("eventId") long eventPeventId, @Param("userId") long userId);

    Event getLastEventByUserForSendToChat(int userId);

}
