package anna.freimuth.service.responses;

import anna.freimuth.entity.Item;
import anna.freimuth.entity.ItemType;

import java.util.ArrayList;

public class ItemListResponse {
    public Iterable<ItemResponse> list;

    public static ItemListResponse fromIterable(Iterable<Item> list) {

        ArrayList<ItemResponse> responses = new ArrayList<>();
        list.forEach(item -> responses.add(ItemResponse.fromItem(item)));

        ItemListResponse itemListResponse = new ItemListResponse();
        itemListResponse.list = responses;
        return itemListResponse;
    }
}
