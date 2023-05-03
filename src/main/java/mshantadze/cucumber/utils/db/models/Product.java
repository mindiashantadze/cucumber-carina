package mshantadze.cucumber.utils.db.models;

public class Product {
    private long id;
    private String title;
    private String description;
    private String price;

    public Product() {
        this.id = -1;
        this.title = "";
        this.description = "";
        this.price = "";
    }

    public Product(long id, String title, String description, String price) {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
