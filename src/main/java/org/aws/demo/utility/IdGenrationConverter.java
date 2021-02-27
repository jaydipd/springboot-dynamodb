package org.aws.demo.utility;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.util.UUID;

public class IdGenrationConverter implements DynamoDBTypeConverter<String, String> {

    @Override
    public String convert(String input) {
        String str = "open:api:" + input;
        System.out.println("convert: " + str);
        return str;
    }

    @Override
    public String unconvert(String input) {
        System.out.println("unconvert: " + input);
        String str = input.replaceFirst("open:api:", "");
        System.out.println("after unconvert: " + str);
        return str;
    }
}
