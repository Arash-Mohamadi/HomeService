package validation;


import entity.BaseEntity;
import exception.CustomizedIllegalArgumentException;
import jakarta.validation.ConstraintViolation;
import util.EntityManagerFactoryProvider;

import java.util.Set;

public  class Validation {

    public static void checkEntity(BaseEntity<Long> entity) {
        Set<ConstraintViolation<BaseEntity<Long>>> constraintViolations = EntityManagerFactoryProvider
                .getValidator()
                .validate(entity);
        if (!constraintViolations.isEmpty())
            throw new CustomizedIllegalArgumentException(constraintViolations.toString());
    }
}
