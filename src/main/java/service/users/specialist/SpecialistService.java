package service.users.specialist;

import entity.enums.UserType;
import entity.users.Specialist;
import service.users.BaseUsersServices;

import java.io.File;

public interface SpecialistService extends BaseUsersServices  {
    void signup(Specialist specialist, File file);



}
