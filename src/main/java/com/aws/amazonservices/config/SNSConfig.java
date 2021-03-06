package com.aws.amazonservices.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SNSConfig {

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Primary
    @Bean
    public AmazonSNSClient amazonSNSClient(){
        AWSCredentials creds = new BasicAWSCredentials(accessKey,secretKey);
        return (AmazonSNSClient) AmazonSNSClientBuilder.standard().withRegion(region).withCredentials(new AWSStaticCredentialsProvider(creds)).build();
    }
}
