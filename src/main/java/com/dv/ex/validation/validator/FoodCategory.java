package com.dv.ex.validation.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

@Documented
@NotNull(message = "Category must be present")
@NotEmpty(message = "Category must not be empty")
@Constraint(validatedBy = FoodCategoryValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface FoodCategory {

    String message() default "Invalid category";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] allowed() default {"dairy", "grain"};

}
