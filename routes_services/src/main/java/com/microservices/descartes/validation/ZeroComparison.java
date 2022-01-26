package com.microservices.descartes.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ZeroComparison implements ConstraintValidator<NonZeroValues, Double> {



    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        boolean valid = true;

        if (value != null){
            if(value == 0){
                valid = false;
                return valid;
            }

            return valid;
        }

        return valid;

    }
}
