package guru.springframework.spring7resttemplate.client;


/*
Created by Zsolt Melich (BT - IVR team)
*/

import guru.springframework.spring7resttemplate.model.BeerDTO;
import guru.springframework.spring7resttemplate.model.BeerDTOPageImpl;
import guru.springframework.spring7resttemplate.model.BeerStyle;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BeerClientImpl implements BeerClient {

    private final RestTemplateBuilder restTemplateBuilder;

    //private static final String BASE_URL = "http://localhost:8080";
    private static final String GET_BEER_PATH="/api/v1/beer";
    private static final String GET_BEER_BY_ID_PATH="/api/v1/beer/{beerId}";


    @Override
    public BeerDTO updateBeer(BeerDTO beerDto) {
        RestTemplate  restTemplate = restTemplateBuilder.build();

        restTemplate.put(GET_BEER_BY_ID_PATH, beerDto, beerDto.getId());

        return getBeerById(beerDto.getId());
    }

    @Override
    public BeerDTO getBeerById(UUID beerId) {
        RestTemplate  restTemplate = restTemplateBuilder.build();
        return restTemplate.getForObject(GET_BEER_BY_ID_PATH, BeerDTO.class, beerId);
    }

    @Override
    public BeerDTO createBeer(BeerDTO newDTO) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        //ResponseEntity<BeerDTO> responseEntity = restTemplate.postForEntity(GET_BEER_PATH, newDTO, BeerDTO.class);
        URI uri = restTemplate.postForLocation(GET_BEER_PATH, newDTO);

        return restTemplate.getForObject(uri.getPath(), BeerDTO.class);
    }

    @Override
    public Page<BeerDTO> listBeers() {
        return this.listBeers(null, null, null, null, null);
    }

    @Override
    public Page<BeerDTO> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventoryOnHand, Integer pageNumber, Integer pageSize) {
        RestTemplate  restTemplate = restTemplateBuilder.build();

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath(GET_BEER_PATH);

        if (beerName!=null){
            uriComponentsBuilder.queryParam("beerName", beerName);
        }
        if (beerStyle!=null){
            uriComponentsBuilder.queryParam("beerStyle", beerStyle.toString());
        }

        if (showInventoryOnHand!=null){
            uriComponentsBuilder.queryParam("showInventoryOnHand", Boolean.toString(showInventoryOnHand));
        }

        if (pageNumber != null) {
            uriComponentsBuilder.queryParam("pageNumber", pageNumber);
        }
        if (pageSize != null) {
            uriComponentsBuilder.queryParam("pageSize", pageSize);
        }

        ResponseEntity<BeerDTOPageImpl> response = restTemplate
                .getForEntity(uriComponentsBuilder.toUriString(), BeerDTOPageImpl.class);

        return response.getBody();
    }

    @Override
    public Page<BeerDTO> listBeers(String beerName) {

        RestTemplate  restTemplate = restTemplateBuilder.build();

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath(GET_BEER_PATH);

        if (beerName!=null){
            uriComponentsBuilder.queryParam("beerName", beerName);
        }

        ResponseEntity<BeerDTOPageImpl> response = restTemplate
                //.getForEntity(BASE_URL+GET_BEER_PATH, BeerDTOPageImpl.class);
                //.getForEntity(GET_BEER_PATH, BeerDTOPageImpl.class);
                .getForEntity(uriComponentsBuilder.toUriString(), BeerDTOPageImpl.class);

        return response.getBody();
    }

    @Override
    public Page<BeerDTO> listBeers(BeerStyle beerStyle) {
        RestTemplate  restTemplate = restTemplateBuilder.build();

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath(GET_BEER_PATH);

        if (beerStyle!=null){
            uriComponentsBuilder.queryParam("beerStyle", beerStyle.toString());
        }

        ResponseEntity<BeerDTOPageImpl> response = restTemplate
                .getForEntity(uriComponentsBuilder.toUriString(), BeerDTOPageImpl.class);

        return response.getBody();
    }

    @Override
    public Page<BeerDTO> listBeers(Boolean showInventoryOnHand) {
        RestTemplate  restTemplate = restTemplateBuilder.build();

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath(GET_BEER_PATH);

        if (showInventoryOnHand!=null){
            uriComponentsBuilder.queryParam("showInventoryOnHand", Boolean.toString(showInventoryOnHand));
        }

        ResponseEntity<BeerDTOPageImpl> response = restTemplate
                .getForEntity(uriComponentsBuilder.toUriString(), BeerDTOPageImpl.class);

        return response.getBody();
    }

    @Override
    public Page<BeerDTO> listBeers(Integer pageSize) {
        RestTemplate  restTemplate = restTemplateBuilder.build();

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath(GET_BEER_PATH);

        if (pageSize != null) {
            uriComponentsBuilder.queryParam("pageSize", pageSize);
        }

        ResponseEntity<BeerDTOPageImpl> response = restTemplate
                .getForEntity(uriComponentsBuilder.toUriString(), BeerDTOPageImpl.class);

        return response.getBody();
    }

    @Override
    public Page<BeerDTO> listBeers(Integer pageNumber, Integer pageSize) {
        RestTemplate  restTemplate = restTemplateBuilder.build();

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath(GET_BEER_PATH);

        if (pageNumber != null) {
            uriComponentsBuilder.queryParam("pageNumber", pageNumber);
        }
        if (pageSize != null) {
            uriComponentsBuilder.queryParam("pageSize", pageSize);
        }

        ResponseEntity<BeerDTOPageImpl> response = restTemplate
                .getForEntity(uriComponentsBuilder.toUriString(), BeerDTOPageImpl.class);

        return response.getBody();
    }

    @Override
    public Page<BeerDTO> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventoryOnHand, Integer pageNumber) {
        RestTemplate  restTemplate = restTemplateBuilder.build();

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath(GET_BEER_PATH);

        if (beerStyle!=null){
            uriComponentsBuilder.queryParam("beerStyle", beerStyle.toString());
        }

        if (showInventoryOnHand!=null){
            uriComponentsBuilder.queryParam("showInventoryOnHand", Boolean.toString(showInventoryOnHand));
        }

        if (pageNumber != null) {
            uriComponentsBuilder.queryParam("pageNumber", pageNumber);
        }

        ResponseEntity<BeerDTOPageImpl> response = restTemplate
                .getForEntity(uriComponentsBuilder.toUriString(), BeerDTOPageImpl.class);

        return response.getBody();
    }

    @Override
    public Page<BeerDTO> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventoryOnHand) {
        RestTemplate  restTemplate = restTemplateBuilder.build();

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath(GET_BEER_PATH);

        if (beerName!=null){
            uriComponentsBuilder.queryParam("beerName", beerName);
        }
        if (beerStyle!=null){
            uriComponentsBuilder.queryParam("beerStyle", beerStyle.toString());
        }

        if (showInventoryOnHand!=null){
            uriComponentsBuilder.queryParam("showInventoryOnHand", Boolean.toString(showInventoryOnHand));
        }

        ResponseEntity<BeerDTOPageImpl> response = restTemplate
                .getForEntity(uriComponentsBuilder.toUriString(), BeerDTOPageImpl.class);

        return response.getBody();
    }

    @Override
    public Page<BeerDTO> listBeers(String beerName, BeerStyle beerStyle) {
        RestTemplate  restTemplate = restTemplateBuilder.build();

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath(GET_BEER_PATH);

        if (beerName!=null){
            uriComponentsBuilder.queryParam("beerName", beerName);
        }
        if (beerStyle!=null){
            uriComponentsBuilder.queryParam("beerStyle", beerStyle.toString());
        }

        ResponseEntity<BeerDTOPageImpl> response = restTemplate
                .getForEntity(uriComponentsBuilder.toUriString(), BeerDTOPageImpl.class);

        return response.getBody();
    }

    @Override
    public Page<BeerDTO> listBeers(String beerName, Boolean showInventoryOnHand) {
        RestTemplate  restTemplate = restTemplateBuilder.build();

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath(GET_BEER_PATH);

        if (beerName!=null){
            uriComponentsBuilder.queryParam("beerName", beerName);
        }

        if (showInventoryOnHand!=null){
            uriComponentsBuilder.queryParam("showInventoryOnHand", Boolean.toString(showInventoryOnHand));
        }

        ResponseEntity<BeerDTOPageImpl> response = restTemplate
                .getForEntity(uriComponentsBuilder.toUriString(), BeerDTOPageImpl.class);

        return response.getBody();
    }

    @Override
    public Page<BeerDTO> listBeers(BeerStyle beerStyle, Boolean showInventoryOnHand) {
        RestTemplate  restTemplate = restTemplateBuilder.build();

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath(GET_BEER_PATH);

        if (beerStyle!=null){
            uriComponentsBuilder.queryParam("beerStyle", beerStyle.toString());
        }

        if (showInventoryOnHand!=null){
            uriComponentsBuilder.queryParam("showInventoryOnHand", Boolean.toString(showInventoryOnHand));
        }

        ResponseEntity<BeerDTOPageImpl> response = restTemplate
                .getForEntity(uriComponentsBuilder.toUriString(), BeerDTOPageImpl.class);

        return response.getBody();
    }

    @Override
    public Page<BeerDTO> listBeers(String beerName, Integer pageNumber, Integer pageSize) {
        RestTemplate  restTemplate = restTemplateBuilder.build();

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath(GET_BEER_PATH);

        if (beerName!=null){
            uriComponentsBuilder.queryParam("beerName", beerName);
        }
        if (pageNumber != null) {
            uriComponentsBuilder.queryParam("pageNumber", pageNumber);
        }
        if (pageSize != null) {
            uriComponentsBuilder.queryParam("pageSize", pageSize);
        }

        ResponseEntity<BeerDTOPageImpl> response = restTemplate
                .getForEntity(uriComponentsBuilder.toUriString(), BeerDTOPageImpl.class);

        return response.getBody();
    }

    @Override
    public Page<BeerDTO> listBeers(BeerStyle beerStyle, Integer pageNumber, Integer pageSize) {
        RestTemplate  restTemplate = restTemplateBuilder.build();

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath(GET_BEER_PATH);

        if (beerStyle!=null){
            uriComponentsBuilder.queryParam("beerStyle", beerStyle.toString());
        }
        if (pageNumber != null) {
            uriComponentsBuilder.queryParam("pageNumber", pageNumber);
        }
        if (pageSize != null) {
            uriComponentsBuilder.queryParam("pageSize", pageSize);
        }

        ResponseEntity<BeerDTOPageImpl> response = restTemplate
                .getForEntity(uriComponentsBuilder.toUriString(), BeerDTOPageImpl.class);

        return response.getBody();
    }

    @Override
    public Page<BeerDTO> listBeers(Boolean showInventoryOnHand, Integer pageNumber, Integer pageSize) {
        RestTemplate  restTemplate = restTemplateBuilder.build();

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath(GET_BEER_PATH);

        if (showInventoryOnHand!=null){
            uriComponentsBuilder.queryParam("showInventoryOnHand", Boolean.toString(showInventoryOnHand));
        }
        if (pageNumber != null) {
            uriComponentsBuilder.queryParam("pageNumber", pageNumber);
        }
        if (pageSize != null) {
            uriComponentsBuilder.queryParam("pageSize", pageSize);
        }

        ResponseEntity<BeerDTOPageImpl> response = restTemplate
                .getForEntity(uriComponentsBuilder.toUriString(), BeerDTOPageImpl.class);

        return response.getBody();
    }


}
