package com.aws.amazonservices.amazonservices.controller;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import lombok.extern.flogger.Flogger;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SQSController {

    @Autowired
    private QueueMessagingTemplate sqsAsyncClient;

    @Value("${cloud.aws.sqs.endpoint.uri}")
    private String sqsEndpoint;

    @GetMapping("/send/{message}")
    public void sendMessage(@PathVariable String message){
        sqsAsyncClient.send(sqsEndpoint, MessageBuilder.withPayload(message).build());
    }

    @SqsListener(value="gobi-demo-queue")
    public void receivemessage(String message){
        log.info("Message received {} ",message);
    }
}
