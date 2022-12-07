package repository.capability.services.impl;


import entity.capability.Services;
import jakarta.persistence.EntityManager;
import repository.BaseRepositoryImpl;
import repository.capability.services.ServicesRepository;



public class ServicesRepositoryImpl extends BaseRepositoryImpl<Services,Long> implements ServicesRepository {

    @Override
    public Class<Services> getEntityClass() {
        return Services.class;
    }

    @Override
    public void editName(EntityManager entityManager ,String name, Long servicesId) {
        String jpql ="update Services s set s.name=:name where s.id=:servicesId";
        entityManager.createQuery(jpql,Services.class)
                .setParameter("name",name)
                .setParameter("servicesId",servicesId)
                .executeUpdate();
    }
}
