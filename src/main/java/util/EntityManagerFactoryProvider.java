package util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;


public class EntityManagerFactoryProvider {
    private static EntityManagerFactory entityManagerFactory;
    private static  ValidatorFactory validatorFactory;
    static {

        entityManagerFactory = Persistence.createEntityManagerFactory("unit");
        validatorFactory = Validation.buildDefaultValidatorFactory();
    }

    public static EntityManagerFactory getEntityManagerFactory() {

        return entityManagerFactory;
    }

    public static Validator getValidator() {
        return validatorFactory.getValidator();
    }
}
