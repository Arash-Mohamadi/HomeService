package repository.users.Sopecialist;


import entity.users.Specialist;
import jakarta.persistence.EntityManager;
import repository.BaseRepository;


public interface SpecialistRepository extends BaseRepository<Specialist,Long> {

    void removeOfSubServices(EntityManager entityManager ,Long SpecialistId,Long SubServicesId);
    void removeOfServices(EntityManager entityManager ,Long SpecialistId,Long ServicesId);

}
