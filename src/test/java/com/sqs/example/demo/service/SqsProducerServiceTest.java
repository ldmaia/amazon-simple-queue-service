package com.sqs.example.demo.service;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import com.amazonaws.services.sqs.AmazonSQSClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SqsProducerServiceTest {

    @Mock
    private AmazonSQSClient amazonSQS;

    @Mock
    private Environment environment;

    @Mock
    private Appender<ILoggingEvent> mockAppender;

    private SqsProducerService sqsProducerService;



    @BeforeEach
    void setUp() {

        this.sqsProducerService = new SqsProducerService(this.amazonSQS);

        final Logger logger = LoggerFactory
                .getLogger(Logger.ROOT_LOGGER_NAME);
        ((ch.qos.logback.classic.Logger) logger).addAppender(this.mockAppender);

    }

    @Test
    void sendMessageToSqs() {

        String sqsUrl = "www.sqs.com.br";
        String body = "Mensagem para a fila SQS";
        String queueName = "Fila SQS";
        assertDoesNotThrow(() -> this.sqsProducerService.sendMessageToSqs(sqsUrl,body,queueName));

    }
}