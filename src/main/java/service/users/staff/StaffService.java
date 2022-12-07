package service.users.staff;

import entity.capability.Services;
import entity.capability.SubServices;
import entity.enums.StaffStatus;
import service.users.BaseUsersServices;

import java.util.List;

public interface StaffService extends BaseUsersServices {

    List<Services> showAllServices();
    void addServices(Services services);
    void removeServicesById(Long id);
    void addSubServicesToServices(Long servicesId, SubServices subServices);
    void removeSubServicesOfServices(Long servicesId,Long subServicesId);
    void editServices(String name,Long serviceId);
    void editSubServices(Long id , SubServices subServices);
    void addSpecialistToSubServices(Long subServicesId,Long specialistId);
    void removeSpecialistOfSubServices(Long subServicesId,Long specialistId);
    void removeSpecialistOfServices(Long servicesId,Long specialistId);
    void confirmSpecialist(Long specialistId, StaffStatus status);


}
