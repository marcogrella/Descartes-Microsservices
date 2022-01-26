package com.microservices.descartes.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = { ZeroComparison.class})
public @interface NonZeroValues {

    String message() default "This field not accepts zero values";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };


}
