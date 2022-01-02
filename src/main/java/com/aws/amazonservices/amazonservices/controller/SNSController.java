package com.aws.amazonservices.amazonservices.controller;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SNSController {

    @Autowired
    private AmazonSNSClient amazonSNSClient;


    @Value("${cloud.aws.sns.topic.arn}")
    private String snsTopicARN;


    @GetMapping("/subscribe/{email}")
    public String subscribe(@PathVariable String email){
        SubscribeRequest request = new SubscribeRequest(snsTopicARN,"email",email);
        amazonSNSClient.subscribe(request);
        return "Please check your mail";
    }

    @GetMapping("/publish/{msg}")
    public String publish(@PathVariable String msg){
        PublishRequest request = new PublishRequest(snsTopicARN,msg,"SNS Subject");
        amazonSNSClient.publish(request);
        return "Message published";
    }

}
