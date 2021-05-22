package anna.freimuth.controller;

import anna.freimuth.service.ItemTypeService;
import anna.freimuth.service.requests.CreateItemTypeRequest;
import anna.freimuth.service.requests.DeleteItemTypeRequest;
import anna.freimuth.service.responses.ItemTypeListResponse;
import anna.freimuth.service.responses.ItemTypeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/item-type")
@RestController
public class ItemTypesRestController {

    private final ItemTypeService itemTypeService;

    public ItemTypesRestController(ItemTypeService itemTypeService) {
        this.itemTypeService = itemTypeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemTypeResponse createProduct(@RequestBody CreateItemTypeRequest request) {

        return itemTypeService.addItem(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        DeleteItemTypeRequest request = new DeleteItemTypeRequest(id);
        itemTypeService.deleteItem(request);
    }

    @GetMapping
    public ItemTypeListResponse list() {
        return itemTypeService.list();
    }
}
