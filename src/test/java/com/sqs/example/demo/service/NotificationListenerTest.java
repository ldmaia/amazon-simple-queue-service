package com.sqs.example.demo.service;

import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.sqs.example.demo.service.model.ChangeNotification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class NotificationListenerTest {

    @Mock
    private AmazonSQSClient amazonSQS;

    @Mock
    private ReceiveMessageRequest receiveMessageRequest;

    private SqsProducerService sqsProducerService;

    private NotificationListener notificationListener;

    @BeforeEach
    void setUp() {

        this.sqsProducerService = new SqsProducerService(this.amazonSQS);
        this.notificationListener = new NotificationListener(this.amazonSQS);

    }

    @Test
    void receiveMessage() {



        //ChangeNotification notification = new ChangeNotification();


        ReceiveMessageRequest messageRequest = new ReceiveMessageRequest();
        messageRequest.setQueueUrl("fila");
        messageRequest.setAttributeNames(List.of("nome dos atributos"));
        messageRequest.setMessageAttributeNames(List.of("mensagens da fila"));

        String sqsUrl = "www.sqs.com.br";
        String body = "Mensagem para a fila SQS";
        String queueName = "fila";
        sqsProducerService.sendMessageToSqs(sqsUrl,body,queueName);

        notificationListener.receiveMessage(messageRequest);

        //ReceiveMessageResult result = amazonSQS.receiveMessage("fila");

        //System.out.println(result);




        //amazonSQS.receiveMessage(messageRequest).getMessages().get(0);


        //when(this.notificationListener.receiveMessage(notification)).thenReturn()


       // assertDoesNotThrow(() -> this.notificationListener.receiveMessage(messageRequest));
    }
}