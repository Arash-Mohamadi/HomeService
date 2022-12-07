package repository.capability.services;

import entity.capability.Services;
import jakarta.persistence.EntityManager;
import repository.BaseRepository;


public interface ServicesRepository extends BaseRepository<Services,Long> {
    void editName(EntityManager entityManager ,String name, Long servicesId);

}
