package org.aws.demo.utility;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.util.UUID;

public class IdGenrationConverter implements DynamoDBTypeConverter<String, String> {

    @Override
    public String convert(String input) {
        return "open:api:" +  UUID.randomUUID();
    }

    @Override
    public String unconvert(String input) {
        return input.replaceFirst("open:api:", "");
    }
}
