package anna.freimuth.entity;

import anna.freimuth.service.requests.CreateProductRequest;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name="item", schema = "pantry")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    @OneToOne
    @JoinColumn(name = "type_id")
    private ItemType itemType;

    private long quantity;

    private Date addDate;

    private Date expiringDate;

    private Boolean deleted = false;

    private Boolean getDeleted() {
        return deleted;
    }

    public void setDelete(Boolean deleted) {
        this.deleted = deleted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Date getExpiringDate() {
        return expiringDate;
    }

    public void setExpiringDate(Date expiringDate) {
        this.expiringDate = expiringDate;
    }

    public static Item fromRequest(CreateProductRequest request, ItemType itemType) {

        Item item = new Item();

        item.setId(0L);
        item.setItemType(itemType);
        item.setQuantity(request.quantity);
        item.setAddDate(Date.valueOf(LocalDate.now()));
        item.setExpiringDate(Date.valueOf(request.expiringDate));

        return item;
    }
}
