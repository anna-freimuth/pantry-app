package anna.freimuth.service;

import anna.freimuth.entity.ItemType;
import anna.freimuth.repo.ItemTypeRepo;
import anna.freimuth.service.requests.CreateItemTypeRequest;
import anna.freimuth.service.requests.DeleteItemTypeRequest;
import anna.freimuth.service.responses.ItemTypeListResponse;
import anna.freimuth.service.responses.ItemTypeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ItemTypeService {
    private final ItemTypeRepo itemTypeRepo;

    public ItemTypeService(ItemTypeRepo itemTypeRepo) {
        this.itemTypeRepo = itemTypeRepo;
    }

    public ItemTypeResponse addItem(CreateItemTypeRequest request) {

        ItemType item = getItem(request.typeName.strip());
        if (item != null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Item Type already exists");
        }
        item = addItem(ItemType.fromRequest(request));
        return ItemTypeResponse.fromItemType(item);
    }

    public void deleteItem(DeleteItemTypeRequest request) {

        ItemType item = getItem(request.id);
        item.setDeleted(true);
        itemTypeRepo.save(item);
    }

    public Optional<ItemType> getOptionalItemType(Long id) {

        return itemTypeRepo.findById(id);
    }

    public ItemType getItem(Long id) {

        Optional<ItemType> itemType = itemTypeRepo.findById(id);
        if (itemType.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "item type not found");
        }
        return itemType.get();
    }

    private ItemType getItem(String name) {

        return itemTypeRepo.findFirstByTypeName(name);
    }

    public ItemType addItem(ItemType request) {
        return itemTypeRepo.save(request);
    }

    public ItemTypeListResponse list() {
        return ItemTypeListResponse.fromIterable(itemTypeRepo.findAll());
    }
}
