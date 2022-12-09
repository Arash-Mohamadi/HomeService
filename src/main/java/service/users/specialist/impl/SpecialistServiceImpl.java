package service.users.specialist.impl;

import entity.capability.Order;
import entity.capability.Suggestion;
import entity.enums.OrderStatus;
import entity.enums.SpecialistStatus;
import entity.enums.UserType;
import entity.users.Specialist;
import exception.CustomizedFormatOrSizeFile;
import jakarta.persistence.EntityManager;
import repository.capability.order.OrderRepository;
import repository.capability.suggestion.SuggestionRepository;
import repository.users.Specialist.SpecialistRepository;
import service.users.specialist.SpecialistService;
import util.EntityManagerFactoryProvider;
import util.OnlyExt;
import validation.Validation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SpecialistServiceImpl implements SpecialistService {
    private SpecialistRepository specialistRepository;
    private OrderRepository orderRepository;
    private SuggestionRepository suggestionRepository;

    @Override
    public void editPassword(Long userId, String password) {
        EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Specialist specialist = null;

        try {
            specialist = specialistRepository.findById(entityManager, userId);
            if (specialist != null) {
                if (specialist.getStatus() == SpecialistStatus.CONFIRMED) {
                    specialist.setPassword(password);
                    specialistRepository.update(entityManager, specialist);
                } else {
                    System.out.println("specialist not allowed for edit password");
                    //  throw new CustomizedInvalidStatus("specialist not allowed for edit password");
                }
            } else {
                System.out.println(" specialist  not found");
                //  throw new CustomizedNotFoundException(" specialist  not found");
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
            byte[] bytes = checkFile(file); //exception code

                specialist.setPhoto(bytes);
                // save image
                specialistRepository.save(entityManager, specialist);
                entityManager.getTransaction().commit();


        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
        }

    }

    @Override
    // specialist login done -> find specialist
    public void sendSuggestion(Suggestion suggestion, Long orderId, Specialist specialist) {
        EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Order order = null;

        if (specialist.getStatus() == SpecialistStatus.CONFIRMED) {
            try {
                order = orderRepository.findById(entityManager, orderId);
                if (order != null && (order.getStatus() == OrderStatus.PENDING_FOR_SPECIALIST_SUGGESTION
                        || order.getStatus() == OrderStatus.PENDING_FOR_SPECIALIST_SELECTION)) {
                    order.addSuggestion(suggestion);
                    order.setStatus(OrderStatus.PENDING_FOR_SPECIALIST_SELECTION);
                    orderRepository.update(entityManager, order);
                    specialistRepository.update(entityManager, specialist);
                    entityManager.getTransaction().commit();
                } else
                    System.out.println(" end time for take order");
                // throw new
            } catch (Exception e) {
                System.out.println(e.getMessage());
                entityManager.getTransaction().rollback();
            }
        }
    }


    private byte[] checkFile(File file) throws IOException {

        byte[] bFile = null;
        OnlyExt format = new OnlyExt("jpg");
      //  file.getName().endsWith(".jpg");
        if (format.accept(file, file.getName()) && file.length() / 1024 <= 300) {
            bFile = new byte[(int) file.length()];

                FileInputStream fileInputStream = new FileInputStream(file);
                //convert file into array of bytes
                fileInputStream.read(bFile);
                fileInputStream.close();
        } else
           throw new CustomizedFormatOrSizeFile("format or size of file not allowed");

        return bFile;
    }

}
