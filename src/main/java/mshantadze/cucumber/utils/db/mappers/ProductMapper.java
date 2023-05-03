package mshantadze.cucumber.utils.db.mappers;

import mshantadze.cucumber.utils.db.models.Product;
import mshantadze.cucumber.utils.db.models.User;

import java.util.List;

public interface ProductMapper {
    List<Product> getUserProducts(User user);
}
