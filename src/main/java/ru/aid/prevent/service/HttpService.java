package ru.aid.prevent.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.aid.prevent.model.rest.PollDTO;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static ru.aid.prevent.bot.PeventBotUpdateHandler.BOT_TOKEN;

public class HttpService {


    private static final ObjectMapper objectMapper = new ObjectMapper();
    //TODO В дальнейшем желательно вынести в отдельную историю
    private static final HttpClient httpClient = HttpClient.newHttpClient();


    public static void sendPollToGroupChat(PollDTO pollDTO){
        try {
            String jsonBody = objectMapper.writeValueAsString(pollDTO);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.telegram.org/bot" + BOT_TOKEN + "/sendPoll"))
                    .timeout(Duration.ofMinutes(1))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Poll sended, status code is: " + response.statusCode());
        } catch (IOException e) {
            //TODO logger
            e.printStackTrace();
        } catch (InterruptedException e) {
            //TODO logger
            e.printStackTrace();
        }

    }

}
