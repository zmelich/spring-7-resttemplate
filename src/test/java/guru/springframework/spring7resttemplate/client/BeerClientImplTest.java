package guru.springframework.spring7resttemplate.client;

import guru.springframework.spring7resttemplate.model.BeerDTO;
import guru.springframework.spring7resttemplate.model.BeerStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerClientImplTest {

    @Autowired
    BeerClientImpl beerClient;

    @Test
    void testDeleteBeer(){
        BeerDTO newDTO = BeerDTO.builder()
                .price(new BigDecimal("9.99"))
                .beerName("Mango Bobs - To Delete")
                .beerStyle(BeerStyle.IPA)
                .quantityOnHand(240)
                .upc("123456789015")
                .build();

        BeerDTO beerDto = beerClient.createBeer(newDTO);

        beerClient.deleteBeer(beerDto.getId());

       assertThrows(HttpClientErrorException.class, () -> {
            //Should get error
            beerClient.getBeerById(beerDto.getId());
        });

    }

    @Test
    void testUpdateBeer() {
        BeerDTO newDTO = BeerDTO.builder()
                .price(new BigDecimal("9.99"))
                .beerName("Mango Bobs - Original")
                .beerStyle(BeerStyle.IPA)
                .quantityOnHand(240)
                .upc("123456789015")
                .build();

        BeerDTO beerDto = beerClient.createBeer(newDTO);

        final String newName = "Mango Bobs - Updated";
        beerDto.setBeerName(newName);
        BeerDTO updatedBeer = beerClient.updateBeer(beerDto);

        assertEquals(newName, updatedBeer.getBeerName());
    }

    @Test
    void testCreateBeer() {
        BeerDTO newDTO = BeerDTO.builder()
                .price(new BigDecimal("10.99"))
                .beerName("Mango Bobs")
                .beerStyle(BeerStyle.IPA)
                .quantityOnHand(500)
                .upc("123456789012")
                .build();

        BeerDTO savedDto = beerClient.createBeer(newDTO);
        assertNotNull(savedDto);
    }

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