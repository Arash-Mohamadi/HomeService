package service.users.customer.impl;

import entity.capability.Credit;
import entity.enums.UserType;
import entity.users.Customer;
import exception.CustomizedNotFoundException;
import jakarta.persistence.EntityManager;
import repository.capability.credit.CreditRepository;
import repository.users.Customer.CustomerRepository;
import service.users.customer.CustomerService;
import util.EntityManagerFactoryProvider;
import validation.Validation;

public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private CreditRepository creditRepository;
    @Override
    public void editPassword(Long userId, String password) {
        EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Customer customer = null;

        try {
            customer = customerRepository.findById(entityManager,userId);
            if (customer != null){
                customer.setPassword(password);
                customerRepository.update(entityManager,customer);
            } else {
                throw new CustomizedNotFoundException(" customer  not found");
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
        }

    }

    @Override
    public void signup(Customer customer) {
        EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        try {
            Validation.checkEntity(customer);
            customer.setUserType(UserType.CUSTOMER);
            Credit credit = new Credit();
            creditRepository.save(entityManager,credit); //cascade
            customerRepository.save(entityManager,customer);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
        }
    }


}
