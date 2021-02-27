package org.aws.demo.data.config;


import org.aws.demo.data.dtos.OpenApiDTO;
import org.aws.demo.data.model.OpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Locale;
import java.util.Objects;

@Component
public class OpenApiValidator implements Validator {

    @Autowired
    MessageSource messageSource;


    @Override
    public boolean supports(Class<?> clazz) {
        return OpenApiDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        OpenApiDTO openApiDTO = (OpenApiDTO) target;
        ValidationUtils.rejectIfEmpty(errors, "name",
                messageSource.getMessage("validation.name", null, Locale.getDefault()));

        if (!Objects.nonNull(openApiDTO.getName())) {
            System.out.println(">>>>>>>>>>>>>>");
            errors.rejectValue("name", messageSource.getMessage("validation.name", null, Locale.getDefault()),
                    "name cant be empty!!");
        }
    }
}
