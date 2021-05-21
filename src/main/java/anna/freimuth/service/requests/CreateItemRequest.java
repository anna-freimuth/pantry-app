package anna.freimuth.service.requests;

import java.time.LocalDate;

public class CreateItemRequest {

    public LocalDate expiringDate;
    public long quantity;
    public long itemTypeId;
}
