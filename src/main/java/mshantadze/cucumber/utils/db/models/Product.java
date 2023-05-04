package mshantadze.cucumber.utils.db.models;

import java.math.BigDecimal;

public class Product {
    private long id;
    private String title;
    private String description;
    private Double price;

    public Product() {
    }

    public Product(long id, String title, String description, double price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        Product product = (Product) obj;
        if (product.getId() == this.id && product.getTitle().equals(this.title) && product.getDescription().equals(this.description) && product.getPrice() == this.price) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
