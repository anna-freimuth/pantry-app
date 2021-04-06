package anna.freimuth.entity;

import anna.freimuth.service.PantryDeSerialization;
import anna.freimuth.service.PantrySerialization;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Component
public class ProductMap {

    HashMap<String, LocalDate> products;
    PantrySerialization serialization;

    public ProductMap(PantryDeSerialization storage, PantrySerialization serialization) {
        this.serialization = serialization;
        this.products = storage.simplePantryDeSerialization();
    }

    public Map<String, LocalDate> getProducts() {
        return products;
    }


    public Map add(Product product) {
        products.put(product.getName(), product.getExpiringDay());
        save();
        return products;
    }


    public Map remove(Product product) {
        if (products.isEmpty())
            return new HashMap();

        if (products.containsKey(product.getName()))
            products.remove(product.getName());
        save();
        return products;
    }

    private void save() {
        serialization.pantrySerialization(products);
    }
}
