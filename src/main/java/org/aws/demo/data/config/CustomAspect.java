package org.aws.demo.data.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aws.demo.data.model.PersonalInfo;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Component
public class CustomAspect {

    @Before("execution(* org.aws.demo.web.StateMachineController.*(..)) and args(personalInfo)")
    public void assignIdToPersonalInfoInstance(JoinPoint joinPoint, PersonalInfo personalInfo) {
        personalInfo.setId(UUID.randomUUID().toString());
    }
}
