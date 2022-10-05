package com.sqs.example.demo.service;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.sqs.example.demo.service.model.ChangeNotification;
import com.sqs.example.demo.service.model.NotificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Value("${sns.topic.arn}")
    private String topicArn;

    @Value("${aws.region}")
    private String awsRegion;

    private final AmazonSNSClient snsClient;

    @Autowired
    public NotificationService(AmazonSNSClient snsClient) {
        this.snsClient = snsClient;
    }

    public ChangeNotification sendNotification(NotificationRequest notificationRequest) {

        ChangeNotification changeNotification = new ChangeNotification();

        changeNotification.setSentMessage(notificationRequest.getMessage());


        try {
            //init();

            PublishRequest request = new PublishRequest();
            request.setMessage(notificationRequest.getMessage());
            request.setTopicArn(topicArn);


            PublishResult result = snsClient.publish(request);
            System.out.println(result.getMessageId() + " Message sent. Status was " + result.getSdkHttpMetadata().getHttpStatusCode());
            changeNotification.setId(result.getMessageId());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return changeNotification;
    }
}
