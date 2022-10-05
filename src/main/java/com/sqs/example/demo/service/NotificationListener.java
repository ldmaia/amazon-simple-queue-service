package com.sqs.example.demo.service;

import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sqs.example.demo.service.model.ChangeNotification;
import com.sqs.example.demo.service.model.SnsMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class NotificationListener {

    private final AmazonSQSClient sqsClient;

    // Injeta sqs no construtor do servico de notificacao
    @Autowired
    public NotificationListener(AmazonSQSClient sqsClient) {
        this.sqsClient = sqsClient;
    }

    // Metodo para recebimento da mensagem no sqs e transformacao para preparar para mandar a notificacao

    public ChangeNotification receiveMessage(ReceiveMessageRequest receiveMessageRequest) {
        ChangeNotification changeNotification = new ChangeNotification();
        try {
            receiveMessageRequest = new ReceiveMessageRequest();
            receiveMessageRequest.setQueueUrl("fila");
            receiveMessageRequest.setMaxNumberOfMessages(1);

            Message message = sqsClient.receiveMessage(receiveMessageRequest).getMessages().get(0);

            log.info("Recebendo mensagem {}.", message);

            ObjectMapper objectMapper = new ObjectMapper();

            SnsMessage snsMessage = objectMapper.readValue(message.getBody(), SnsMessage.class);
            changeNotification = new ChangeNotification();
            changeNotification.setReceivedMessage(snsMessage.getMessage());

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return changeNotification;
    }
}
