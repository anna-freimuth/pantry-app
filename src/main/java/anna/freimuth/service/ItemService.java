package anna.freimuth.service;

import anna.freimuth.entity.Item;
import anna.freimuth.entity.ItemType;
import anna.freimuth.repo.ItemRepo;
import anna.freimuth.service.requests.CreateProductRequest;
import anna.freimuth.service.responses.ItemResponse;
import org.springframework.stereotype.Service;

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
}
