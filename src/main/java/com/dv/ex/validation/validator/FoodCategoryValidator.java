package com.dv.ex.validation.validator;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class FoodCategoryValidator
        implements ConstraintValidator<FoodCategory, String> {

    List<String> allowed;

    @Override
    public void initialize(FoodCategory constraintAnnotation) {
        this.allowed = Arrays.asList(constraintAnnotation.allowed());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        log.info("isValid: value=[{}]", value);
        if (!allowed.contains(value.toLowerCase())) {
            String err = "Category must be one of the following: " + allowed;
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(err)
                    .addConstraintViolation();
            return false;
        }
        return true;
    }

}
