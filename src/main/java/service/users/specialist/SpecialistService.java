package service.users.specialist;

import entity.capability.Suggestion;
import entity.users.Specialist;
import service.users.BaseUsersServices;

import java.io.File;

public interface SpecialistService extends BaseUsersServices  {
    void signup(Specialist specialist,File file);
    void sendSuggestion(Suggestion suggestion, Long orderId,Specialist specialist);





}
