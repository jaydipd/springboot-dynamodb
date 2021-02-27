package org.aws.demo.stepfunction;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import org.apache.http.client.CredentialsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class CustomCredentials implements AWSCredentialsProvider {

    @Autowired
    private Credentials credentials;

    @Override
    public AWSCredentials getCredentials() {
        return credentials;
    }

    @Override
    public void refresh() {

    }
}

@Component
class Credentials implements AWSCredentials {
    @Value("${amazon.aws.accesskey}")
    private String key;

    @Value("${amazon.aws.secretkey}")
    private String secret;

    @Override
    public String getAWSAccessKeyId() {
        return key;
    }

    @Override
    public String getAWSSecretKey() {
        return secret;
    }
}
