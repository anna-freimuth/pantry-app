package anna.freimuth;


import anna.freimuth.config.Config;
import anna.freimuth.entity.Item;
import anna.freimuth.entity.ItemType;
import anna.freimuth.repo.ItemRepo;
import anna.freimuth.repo.ItemTypeRepo;
import anna.freimuth.service.requests.CreateItemRequest;
import anna.freimuth.service.responses.ItemListResponse;
import anna.freimuth.service.responses.ItemResponse;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class ItemRestControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Config config;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ItemTypeRepo itemTypeRepo;

    @Autowired
    private Flyway flyway;

    @BeforeEach
    void init() {
        flywayReset();
        createItemTypeId1L();
    }

    private void flywayReset() {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    void addItemRestController() throws Exception {
        CreateItemRequest request = new CreateItemRequest();
        request.expiringDate = LocalDate.now();
        request.quantity = 5;
        request.itemTypeId = 1L;

        MvcResult result = mockMvc.perform(
            MockMvcRequestBuilders.post("/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content(config.objectMapper().writeValueAsString(request)))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andReturn();

        ItemResponse itemResponse = config.objectMapper().readValue(result.getResponse().getContentAsString(), ItemResponse.class);
        assertEquals(5, itemResponse.quantity);
        assertEquals(1L, itemResponse.id);
        assertEquals(1L, itemResponse.typeId);
    }

    @Test
    void patchItemRestController() throws Exception {
        Item item = new Item();
        item.setQuantity(5L);
        item.setExpiringDate(Date.valueOf(LocalDate.of(2021, 5, 6)));
        item.setItemType(itemTypeRepo.findById(1L).get());
        itemRepo.save(item);

        MvcResult result = mockMvc.perform(
            MockMvcRequestBuilders.patch("/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"id\": 1," +
                    "\"quantity\":6," +
                    "\"expiringDate\":\"2021-05-03\"" +
                    "}"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();

        ItemResponse itemResponse = config.objectMapper().readValue(result.getResponse().getContentAsString(), ItemResponse.class);
        assertEquals(6, itemResponse.quantity);
        assertEquals(1L, itemResponse.id);
        assertEquals(1L, itemResponse.typeId);
        assertEquals(Date.valueOf(LocalDate.of(2021, 5, 3)), itemResponse.expiringDate);
    }

    @Test
    void showAllItems() throws Exception {
        Item item1 = new Item();
        item1.setItemType(itemTypeRepo.findById(1L).get());
        item1.setQuantity(3);

        Item item2 = new Item();
        item2.setItemType(itemTypeRepo.findById(1L).get());
        item2.setQuantity(6);

        List<Item> itemList = List.of(item1, item2);
        itemList.forEach(item -> itemRepo.save(item));


        MvcResult result = mockMvc.perform(
            MockMvcRequestBuilders.get("/item")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();

        ItemListResponse response = config.objectMapper().readValue(result.getResponse().getContentAsString(), ItemListResponse.class);

        assertEquals(itemList.size(), response.list.size());

        for (int i = 0; i < response.list.size(); i++) {

            assertEquals(itemList.get(i).getQuantity(), response.list.get(i).quantity);
            assertEquals(itemList.get(i).getItemType().getId(), response.list.get(i).typeId);
        }
    }

    @Test
    void deleteItemRestController() throws Exception {
        Item item = new Item();
        item.setItemType(itemTypeRepo.findById(1L).get());
        item.setQuantity(5);
        item.setDelete(false);
        itemRepo.save(item);

        mockMvc.perform(
            MockMvcRequestBuilders.delete("/item/1")
        )
            .andExpect(MockMvcResultMatchers.status().isNoContent())
        ;

        Item deletedItem = itemRepo.findById(1L).get();
        assertEquals(true, deletedItem.getDelete());
    }


    @Test
    void deleteItemRestController_not_existing_item() throws Exception {

        mockMvc.perform(
            MockMvcRequestBuilders.delete("/item/1000")
        )
            .andExpect(MockMvcResultMatchers.status().isNotFound())
        ;
    }


    private void createItemTypeId1L() {

        if (itemTypeRepo.findById(1L).isEmpty()) {
            ItemType itemType = new ItemType();
            itemType.setTypeName("test-type");
            itemType.setDeleted(false);
            itemTypeRepo.save(itemType);
        }
    }
}
