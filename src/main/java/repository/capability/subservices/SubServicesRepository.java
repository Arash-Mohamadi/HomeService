package repository.capability.subservices;

import entity.capability.SubServices;
import jakarta.persistence.EntityManager;
import repository.BaseRepository;



public interface SubServicesRepository extends BaseRepository<SubServices,Long> {

    void removeOfServices(EntityManager entityManager,Long services,Long subServices);


}
