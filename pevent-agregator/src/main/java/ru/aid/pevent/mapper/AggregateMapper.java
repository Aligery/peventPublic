package ru.aid.pevent.mapper;

import org.apache.ibatis.annotations.Param;
import ru.aid.pevent.dto.EventDTO;

import java.util.Collection;

public interface AggregateMapper {

    public void insertAggregatedEvent(@Param("events") Collection<EventDTO> eventDTO);

}
