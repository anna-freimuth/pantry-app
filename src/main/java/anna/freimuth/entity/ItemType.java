package anna.freimuth.entity;

import anna.freimuth.service.requests.CreateItemTypeRequest;

import javax.persistence.*;

@Entity
@Table(name = "item_types", schema = "pantry")
public class ItemType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String typeName;

    private Boolean deleted = false;

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public static ItemType fromRequest(CreateItemTypeRequest request) {

        ItemType itemType = new ItemType();
        itemType.typeName = request.typeName.strip();
        itemType.id = 0;

        return itemType;
    }
}
