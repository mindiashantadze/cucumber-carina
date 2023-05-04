package mshantadze.cucumber.utils.db.mappers;

import mshantadze.cucumber.utils.db.models.User;

public interface UserMapper {
    User getUser(String username);
}
