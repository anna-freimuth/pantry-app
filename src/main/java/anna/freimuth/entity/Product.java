package anna.freimuth.entity;


import java.time.LocalDate;


public class Product {

    private String name;
    private LocalDate expiringDay;

    public Product(String name, LocalDate expiringDay) {
        this.name = name;

        this.expiringDay = expiringDay;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public LocalDate getExpiringDay() {
        return expiringDay;
    }

    public void setExpiringDay(LocalDate expiringDay) {
        this.expiringDay = expiringDay;
    }

}
