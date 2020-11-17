package ru.aid.pevent.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aid.pevent.data.TypeAggregateInfo;
import ru.aid.pevent.dto.EventDTO;
import ru.aid.pevent.dto.KudaGoPlace;
import ru.aid.pevent.dto.ResponseKudaGo;
import ru.aid.pevent.mapper.AggregateMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class HttpService {

    private static final String BAR_URI = "https://kudago.com/public-api/v1.4/places/?location=msk&categories=bar&fields=id,title,description,site_url,is_closed,subway&text_format=plain";
//    private static final String QUEST_URI="";
//    private static final String STUDY_URI="";
//    private static final String TEAM_GAME_URI="";


    private static final Logger log = LoggerFactory.getLogger(HttpService.class);

    private HttpClient httpClient;
    private ObjectMapper objectMapper;
    private AggregateMapper aggregateMapper;


    @Autowired
    public HttpService(HttpClient httpClient, ObjectMapper objectMapper, AggregateMapper aggregateMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
        this.aggregateMapper = aggregateMapper;
    }

    public void getAndInsertEvents(){
        log.info("get new events from kudago started");
        getEventsAndInsertToDB(TypeAggregateInfo.BAR);
        getEventsAndInsertToDB(TypeAggregateInfo.QUEST);
        getEventsAndInsertToDB(TypeAggregateInfo.STUDY);
        log.info("aggregated finished");

    }

    private void getEventsAndInsertToDB(TypeAggregateInfo type){
        List<HttpRequest> requests = new ArrayList<>();

        for(String uri : type.getUri()) {
            requests.add(getRequestByStringURI(uri));
        }

        requests.forEach(req -> {
            try {
                ResponseKudaGo kudaGo = doRequestAndInsertToDB(req, type.getTypeId());
                String nextPage = kudaGo.getNext();
                log.info("next URL was found {}", kudaGo.getNext());
                log.info("total count from service {}", kudaGo.getCount());
                while (StringUtils.isNotEmpty(nextPage)) {
                    HttpRequest nextReq = getRequestByStringURI(nextPage);

                    ResponseKudaGo nextResponseKudaGo = doRequestAndInsertToDB(nextReq, type.getTypeId());
                    log.info("Next page {}", nextResponseKudaGo.getNext());
                    nextPage=nextResponseKudaGo.getNext();

                }
            } catch (IOException|InterruptedException e) {
                log.error("Error while during REST response", e);
            }
        });


    }

    private ResponseKudaGo doRequestAndInsertToDB(HttpRequest request, int typeId) throws IOException, InterruptedException {
        HttpResponse<String> nextResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        ResponseKudaGo responseKudaGo = objectMapper.readValue(nextResponse.body(), ResponseKudaGo.class);

        List<EventDTO> eventDTOS = transformKudaGOToPevent(responseKudaGo.getResults(), typeId);
        aggregateMapper.insertAggregatedEvent(eventDTOS);

        return responseKudaGo;
    }

    private HttpRequest getRequestByStringURI(String nextPage){
        return HttpRequest.newBuilder().GET().uri(URI.create(nextPage)).build();
    }

    private static List<EventDTO> transformKudaGOToPevent(List<KudaGoPlace> kudaGoPlaces, int typeId) {
        List<EventDTO> pevents = new ArrayList<>();
        for (KudaGoPlace place : kudaGoPlaces) {
            EventDTO eventDTO = new EventDTO();
            eventDTO.setUrl(place.getSiteUrl());
            eventDTO.setExtIdent(String.valueOf(place.getId()));
            eventDTO.setDeleted(place.isClosed());
            eventDTO.setDescription(place.getDescription());
            eventDTO.setSubway(place.getSubway());
            eventDTO.setEventName(place.getTitle());
            eventDTO.setEventTypeId(typeId);

            pevents.add(eventDTO);
        }
        return pevents;
    }



}
