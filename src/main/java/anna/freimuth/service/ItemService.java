package anna.freimuth.service;

import anna.freimuth.entity.Item;
import anna.freimuth.entity.ItemType;
import anna.freimuth.repo.ItemRepo;
import anna.freimuth.service.requests.CreateProductRequest;
import anna.freimuth.service.requests.DeleteProductRequest;
import anna.freimuth.service.requests.PatchItemRequest;
import anna.freimuth.service.responses.ItemResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ItemService {

    private final ItemTypeService itemTypeService;
    private final ItemRepo itemRepo;

    public ItemService(ItemTypeService itemTypeService, ItemRepo itemRepo) {
        this.itemTypeService = itemTypeService;
        this.itemRepo = itemRepo;
    }

    public ItemResponse addItem(CreateProductRequest request) {

        ItemType itemType = itemTypeService.addItemTypeIfNotExists(request);
        Item item = addItem(Item.fromRequest(request, itemType));
        return ItemResponse.fromItem(item);
    }

    public Item addItem(Item item) {
        return itemRepo.save(item);
    }

    public void deleteItem(DeleteProductRequest request) {

        Item item = itemRepo.findById(request.id).get();
        item.setDelete(true);
        itemRepo.save(item);
    }

    public ItemResponse patchItem(PatchItemRequest request) {

        Item item= findItem(request);
        Optional<ItemType> itemType = itemTypeService.getItemById(request.typeId.orElse(0L));
        item.update(request, itemType);
        itemRepo.save(item);
        return ItemResponse.fromItem(item);
    }

    public Item findItem(PatchItemRequest request){

        Optional<Item> item = itemRepo.findById(request.id);
        if (item.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "item not found");
        return itemRepo.findById(request.id).get();
    }
}
