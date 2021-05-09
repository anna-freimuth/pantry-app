package anna.freimuth.service;

import anna.freimuth.entity.ItemType;
import anna.freimuth.repo.ItemTypeRepo;
import anna.freimuth.service.requests.CreateProductRequest;
import org.springframework.stereotype.Service;

@Service
public class ItemTypeService {
    private final ItemTypeRepo itemTypeRepo;

    public ItemTypeService(ItemTypeRepo itemTypeRepo) {
        this.itemTypeRepo = itemTypeRepo;
    }

    public ItemType addItemTypeIfNotExists(CreateProductRequest request) {

        ItemType item = getItem(request.name.strip());
        if (item != null) return item;

        return addItem(ItemType.fromRequest(request));
    }

    private ItemType getItem(String name) {

        return itemTypeRepo.findFirstByTypeName(name);
    }

    private ItemType addItem(ItemType itemType) {

        return itemTypeRepo.save(itemType);
    }
}
