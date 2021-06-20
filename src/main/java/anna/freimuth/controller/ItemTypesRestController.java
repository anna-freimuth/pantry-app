package anna.freimuth.controller;

import anna.freimuth.service.ItemTypeService;
import anna.freimuth.service.requests.CreateItemTypeRequest;
import anna.freimuth.service.requests.DeleteItemTypeRequest;
import anna.freimuth.service.responses.ItemTypeListResponse;
import anna.freimuth.service.responses.ItemTypeResponse;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Create a new item")
    public ItemTypeResponse createProduct(@RequestBody CreateItemTypeRequest request) {

        return itemTypeService.addItem(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deleting an item")
    public void deleteProduct(@PathVariable Long id) {
        DeleteItemTypeRequest request = new DeleteItemTypeRequest(id);
        itemTypeService.deleteItem(request);
    }

    @GetMapping
    @Operation(summary = "Getting a list of items")
    public ItemTypeListResponse list() {
        return itemTypeService.list();
    }
}
