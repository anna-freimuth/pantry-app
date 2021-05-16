package anna.freimuth.controller;

import anna.freimuth.service.ItemService;
import anna.freimuth.service.requests.CreateProductRequest;
import anna.freimuth.service.requests.DeleteProductRequest;
import anna.freimuth.service.responses.ItemResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/products")
@RestController
public class ProductController {

    private  final ItemService itemService;

    public ProductController(ItemService itemService) {
        this.itemService = itemService;
    }


    @PostMapping("/add-product")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemResponse createProduct(@RequestBody CreateProductRequest request) {

        return itemService.addItem(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id){
        DeleteProductRequest request = new DeleteProductRequest(id);
        itemService.deleteItem(request);
    }
}
