package anna.freimuth;

import anna.freimuth.config.Config;
import anna.freimuth.entity.ItemType;
import anna.freimuth.repo.ItemTypeRepo;
import anna.freimuth.service.requests.CreateItemTypeRequest;
import anna.freimuth.service.responses.ItemTypeResponse;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class ItemTypeRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Config config;


    @Autowired
    private ItemTypeRepo itemTypeRepo;

    @Autowired
    private Flyway flyway;

    @BeforeEach
    void init() {
        flywayReset();
    }

    private void flywayReset() {
        flyway.clean();
        flyway.migrate();
    }


    @Test
    void addItemTypeRestController() throws Exception {

        CreateItemTypeRequest request = new CreateItemTypeRequest();
        request.typeName = "test-type5";

        MvcResult result = mockMvc.perform(
            MockMvcRequestBuilders.post("/item-type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(config.objectMapper().writeValueAsString(request)))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andReturn();

        ItemTypeResponse itemResponse = config.objectMapper().readValue(result.getResponse().getContentAsString(), ItemTypeResponse.class);
        assertEquals(1L, itemResponse.id);
        assertEquals("test-type5", itemResponse.typeName);
    }

    @Test
    void deleteItemTypeRestController() throws Exception {
        ItemType itemType = new ItemType();
        itemType.setTypeName("test-type");
        itemTypeRepo.save(itemType);

        mockMvc.perform(
            MockMvcRequestBuilders.delete("/item-type/1")
        )
            .andExpect(MockMvcResultMatchers.status().isNoContent())
        ;

        ItemType deletedItem = itemTypeRepo.findById(1L).get();
        assertEquals(true, deletedItem.getDeleted());
    }

    @Test
    void deleteItemTypeRestController_not_existing_item() throws Exception {

        mockMvc.perform(
            MockMvcRequestBuilders.delete("/item-type/1000")
        )
            .andExpect(MockMvcResultMatchers.status().isNotFound())
        ;
    }
}

