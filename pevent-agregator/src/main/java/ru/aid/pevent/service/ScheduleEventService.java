package ru.aid.pevent.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.aid.pevent.mapper.AggregateMapper;

@Service
@EnableScheduling
public class ScheduleEventService {

    private static final int BATCH_SIZE = 5000;

    private static final Logger log = LoggerFactory.getLogger(ScheduleEventService.class);

    private HttpService httpService;
    private AggregateMapper aggregateMapper;

    @Autowired
    public ScheduleEventService(HttpService httpService, AggregateMapper aggregateMapper) {
        this.httpService = httpService;
        this.aggregateMapper = aggregateMapper;
    }


//    @Scheduled(cron = "0 3 * * *")
    @Scheduled(fixedDelay = 24*1000*60*60)
    public void aggregateAndInsertEvents() {
        log.info("Aggregate events started");
        httpService.getAndInsertEvents();
        log.info("Aggregate events ended");
    }
}
