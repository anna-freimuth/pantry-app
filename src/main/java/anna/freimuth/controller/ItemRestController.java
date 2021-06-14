package anna.freimuth.controller;

import anna.freimuth.service.ItemService;
import anna.freimuth.service.requests.CreateItemRequest;
import anna.freimuth.service.requests.DeleteItemRequest;
import anna.freimuth.service.requests.PatchItemRequest;
import anna.freimuth.service.responses.ItemListResponse;
import anna.freimuth.service.responses.ItemResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/item")
@RestController
public class ItemRestController {
    private final ItemService itemService;

    public ItemRestController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new item")
    public ItemResponse createProduct(@RequestBody CreateItemRequest request) {

        return itemService.addItem(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deleting an item")
    public void deleteProduct(@PathVariable Long id) {

        DeleteItemRequest request = new DeleteItemRequest(id);
        itemService.deleteItem(request);
    }

    @PatchMapping
    @Operation(summary = "Changing the item")
    public ItemResponse patchProduct(@RequestBody PatchItemRequest request) {

        return itemService.patchItem(request);
    }

    @GetMapping
    @Operation(summary = "Getting a list of items")
    public ItemListResponse list() {
        return itemService.list();
    }
}
