package guru.springframework.spring7resttemplate.client;


/*
Created by Zsolt Melich (BT - IVR team)
*/

import guru.springframework.spring7resttemplate.model.BeerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.JsonNode;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class BeerClientImpl implements BeerClient {

    private final RestTemplateBuilder restTemplateBuilder;

    private static final String BASE_URL = "http://localhost:8080";
    private static final String GET_BEER_PATH="/api/v1/beer";

    @Override
    public Page<BeerDTO> listBeers() {

        RestTemplate  restTemplate = restTemplateBuilder.build();

        ResponseEntity<String> stringResponseEntity = restTemplate
                .getForEntity(BASE_URL+GET_BEER_PATH, String.class);

        ResponseEntity<Map> mapResponseEntity = restTemplate
                .getForEntity(BASE_URL+GET_BEER_PATH, Map.class);


        ResponseEntity<JsonNode> jsonResponseEntity = restTemplate
                .getForEntity(BASE_URL+GET_BEER_PATH, JsonNode.class);


        jsonResponseEntity.getBody().findPath("content").forEach(jsonNode -> {
            System.out.println(jsonNode.get("beerName").asText());
        });

        System.out.println(stringResponseEntity.getBody());

        return null;
    }
}
