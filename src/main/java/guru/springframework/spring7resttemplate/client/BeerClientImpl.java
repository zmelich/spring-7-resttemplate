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

@Service
@RequiredArgsConstructor
public class BeerClientImpl implements BeerClient {

    private final RestTemplateBuilder restTemplateBuilder;

    @Override
    public Page<BeerDTO> listBeers() {

        RestTemplate  restTemplate = restTemplateBuilder.build();

        ResponseEntity<String> stringResponseEntity = restTemplate
                .getForEntity("http://localhost:8080/api/v1/beer", String.class);

        System.out.println(stringResponseEntity.getBody());

        return null;
    }
}
