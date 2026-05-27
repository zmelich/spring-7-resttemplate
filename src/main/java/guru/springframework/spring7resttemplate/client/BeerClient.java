package guru.springframework.spring7resttemplate.client;

import guru.springframework.spring7resttemplate.model.BeerDTO;
import guru.springframework.spring7resttemplate.model.BeerStyle;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface BeerClient {

    Page<BeerDTO> listBeers();

    Page<BeerDTO> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventoryOnHand, Integer pageNumber, Integer pageSize);

    Page<BeerDTO> listBeers(String beerName);

    Page<BeerDTO> listBeers(BeerStyle beerStyle);

    Page<BeerDTO> listBeers(Boolean showInventoryOnHand);

    Page<BeerDTO> listBeers(Integer pageSize);

    Page<BeerDTO> listBeers(Integer pageNumber, Integer pageSize);

    Page<BeerDTO> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventoryOnHand, Integer pageNumber);

     Page<BeerDTO> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventoryOnHand);

     Page<BeerDTO> listBeers(String beerName, BeerStyle beerStyle);

     Page<BeerDTO> listBeers(String beerName, Boolean showInventoryOnHand);

     Page<BeerDTO> listBeers(BeerStyle beerStyle, Boolean showInventoryOnHand);

     Page<BeerDTO> listBeers(String beerName, Integer pageNumber, Integer pageSize);

     Page<BeerDTO> listBeers(BeerStyle beerStyle, Integer pageNumber, Integer pageSize);

     Page<BeerDTO> listBeers(Boolean showInventoryOnHand, Integer pageNumber, Integer pageSize);

    BeerDTO getBeerById(UUID beerId);
}
