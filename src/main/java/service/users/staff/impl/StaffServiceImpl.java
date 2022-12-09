package service.users.staff.impl;


import entity.capability.Credit;
import entity.capability.Services;
import entity.capability.SubServices;
import entity.enums.SpecialistStatus;
import entity.enums.StaffStatus;
import entity.users.Staff;
import entity.users.Specialist;
import exception.CustomizedInvalidStatus;
import exception.CustomizedNotFoundException;
import jakarta.persistence.EntityManager;
import repository.capability.credit.CreditRepository;
import repository.capability.services.ServicesRepository;
import repository.capability.subservices.SubServicesRepository;
import repository.users.Staff.StaffRepository;
import repository.users.Specialist.SpecialistRepository;
import service.users.staff.StaffService;
import util.EntityManagerFactoryProvider;
import validation.Validation;

import java.util.List;


public class StaffServiceImpl implements StaffService {

    private ServicesRepository servicesRepository;
    private SubServicesRepository subServicesRepository;
    private SpecialistRepository specialistRepository;
    private StaffRepository adminRepository;

    private CreditRepository creditRepository;

    @Override
    public List<Services> showAllServices() {
        EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        List<Services> list = null;
        try {
            list = servicesRepository.findAll(entityManager);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void addServices(Services services) {
        EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        try {
            Validation.checkEntity(services); // exception handler for duplicate name .
            servicesRepository.save(entityManager, services);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void removeServicesById(Long id) {
        EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        try {
            servicesRepository.delete(entityManager, id);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void addSubServicesToServices(Long servicesId, SubServices subServices) {
        EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Services services = null;
        try {
            services = servicesRepository.findById(entityManager, servicesId);
            if (services != null) {
                Validation.checkEntity(subServices); // exception handler for duplicate name .
                services.addSubServices(subServices);
                subServicesRepository.save(entityManager, subServices);
                servicesRepository.update(entityManager, services);
            } else {
                System.out.println("services is not found");
//                throw new CustomizedNotFoundException(" services is not found");
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
        }

    }

    @Override
    public void removeSubServicesOfServices(Long servicesId, Long subServicesId) {
        EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        SubServices subServices = null;
        Services services = null;
        try {
            services = servicesRepository.findById(entityManager, servicesId);

            if (services != null) {
                subServices = subServicesRepository.findById(entityManager, subServicesId);
                if (subServices != null) {
                    subServicesRepository.removeOfServices(entityManager, servicesId, subServicesId);
                } else {
                    System.out.println(" subServices is not found");
                    //  throw new CustomizedNotFoundException(" subServices is not found");
                }
            } else {
                System.out.println(" Services is not found");
                // throw new CustomizedNotFoundException(" Services is not found");
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void editServices(String name, Long serviceId) {
        EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Services services = null;
        try {
            services = servicesRepository.findById(entityManager, serviceId);
            if (services != null) {
                services.setName(name);
                Validation.checkEntity(services);
                servicesRepository.save(entityManager, services);

            } else {
                System.out.println(" Services is not found");
                //  throw new CustomizedNotFoundException(" Services is not found");
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void editSubServices(Long id, SubServices subServices) {
        EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        SubServices subServiceFound = null;
        try {
            subServiceFound = subServicesRepository.findById(entityManager, id);
            if (subServiceFound != null) {
                subServiceFound.setName(subServices.getName());
                subServiceFound.setBasePrice(subServices.getBasePrice());
                Validation.checkEntity(subServiceFound);
                subServicesRepository.save(entityManager, subServiceFound);

            } else {
                System.out.println(" subServices is not found");
                //  throw new CustomizedNotFoundException(" subServices is not found");
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
        }

    }

    @Override
    public void addSpecialistToSubServices(Long subServicesId, Long specialistId) {
        EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        SubServices subServiceFound = null;
        Specialist specialistFound = null;
        try {
            subServiceFound = subServicesRepository.findById(entityManager, subServicesId);
            if (subServiceFound != null) {
                specialistFound = specialistRepository.findById(entityManager, specialistId);
                if (specialistFound.getStatus() == SpecialistStatus.CONFIRMED) {
                    subServiceFound.addSpecialist(specialistFound);
                    specialistRepository.update(entityManager, specialistFound);
                    subServicesRepository.update(entityManager, subServiceFound);
                    // can to update
                    entityManager.getTransaction().commit();
                } else {
                    System.out.println(" status of specialist is not allowed");
                    //  throw new CustomizedInvalidStatus(" status of specialist is not allowed");
                }
            } else {
                System.out.println(" subServices is not found");
                // throw new CustomizedNotFoundException(" subServices is not found");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void removeSpecialistOfSubServices(Long subServicesId, Long specialistId) {
        EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        SubServices subServiceFound = null;
        Specialist specialistFound = null;
        try {
            subServiceFound = subServicesRepository.findById(entityManager, subServicesId);
            if (subServiceFound != null) {
                specialistFound = specialistRepository.findById(entityManager, specialistId);
                if (specialistFound != null) {
                    // specialistRepository.removeOfSubServices(entityManager, specialistId, subServicesId);
                    specialistFound.getSubServicesSet().remove(subServiceFound);
                    specialistRepository.update(entityManager, specialistFound);
                } else {
                    System.out.println(" specialist not found");
                    //  throw new CustomizedNotFoundException(" specialist not found");
                }
            } else {
                System.out.println(" subServices is not found");
                //   throw new CustomizedNotFoundException(" subServices is not found");
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void removeSpecialistOfServices(Long servicesId, Long specialistId) {
        EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Services serviceFound = null;
        Specialist specialistFound = null;
        try {
            serviceFound = servicesRepository.findById(entityManager, servicesId);
            if (serviceFound != null) {
                specialistFound = specialistRepository.findById(entityManager, specialistId);
                if (specialistFound != null) {
                    // specialistRepository.removeOfSubServices(entityManager, specialistId, subServicesId);
                    specialistFound.getSubServicesSet().clear();
                    specialistFound.getServicesSet().remove(serviceFound);
                    specialistRepository.update(entityManager, specialistFound);
                } else {
                    System.out.println(" specialist not found");
                    //  throw new CustomizedNotFoundException(" specialist not found");
                }
            } else {
                System.out.println(" subServices is not found");
                //   throw new CustomizedNotFoundException(" subServices is not found");
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void confirmSpecialist(Long specialistId, StaffStatus status) {
        if (status == StaffStatus.MANAGER) {

            EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
            entityManager.getTransaction().begin();
            Specialist specialistFound = null;
            try {
                specialistFound = specialistRepository.findById(entityManager, specialistId);
                if (specialistFound != null) {
                    specialistFound.setStatus(SpecialistStatus.CONFIRMED);
                    Credit credit = new Credit();
                    specialistFound.setCredit(credit);
                    creditRepository.save(entityManager, credit);
                    specialistRepository.update(entityManager, specialistFound);
                } else {
                    System.out.println(" specialist not found");
                    //    throw new CustomizedNotFoundException(" specialist not found");
                }
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                entityManager.getTransaction().rollback();
            }

        } else {
            System.out.println("only manager is allowed");
            //    throw new CustomizedInvalidStatus("only manager is allowed");
        }
    }


    @Override
    public void editPassword(Long userId, String password) {
        EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Staff admin = null;

        try {
            admin = adminRepository.findById(entityManager, userId);
            if (admin != null) {
                admin.setPassword(password);
                adminRepository.update(entityManager, admin);
            } else {
                System.out.println(" admin  not found");
                //  throw new CustomizedNotFoundException(" admin  not found");
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
        }
    }

}


