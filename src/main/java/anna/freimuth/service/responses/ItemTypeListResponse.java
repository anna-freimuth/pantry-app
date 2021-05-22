package anna.freimuth.service.responses;

import anna.freimuth.entity.ItemType;

import java.util.ArrayList;

public class ItemTypeListResponse {
    public Iterable<ItemTypeResponse> list;

    public static ItemTypeListResponse fromIterable(Iterable<ItemType> list) {

        ArrayList<ItemTypeResponse> responses = new ArrayList<>();
        list.forEach(itemType -> responses.add(ItemTypeResponse.fromItemType(itemType)));

        ItemTypeListResponse itemTypeListResponse = new ItemTypeListResponse();
        itemTypeListResponse.list = responses;
        return itemTypeListResponse;
    }
}
