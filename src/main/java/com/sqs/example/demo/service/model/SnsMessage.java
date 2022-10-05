package com.sqs.example.demo.service.model;

import lombok.Data;

@Data
public class SnsMessage {
    private String Type;
    private String MessageId;
    private String TopicArn;
    private String Message;
}
