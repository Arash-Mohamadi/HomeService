package service.users.customer.impl;

import entity.capability.Credit;
import entity.capability.Opinion;
import entity.capability.Order;
import entity.capability.SubServices;
import entity.enums.OrderStatus;
import entity.enums.UserType;
import entity.users.Customer;
import entity.users.Specialist;
import exception.CustomizedNotFoundException;
import jakarta.persistence.EntityManager;
import repository.capability.credit.CreditRepository;
import repository.capability.opinion.OpinionRepository;
import repository.capability.order.OrderRepository;
import repository.capability.subservices.SubServicesRepository;
import repository.users.Customer.CustomerRepository;
import repository.users.Specialist.SpecialistRepository;
import service.users.customer.CustomerService;
import util.EntityManagerFactoryProvider;
import validation.Validation;

public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private CreditRepository creditRepository;
    private SubServicesRepository subServicesRepository;
    private OrderRepository orderRepository;
    private OpinionRepository opinionRepository;
    private SpecialistRepository specialistRepository;

    @Override
    public void editPassword(Long userId, String password) {
        EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Customer customer = null;

        try {
            customer = customerRepository.findById(entityManager, userId);
            if (customer != null) {
                customer.setPassword(password);
                customerRepository.update(entityManager, customer);
            } else {
                System.out.println(" customer  not found");
                //  throw new CustomizedNotFoundException(" customer  not found");
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
            creditRepository.save(entityManager, credit); //cascade
            customerRepository.save(entityManager, customer);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void registerOpinion(Opinion opinion, Long orderId) {
        EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        SubServices subServices = null;
        Order order = null;

        try {
            order = orderRepository.findById(entityManager, orderId);
            if (order.getStatus() == OrderStatus.PAID) {
                order.setOrderWithOpinion(opinion);
                opinion.setSpecialist(order.getSpecialist());
                order.getSpecialist().setScoreAvg(opinion.getScore());
                orderRepository.update(entityManager, order);
                opinionRepository.save(entityManager, opinion);
                specialistRepository.update(entityManager, opinion.getSpecialist());
                entityManager.getTransaction().commit();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void registerOrder(Order order, Long subServiceId) {
        EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        // customer login done , create order -> save to DB
        SubServices subServices = null;

        try {
            subServices = subServicesRepository.findById(entityManager, subServiceId);
            if (order.getPrice() >= subServices.getBasePrice()) {
                subServices.addOrder(order);
                order.setStatus(OrderStatus.PENDING_FOR_SPECIALIST_SUGGESTION);
                orderRepository.save(entityManager, order);
                entityManager.getTransaction().commit();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void payment(Long orderId, double money) {
        EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Order order = null;

        try {
            order = orderRepository.findById(entityManager, orderId);
            Customer customer = order.getCustomer();
            Specialist specialist = order.getSpecialist();
            double newBalanceCustomer = customer.getCredit().getBalance() - money;
            double newBalanceSpecialist = specialist.getCredit().getBalance() + money;
            customer.getCredit().setBalance(newBalanceCustomer);
            specialist.getCredit().setBalance(newBalanceSpecialist);
            order.setStatus(OrderStatus.PAID);
            orderRepository.update(entityManager,order);
            specialistRepository.update(entityManager,specialist);
            customerRepository.update(entityManager,customer);
            // transaction handler
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
        }
    }


}
