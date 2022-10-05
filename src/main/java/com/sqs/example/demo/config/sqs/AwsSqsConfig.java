package com.sqs.example.demo.config.sqs;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static com.amazonaws.regions.Regions.SA_EAST_1;

@Configuration
public class AwsSqsConfig {

    @Bean
    @Profile({"aws"})
    public AmazonSQSClient sqsClient() {
        var region = SA_EAST_1;
        return (AmazonSQSClient) AmazonSQSClient.builder()
                .withRegion(region)
                .build();
    }

    @Bean
    @Profile({"local"})
    public AmazonSQSClient sqsClientLocal() {
        var region = SA_EAST_1;
        AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration("fakeurl", SA_EAST_1.getName());
        return (AmazonSQSClient) AmazonSQSClient.builder()
                .withRegion(region)
                .withEndpointConfiguration(endpointConfiguration)
                .build();
    }

}
