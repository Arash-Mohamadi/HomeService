package service.users;

import entity.BaseEntity;
import entity.enums.UserType;

import java.io.Serializable;

public interface BaseUsersServices {
    void editPassword(Long userId, String password);

}
