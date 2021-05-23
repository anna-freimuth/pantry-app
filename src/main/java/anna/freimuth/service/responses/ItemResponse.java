package anna.freimuth.service.responses;

import anna.freimuth.entity.Item;

import java.sql.Date;

public class ItemResponse {

    public long id;
    public long typeId;
    public String name;
    public long quantity;
    public Date addDate;
    public Date expiringDate;
    public Boolean deleted;

    public static ItemResponse fromItem(Item item) {

        ItemResponse response = new ItemResponse();

        response.id = item.getId();
        response.typeId = item.getItemType().getId();
        response.name = item.getItemType().getTypeName();
        response.quantity = item.getQuantity();
        response.addDate = item.getAddDate();
        response.expiringDate = item.getExpiringDate();
        response.deleted = item.getDelete();
        return response;
    }
}
