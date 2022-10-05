package com.sqs.example.demo.service.model;

import lombok.Data;


@Data
public class ChangeNotification {
    String id;
    String sentMessage;
    String receivedMessage;
}
