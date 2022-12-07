package repository.capability.subservices.impl;


import entity.capability.SubServices;
import jakarta.persistence.EntityManager;
import repository.BaseRepositoryImpl;
import repository.capability.subservices.SubServicesRepository;



public class SubServicesRepositoryImpl extends BaseRepositoryImpl<SubServices,Long> implements SubServicesRepository {

    @Override
    public Class<SubServices> getEntityClass() {
        return SubServices.class;
    }

    @Override
    public void removeOfServices(EntityManager entityManager, Long services, Long subServices) {
        String jpql = "delete from SubServices sub where sub.id=:subServicesId and sub.services.id=:servicesId";
        entityManager.createQuery(jpql, SubServices.class)
                .setParameter("subServicesId",subServices)
                .setParameter("servicesId",services)
                .executeUpdate();
    }
}
