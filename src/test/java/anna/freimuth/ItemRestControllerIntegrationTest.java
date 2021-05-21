package anna.freimuth;


import anna.freimuth.config.Config;
import anna.freimuth.entity.ItemType;
import anna.freimuth.repo.ItemRepo;

import anna.freimuth.repo.ItemTypeRepo;
import anna.freimuth.service.requests.CreateItemRequest;
import anna.freimuth.service.responses.ItemResponse;
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

import java.time.LocalDate;

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

    @BeforeEach
    void init() {
        createItemTypeId1L();
    }

    @Test
    void itemRestController () throws Exception {


        CreateItemRequest request = new CreateItemRequest();
        request.expiringDate = LocalDate.now();
        request.quantity = 5;
        request.itemTypeId = 1L;

        MvcResult result = mockMvc.perform(
            MockMvcRequestBuilders.post("/item")
            .contentType(MediaType.APPLICATION_JSON)
            .content(config.objectMapper().writeValueAsString(request)))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andReturn()
        ;

        ItemResponse itemResponse = config.objectMapper().readValue(result.getResponse().getContentAsString(), ItemResponse.class);
        assertEquals(5, itemResponse.quantity);
        assertEquals(1L, itemResponse.id);
        assertEquals(1L, itemResponse.typeId);
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
