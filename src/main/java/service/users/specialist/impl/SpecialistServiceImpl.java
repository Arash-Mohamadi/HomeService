package service.users.specialist.impl;

import entity.capability.Credit;
import entity.capability.SubServices;
import entity.enums.SpecialistStatus;
import entity.enums.UserType;
import entity.users.Specialist;
import exception.CustomizedInvalidStatus;
import exception.CustomizedNotFoundException;
import jakarta.persistence.EntityManager;
import repository.BaseRepository;
import repository.users.Sopecialist.SpecialistRepository;
import service.users.specialist.SpecialistService;
import util.EntityManagerFactoryProvider;
import validation.Validation;

import java.io.File;

public class SpecialistServiceImpl implements SpecialistService {
    private SpecialistRepository specialistRepository;
    @Override
    public void editPassword(Long userId, String password) {
        EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Specialist specialist = null;

        try {
            specialist = specialistRepository.findById(entityManager,userId);
            if (specialist != null){
                if (specialist.getStatus()==SpecialistStatus.CONFIRMED){
                    specialist.setPassword(password);
                    specialistRepository.update(entityManager,specialist);
                }else {
                    throw new CustomizedInvalidStatus("specialist not allowed for edit password");
                }
            } else {
                throw new CustomizedNotFoundException(" specialist  not found");
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
        }

    }

    @Override
    public void signup(Specialist specialist, File file) {
        EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        try {
            Validation.checkEntity(specialist);
            specialist.setUserType(UserType.SPECIALIST);
            specialist.setStatus(SpecialistStatus.PENDING_CONFIRMATION);
            // save image
            specialistRepository.save(entityManager,specialist);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
        }

    }




}
