package anna.freimuth.controller;

import anna.freimuth.entity.Product;
import anna.freimuth.entity.ProductMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ProductController {
    private final ProductMap products;

    public ProductController(ProductMap products) {
        this.products = products;
    }

    @PostMapping("/add-product")
    public String createProduct(@ModelAttribute Product product) {
        products.add(product);
        return "test";
    }
}
