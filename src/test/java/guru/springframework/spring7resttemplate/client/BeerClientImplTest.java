package guru.springframework.spring7resttemplate.client;

import guru.springframework.spring7resttemplate.model.BeerDTO;
import guru.springframework.spring7resttemplate.model.BeerStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerClientImplTest {

    @Autowired
    BeerClientImpl beerClient;

    @Test
    void getBeerById(){

        Page<BeerDTO> beerDTOs = beerClient.listBeers();

        BeerDTO dto = beerDTOs.getContent().get(0);

        BeerDTO byId = beerClient.getBeerById(dto.getId());

        assertNotNull(byId);
    }

    @Test
    void listBeersNoParams() {

        beerClient.listBeers();
    }

    @Test
    void listBeersBeerNameOnly() {

        beerClient.listBeers("ALE");

    }

    @Test
    void listBeersBeerStyleOnly() {
        beerClient.listBeers(BeerStyle.IPA);
    }


    @Test
    void listBeersAllParams() {
        beerClient.listBeers("ALE",  BeerStyle.ALE, false, 1,30);
    }

    @Test
    void testListBeersShowInventoryOnlyTrue() {
        beerClient.listBeers(true);
    }

    @Test
    void testListBeersShowInventoryOnlyFalse() {
        beerClient.listBeers(false);
    }


    @Test
    void testListBeersPageSizeOnly() {
        beerClient.listBeers(38);
    }

    @Test
    void testListBeersPageNumberPageSize() {
        beerClient.listBeers(3,29);
    }

    @Test
    void testListBeersNameStyleInventoryPageNumber() {
        beerClient.listBeers("Lager", BeerStyle.LAGER, false, 2);
    }

    @Test
    void testListBeersNameStyleInventory() {
        beerClient.listBeers("Lager", BeerStyle.LAGER, true);
    }

    @Test
    void testListBeersNameStyle() {
        beerClient.listBeers("Black", BeerStyle.LAGER);
    }

    @Test
    void testListBeersNameInventory() {
        beerClient.listBeers("Lager", true);
    }

    @Test
    void testListBeersStyleInventory() {
        beerClient.listBeers(BeerStyle.LAGER, false);
    }

    @Test
    void testListBeersNamePageNumberPageSize() {
        beerClient.listBeers("Ipa", 2, 12);
    }

    @Test
    void testListBeersStylePageNumberPageSize() {
        beerClient.listBeers(BeerStyle.PORTER, 3, 14);
    }

    @Test
    void testListBeersInventoryPageNumberPageSize() {
        beerClient.listBeers(false, 4, 21);
    }

}