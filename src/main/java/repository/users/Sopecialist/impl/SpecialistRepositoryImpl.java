package repository.users.Sopecialist.impl;




import entity.users.Specialist;
import jakarta.persistence.EntityManager;
import repository.BaseRepositoryImpl;
import repository.users.Sopecialist.SpecialistRepository;




public class SpecialistRepositoryImpl extends BaseRepositoryImpl<Specialist,Long> implements SpecialistRepository {

    @Override
    public Class<Specialist> getEntityClass() {
        return Specialist.class;
    }

    @Override
    public void removeOfSubServices(EntityManager entityManager, Long SpecialistId, Long SubServicesId) {
        String sql = "delete from specialist_subservices where specialist_id=:SpecialistId and subservice_id=:SubServicesId";
        entityManager.createNativeQuery(sql)
                .setParameter("SpecialistId",SpecialistId)
                .setParameter("SubServicesId",SubServicesId)
                .executeUpdate();
    }

    @Override
    public void removeOfServices(EntityManager entityManager, Long SpecialistId, Long ServicesId) {
        String sql = "";
    }


}
