package com.sqs.example.demo.config.sns;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sns.AmazonSNSClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static com.amazonaws.regions.Regions.SA_EAST_1;

@Configuration
public class AwsSnsConfig {

    @Bean
    @Profile({"aws"})
    public AmazonSNSClient snsClient() {
        var region = SA_EAST_1;
        return (AmazonSNSClient) AmazonSNSClient.builder()
                .withRegion(region)
                .build();
    }

    @Bean
    @Profile({"local"})
    public AmazonSNSClient snsClientLocal() {
        var region = SA_EAST_1;
        AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration("fakeurl", SA_EAST_1.getName());
        return (AmazonSNSClient) AmazonSNSClient.builder()
                .withRegion(region)
                .withEndpointConfiguration(endpointConfiguration)
                .build();
    }
}
