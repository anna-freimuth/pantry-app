package anna.freimuth.service.requests;

import com.sun.istack.NotNull;
import org.openapitools.jackson.nullable.JsonNullable;

import java.time.LocalDate;

public class PatchItemRequest {

    @NotNull
    public Long id;
    public JsonNullable<Long> typeId = JsonNullable.undefined();
    public JsonNullable<Long> quantity = JsonNullable.undefined();
    public JsonNullable<LocalDate> addDate = JsonNullable.undefined();
    public JsonNullable<LocalDate> expiringDate = JsonNullable.undefined();
}
