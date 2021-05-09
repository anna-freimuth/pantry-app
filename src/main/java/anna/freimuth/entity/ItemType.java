package anna.freimuth.entity;

import anna.freimuth.service.requests.CreateProductRequest;

import javax.persistence.*;

@Entity
@Table(name="item_types", schema = "pantry")
public class ItemType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    private String typeName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String type_name) {
        this.typeName = typeName;
    }

    public static ItemType fromRequest(CreateProductRequest request) {

        ItemType itemType = new ItemType();

        itemType.typeName = request.name.strip();
        itemType.id = 0;

        return itemType;
    }
}
