package com.sqs.example.demo.service;

import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class SqsProducerService {

    private final AmazonSQSClient sqsClient;

    @Autowired
    public SqsProducerService(AmazonSQSClient sqsClient) {
        this.sqsClient = sqsClient;
    }

    public void sendMessageToSqs(String sqsUrl, String body, String queueName) {
        try {
            log.info("Publicando mensagem no SQS {}: {}", queueName, body);
            SendMessageRequest sendMessageRequest = new SendMessageRequest();
            sendMessageRequest.setQueueUrl(sqsUrl);
            sendMessageRequest.setMessageBody(body);

            this.sqsClient.sendMessage(sendMessageRequest);
            log.info("Publicação realizada com sucesso no SQS {}.", queueName);


        } catch (Exception e) {
            log.info("Erro Não foi possivel enviar a mensagem para o SQS {}", queueName);
        }


    }
}
