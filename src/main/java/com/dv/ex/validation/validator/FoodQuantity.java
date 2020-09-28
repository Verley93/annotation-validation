package com.dv.ex.validation.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Max;
import javax.validation.constraints.PositiveOrZero;
import java.lang.annotation.*;

@Documented
@PositiveOrZero(message = "Quantity must be positive")
@Max(value = 25, message = "Quantity must not exceed 25")
@Constraint(validatedBy = {})
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface FoodQuantity {

    String message() default "Invalid quantity";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
