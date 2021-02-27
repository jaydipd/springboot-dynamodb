package org.aws.demo.web;

import com.amazonaws.services.stepfunctions.AWSStepFunctions;
import com.amazonaws.services.stepfunctions.model.StartExecutionRequest;
import com.amazonaws.services.stepfunctions.model.StartExecutionResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aws.demo.data.model.PersonalInfo;
import org.aws.demo.stepfunction.CustomCredentials;
import org.aws.demo.stepfunction.RequestDTO;
import org.aws.demo.utility.StringDataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class StateMachineController {

    @Value("${mystatemachine}")
    private String ACTIVE_STATE_MACHINE_ARN;

    @Value("${boot-step-dynamo}")
    private String BOOT_STEP_DYNAMO;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomCredentials customCred;

    @Autowired
    private AWSStepFunctions awsStepFunctions;

    @Value("${amazon.aws.region}")
    private String region;

    @Value("${account.id}")
    private String accountId;

    @PostMapping("/trytotriggerstepfunction")
    public String triggerStepFunction(@RequestBody RequestDTO requestDTO) throws Exception {
        String input = objectMapper.writeValueAsString(requestDTO);
        triggerStepFunction(input,"MyStateMachine",ACTIVE_STATE_MACHINE_ARN);
        return "Successfully triggered MyStateMachine!!";
    }

    @PostMapping("/personalInfo")
    public String storePersonalInfoInDynamoDBBasedOnInput(@RequestBody PersonalInfo personalInfo) throws Exception {
        String personalInfoInstance =  objectMapper.writeValueAsString(personalInfo);
        triggerStepFunction(personalInfoInstance,"boot-step-dynamo",BOOT_STEP_DYNAMO);
        return "Successfully triggered boot-step-dynamo!!";

    }

    public void triggerStepFunction(String input,String prefix,String arn){
        StartExecutionResult result = awsStepFunctions
                .startExecution(new StartExecutionRequest().withInput(input).withName(prefix + UUID.randomUUID())
                        .withStateMachineArn(arn));

        System.out.println(result.getStartDate());
        System.out.println(result.getExecutionArn());
    }

    private String buildArnToCreateStateMachine(String machineName) {
        return new StringBuilder()
                .append(StringDataUtils.ARN)
                .append(StringDataUtils.seperator)
                .append(StringDataUtils.PLATFORM).append(StringDataUtils.seperator)
                .append(StringDataUtils.STATES).append(StringDataUtils.seperator).append(region)
                .append(StringDataUtils.seperator).append(accountId).append(StringDataUtils.seperator)
                .append(StringDataUtils.SERVICE_TYPE).append(StringDataUtils.seperator).append(machineName)
                .toString();
    }
}