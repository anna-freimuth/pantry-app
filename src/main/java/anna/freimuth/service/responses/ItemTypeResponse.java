package anna.freimuth.service.responses;


import anna.freimuth.entity.ItemType;

public class ItemTypeResponse {
    public long id;
    public String typeName;
    public Boolean deleted;

    public static ItemTypeResponse fromItemType(ItemType itemType) {

        ItemTypeResponse response = new ItemTypeResponse();

        response.id = itemType.getId();
        response.typeName = itemType.getTypeName();
        response.deleted = itemType.getDeleted();

        return response;
    }
}
